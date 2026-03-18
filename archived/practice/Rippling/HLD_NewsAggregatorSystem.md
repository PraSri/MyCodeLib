### High-Level Design for News Aggregator System (Google News-like)

# Requirements: 
## Fetch news articles from multiple news publishers
## Generate custom news feed for Users based on their interests and the publishers they follow
## we scrape from multiple news sites. Assume they provide endpoint to get latest 25 news from each publisher. 
## There can be thousands of publishers.
## It was required to write down table schema for all like publisher, user, category_subscription, publisher_subscription,
## articles etc and handle deduplication in article scraping service etc.
## Along with this, user's should be able to subscribe to certain categories like Education or publishers say ndtv.
## On home page.

---

#### **1. Core Components**
| **Component**          | **Function**                                                                 |
|-------------------------|-----------------------------------------------------------------------------|
| **News Fetcher**        | Collects articles from publishers via APIs/RSS feeds/web scraping.          |
| **Processing Pipeline** | Cleans, dedupes, categorizes, and tags articles (NLP/ML for topic extraction). |
| **User Profile Store**  | Manages user data: interests, followed publishers, reading history, location. |
| **Personalization Engine** | Generates feeds using collaborative filtering + content-based ranking.     |
| **Feed Service**        | Serves real-time personalized feeds via APIs.                               |
| **Storage Layer**       | Stores articles, user profiles, and relationships (SQL/NoSQL).              |
| **Analytics Service**   | Tracks user interactions (clicks, dwell time) for relevance feedback.      |

---

#### **2. Workflow**
**A. News Ingestion**
1. **Fetcher** polls publishers (e.g., CNN, BBC) every 5-10 mins via APIs/RSS.
2. **Processing Pipeline**:
   - Dedupes using URL/title hashes.
   - Extracts entities (people, locations) and topics (politics, tech) via NLP.
   - Stores processed articles in **Article DB** (e.g., Cassandra for scalability).

**B. Personalization**
1. **User Profile Builder**:
   - Tracks explicit preferences (followed publishers) and implicit signals (clicks, shares).
   - Updates profiles in **User DB** (e.g., PostgreSQL for ACID compliance).
2. **Personalization Engine**:
   - **Candidate Sourcing**: Fetches articles from followed publishers + topics of interest.
   - **Ranking**: Scores articles using:
     - `Relevance Score = (Topic Match × 0.6) + (Publisher Affinity × 0.3) + (Freshness × 0.1)`.
     - Boosts diversity (e.g., 1 article/publisher in top 10).

**C. Feed Delivery**
1. **Feed Service**:
   - Generates JSON feeds on-demand for users.
   - Caches responses (Redis) for 60s to handle spikes.
2. **Real-Time Updates**:
   - New articles trigger Kafka events → pushed to online users via WebSockets.

---

#### **3. Data Models**
**Article DB (Cassandra)**
```plaintext
articles {
  article_id (UUID)
  title (text)
  content (text)
  publisher (text)
  category (text)
  publish_time (timestamp)
  tags (list<text>)  // e.g., ["AI", "Elections"]
}
```

**User DB (PostgreSQL)**
```plaintext
users {
  user_id (UUID)
  interests (jsonb)  // e.g., {"sports": 0.8, "tech": 0.7}
  following_publishers (list<text>)
  last_read_articles (list<UUID>)
}
```

**Interaction Data (Clickhouse)**
```plaintext
clicks {
  user_id (UUID)
  article_id (UUID)
  timestamp (datetime)
  dwell_time (int)  // in seconds
}
```

---

#### **4. Personalization Engine**
- **Training**:
  - Uses TensorFlow/PyTorch to train a ranking model:
    - Features: User interests, article tags, publisher affinity, popularity.
    - Labels: Click-through rate (CTR) from historical data.
- **Serving**:
  - Real-time scoring via ONNX/TensorFlow Serving.
  - Fallback to rule-based ranking if ML model fails.

---

#### **5. Scalability & Reliability**
- **Horizontal Scaling**:
  - Microservices architecture (Kubernetes).
  - Sharded databases (e.g., user_id-based sharding).
- **Redundancy**:
  - Multiple News Fetcher instances with retries/backoff.
  - Replicated DBs across AZs.
- **QPS Handling**:
  - 10K RPS via API gateways + Redis cache.
  - Rate limiting (100 reqs/user/min).

---

#### **6. Challenges & Solutions**
| **Challenge**              | **Solution**                                      |
|----------------------------|--------------------------------------------------|
| Duplicate articles         | MinHash/LSH for near-duplicate detection.        |
| Feed staleness             | Push updates via WebSockets for logged-in users. |
| Cold-start problem         | Show trending/popular articles for new users.    |
| Bias in personalization    | Inject diversity (e.g., 20% non-personalized).   |
| Publisher rate limits      | Respect `robots.txt`; use exponential backoff.   |

---

#### **7. Advanced Features**
- **A/B Testing**: Compare ranking algorithms (e.g., ML vs. heuristic).
- **Breaking News Alert**: Bypass personalization for critical events.
- **Multilingual Support**: Translate articles via AWS Translate/Google ML.

---

### Architecture Diagram
```plaintext
┌──────────────────────┐       ┌──────────────────────┐
│   News Publishers    │──────>│     News Fetcher      │
│ (APIs/RSS/Scraping)  │<──────│ (Rate Limiter/Retry)  │
└──────────────────────┘       └──────────┬───────────┘
                                          │
                                 ┌────────▼───────────┐
                                 │ Processing Pipeline│
                                 │  - Dedupe          │
                                 │  - NLP Tagging     │
                                 └────────┬───────────┘
                                          │
                   ┌──────────────────────▼──────────────────────┐
                   │                Storage Layer               │
                   │ ┌────────────┐  ┌────────────┐  ┌─────────┐│
                   │ │ Article DB │  │  User DB   │  │  Logs  ││
                   │ └────────────┘  └────────────┘  └─────────┘│
                   └───────┬─────────────────┬──────────────────┘
                           │                 │
           ┌───────────────▼─────┐ ┌─────────▼──────────┐
           │ Personalization     │ │ Analytics Service   │
           │ Engine (ML Serving) │ │ (Track interactions)│
           └───────────────┬─────┘ └─────────┬──────────┘
                           │                 │
                   ┌───────▼─────────────────▼───────┐
                   │          Feed Service           │
                   │ (API + Caching + WebSockets)    │
                   └────────────────┬────────────────┘
                                    │
                           ┌────────▼────────┐
                           │ Mobile/Web Client│
                           └──────────────────┘
```

### Tools & Technologies
- **Data Pipeline**: Apache Kafka, Spark Streaming.
- **NLP**: spaCy, Google Cloud NLP.
- **ML**: TensorFlow, Scikit-Learn.
- **DBs**: Cassandra (articles), PostgreSQL (users), Redis (cache).
- **Infra**: Kubernetes, Docker, AWS/GCP.