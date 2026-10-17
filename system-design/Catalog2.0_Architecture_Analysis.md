# Walmart AdTech Catalog 2.0 - Architecture Analysis

**Document Version:** 1.0  
**Author:** Senior Software Engineer  
**Date:** December 26, 2025  
**Status:** Technical Analysis & Review

---

## Executive Summary

This document provides a comprehensive analysis of the Walmart AdTech Catalog 2.0 system architecture. The system is designed to manage, process, and serve product catalog data for advertising technology (AdTech) use cases. The architecture follows a data lake pattern with multiple ingestion sources, a centralized processing layer (Spark), multiple search indices (Elasticsearch), and API layers for various consumers.

---

## Table of Contents

1. [Architecture Overview](#architecture-overview)
2. [Component Analysis](#component-analysis)
3. [Data Flow Analysis](#data-flow-analysis)
4. [Technology Stack](#technology-stack)
5. [Design Patterns Observed](#design-patterns-observed)
6. [Critical Discussion Points](#critical-discussion-points)
7. [Technical Questions for Design Review](#technical-questions-for-design-review)
8. [Potential Improvements](#potential-improvements)
9. [Risk Assessment](#risk-assessment)

---

## Architecture Overview

### System Purpose
The Catalog 2.0 system serves as the central product data platform for Walmart's advertising technology stack. It:
- Aggregates product data from multiple upstream sources
- Enriches items with performance metrics, taxonomy, and advertiser data
- Provides search and discovery capabilities via Elasticsearch indices
- Enables itemset creation and curation for ad campaigns
- Serves data through legacy and modern APIs

### Architecture Layers

```
┌─────────────────────────────────────────────────────────────────────────┐
│                        DATA SOURCE LAYER                                 │
│   (Uber, DIS, DMAS, DMP, SP Seasonality, Item Correlation)              │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                    DATA INGESTION & PROCESSING LAYER                     │
│              (Catalog Data Ingestion & Processing System - Spark)        │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                         DATA STORAGE LAYER                               │
│  (ES Indices: Taxonomy, Product, Curation | Aggregated Datasets)        │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                           API LAYER                                      │
│    (Catalog Search API, Itemset API, Catalog MCP Server, Permissions)   │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## Component Analysis

### Legend (Color Coding)
| Color | Meaning |
|-------|---------|
| 🟢 **Green** | New component (Catalog 2.0 additions) |
| 🟡 **Yellow** | Modifications to existing components |
| 🔴 **Red** | Need to revisit / Technical debt |
| ⚪ **White/Gray** | No change (stable components) |

---

### 1. DATA SOURCE LAYER

#### 1.1 Uber (External Data Source)
- **Type:** Database/Data Lake
- **Role:** Primary product data source feeding into DMAS Product Index
- **Data Flow:** Uber → DMAS Product Index (ES)
- **Significance:** Likely contains core product catalog from Walmart's main systems

#### 1.2 DIS (Gduns)
- **Type:** Database (marked as ⚪ No Change)
- **Role:** Data Integration Service providing GDUN (Global Data Universal Number) identifiers
- **Data Flow:** Bidirectional with DMAS
- **Significance:** Critical for product identification and deduplication across systems

#### 1.3 DMAS (product_attrs)
- **Type:** Data Management & Analytics System (🟡 Modified)
- **Role:** Stores and provides product attributes
- **Data Flow:** Feeds into Catalog Data Ingestion System
- **Key Data:** Product attributes including core product information

#### 1.4 DMAS Product Index (ES)
- **Type:** Elasticsearch Index
- **Role:** Indexed product data from Uber for fast lookups
- **Significance:** Provides searchable product data to downstream systems

#### 1.5 DMP (PV, Transc)
- **Type:** Data Management Platform
- **Role:** Provides behavioral data - Page Views (PV) and Transaction data
- **Data Flow:** Feeds into SP Seasonality and Catalog Ingestion
- **Significance:** Critical for performance metrics and user behavior analysis

#### 1.6 SP Seasonality (tags, score)
- **Type:** Analytics Component (🟡 Modified)
- **Role:** Provides seasonality tags and scores for Sponsored Products
- **Data Flow:** Consumes DMP data, feeds Catalog Ingestion
- **Use Case:** Enables seasonal targeting for ad campaigns

#### 1.7 Item Correlation (Coview..)
- **Type:** ML/Analytics Component (🟡 Modified)
- **Role:** Provides item correlation data based on co-viewing behavior
- **Data Flow:** Feeds into Catalog Ingestion
- **Use Case:** Powers "frequently bought together" and recommendation features

---

### 2. DATA PROCESSING LAYER

#### 2.1 Catalog Data Ingestion & Processing System
- **Type:** Batch Processing System (Spark-based) 🟢 **NEW**
- **Role:** Central ETL/ELT system for catalog data
- **Technology:** Apache Spark
- **Responsibilities:**
  - Aggregates data from all upstream sources
  - Transforms and enriches product data
  - Produces three main outputs:
    1. Item Attributes Data
    2. Advertiser Item Data
    3. Item Performance Daily

#### 2.2 Output: Item Attributes Data
- **Content:** Core and Taxon Attributes
- **Purpose:** Normalized product attributes with taxonomy classification
- **Consumers:** Ingestion (Fast Data) system

#### 2.3 Output: Advertiser Item Data
- **Content:** adv_id, price, and advertiser-specific attributes
- **Purpose:** Links products to advertisers with pricing data
- **Consumers:** Ingestion (Fast Data) system

#### 2.4 Output: Item Performance Daily
- **Content:** GMV, Orders, Views, Clicks, etc.
- **Purpose:** Daily aggregated performance metrics
- **Consumers:** Ingestion (Fast Data) system

---

### 3. FAST DATA INGESTION LAYER

#### 3.1 Ingestion (Fast Data)
- **Type:** Real-time/Near Real-time Ingestion (Spark-based) 🟢 **NEW**
- **Role:** Processes and routes data from batch outputs to storage
- **Technology:** Apache Spark (streaming or micro-batch)
- **Outputs:**
  - Feeds into Item & Performance Aggregated dataset
  - Connects to Store Item Mapping
  - Connects to Online Item Mapping

#### 3.2 Item & Performance [Aggregated]
- **Type:** Denormalized Dataset 🔴 **NEEDS REVISIT**
- **Role:** Pre-computed, denormalized dataset for fast queries
- **Concern:** Marked red - likely has performance or data quality issues
- **Purpose:** Serves as the primary data source for search indices

---

### 4. ITEM MAPPING LAYER

#### 4.1 Store Item Mapping
- **Type:** Mapping Service (🟢 New)
- **Role:** Maps system item numbers to store-specific identifiers
- **Purpose:** Enables omnichannel advertising (in-store + online)

#### 4.2 Online Item Mapping
- **Type:** Mapping Service (🔴 Needs Revisit)
- **Role:** Maps items for online/e-commerce context
- **Concern:** Marked red - potential data consistency or performance issues

---

### 5. SEARCH & INDEX LAYER

#### 5.1 Iron Bank
- **Type:** External Service (⚪ No Change)
- **Role:** Provides taxonomy data
- **Output:** Feeds taxonomy information to Taxonomy Index
- **Significance:** Authoritative source for product categorization

#### 5.2 AMAAS (Advertiser Meta Data)
- **Type:** External Service (⚪ No Change)
- **Role:** Provides advertiser metadata
- **Output:** Advertiser meta data for search and filtering
- **Significance:** Links products to advertiser accounts

#### 5.3 Taxonomy Index (Elasticsearch)
- **Type:** Search Index (🟡 Modified)
- **Role:** Searchable taxonomy/category index
- **Source:** Iron Bank
- **Consumer:** Catalog Search API

#### 5.4 Product Index (Elasticsearch)
- **Type:** Search Index (🟡 Modified)
- **Role:** Main product search index
- **Source:** Item & Performance Aggregated data
- **Consumer:** Catalog Search API

#### 5.5 Curation Index (Elasticsearch)
- **Type:** Search Index (🔴 Needs Revisit)
- **Content:** ItemsetId, Items mapping
- **Role:** Stores curated itemsets for ad campaigns
- **Concern:** Marked red - needs architectural review

---

### 6. ITEMSET BUILDER SUBSYSTEM

#### 6.1 Data Discovery Trino
- **Type:** Query Engine (🟡 Modified)
- **Role:** SQL-based data discovery and exploration
- **Technology:** Trino (formerly PrestoSQL)
- **Purpose:** Ad-hoc queries for itemset discovery

#### 6.2 Itemset Catalog DAG
- **Type:** Workflow Orchestration (🟡 Modified)
- **Role:** DAG (Directed Acyclic Graph) for itemset processing
- **Purpose:** Orchestrates itemset creation workflows

#### 6.3 Itemset Items
- **Type:** Data Store (🟢 New)
- **Role:** Stores items belonging to itemsets
- **Flow:** Receives from DAG, feeds Curation Index

#### 6.4 Itemset Metadata
- **Type:** Data Store (🟢 New)
- **Role:** Stores metadata about itemsets
- **Flow:** Receives from DAG, feeds Itemset API

#### 6.5 Build Event Processor (Itemset Builder)
- **Type:** Event-driven Processor (🟡 Modified)
- **Role:** Processes itemset build triggers
- **Trigger:** "Trigger Itemset creation"
- **Output:** Sends events to Event Queue (Kafka)

#### 6.6 Event Queue (Kafka)
- **Type:** Message Queue (🟡 Modified)
- **Technology:** Apache Kafka
- **Role:** Decouples itemset creation from processing
- **Pattern:** Event-driven architecture

---

### 7. API LAYER

#### 7.1 Catalog Search API (Legacy + DIS)
- **Type:** REST API (🟡 Modified)
- **Role:** Primary search interface for catalog data
- **Indices Used:** Taxonomy Index, Product Index
- **Note:** Supports both legacy and DIS-based queries

#### 7.2 Catalog MCP Server
- **Type:** API Server (🟢 New)
- **Role:** Model Context Protocol server for AI/ML integrations
- **Purpose:** Enables LLM/AI-based catalog interactions
- **Significance:** Modern AI-first API interface

#### 7.3 Itemset API (Legacy + DIS)
- **Type:** REST API (🟡 Modified)
- **Role:** CRUD operations for itemsets
- **Features:**
  - Itemset creation
  - Integration with Curation Index
  - Legacy compatibility

#### 7.4 Permissions API (Authorization)
- **Type:** Security Service (🟢 New)
- **Role:** Handles authorization for API access
- **Significance:** Centralized access control

---

## Data Flow Analysis

### Primary Data Flow (Catalog Ingestion)

```
1. Raw Data Sources
   ├── Uber → DMAS Product Index (ES)
   ├── DIS (Gduns) ↔ DMAS (product_attrs)
   ├── DMP (PV, Transc) → SP Seasonality
   └── Item Correlation (Coview)

2. Batch Processing
   └── All sources → Catalog Data Ingestion & Processing (Spark)
       ├── Item Attributes Data
       ├── Advertiser Item Data
       └── Item Performance Daily

3. Fast Data Ingestion
   └── Ingestion (Spark) → Item & Performance [Aggregated]
       ├── Store Item Mapping
       └── Online Item Mapping

4. Indexing
   ├── Iron Bank → Taxonomy Index (ES)
   ├── AMAAS → Advertiser metadata
   └── Aggregated Data → Product Index (ES)

5. API Serving
   └── Catalog Search API ← Product Index + Taxonomy Index
```

### Itemset Creation Flow

```
1. Discovery
   └── Data Discovery (Trino) → User discovers items

2. Build Trigger
   └── Itemset Catalog DAG
       ├── Itemset Items → Curation Index
       └── Itemset Metadata → Itemset API

3. Event Processing
   └── Build Event Processor → Event Queue (Kafka) → Itemset API
```

---

## Technology Stack

| Layer | Technology | Purpose |
|-------|------------|---------|
| Data Processing | Apache Spark | Batch & streaming ETL |
| Search | Elasticsearch | Full-text search & analytics |
| Query Engine | Trino | Ad-hoc SQL queries |
| Message Queue | Apache Kafka | Event streaming |
| Workflow | DAG-based (likely Airflow) | Orchestration |
| API | REST APIs | Service interfaces |
| AI Integration | MCP Server | LLM/AI context serving |

---

## Design Patterns Observed

### 1. **Lambda Architecture**
- Batch layer: Catalog Data Ingestion & Processing (Spark)
- Speed layer: Ingestion (Fast Data)
- Serving layer: Elasticsearch indices

### 2. **Event-Driven Architecture**
- Kafka-based event queue for itemset creation
- Decoupled producers and consumers

### 3. **CQRS (Command Query Responsibility Segregation)**
- Separate write path (ingestion) and read path (search APIs)
- Denormalized read models in Elasticsearch

### 4. **Data Lake Pattern**
- Multiple source ingestion
- Centralized processing
- Multiple serving layers

### 5. **API Gateway Pattern**
- Multiple APIs serving different use cases
- Centralized permissions/authorization

---

## Critical Discussion Points

### 1. 🔴 Components Marked "Needs Revisit"

**Item & Performance [Aggregated]**
- Why is this marked red?
- Possible issues: Data freshness, query performance, schema evolution challenges
- Impact: This is a critical path component feeding Product Index

**Online Item Mapping**
- Potential data consistency issues with Store Item Mapping?
- Reconciliation challenges between online and store items?

**Curation Index**
- Scaling concerns for itemset storage?
- Query performance for large itemsets?

### 2. Legacy + New System Coexistence

The architecture shows "Legacy + DIS" notation on APIs, indicating:
- Migration is in progress
- Backward compatibility requirements
- Potential technical debt

### 3. Multiple Elasticsearch Indices

Three separate indices (Taxonomy, Product, Curation):
- Pro: Separation of concerns, independent scaling
- Con: Cross-index queries complexity, data consistency challenges

### 4. Spark for Both Batch and Fast Data

Using Spark for both layers raises questions:
- Is Spark Streaming used for "Fast Data"?
- What's the latency SLA for fast data path?
- Would Flink be more suitable for true real-time?

---

## Technical Questions for Design Review

### Data Ingestion & Processing

1. **What is the batch processing frequency?** Daily, hourly, or more frequent?

2. **What's the data volume per batch?** Important for Spark cluster sizing.

3. **How is exactly-once semantics guaranteed** in the Spark processing pipeline?

4. **What happens when upstream sources (Uber, DIS) are unavailable?** Is there retry logic or circuit breakers?

5. **How is schema evolution handled** across the pipeline? What if DMAS adds new attributes?

### Data Consistency

6. **What's the consistency model** between batch-processed data and fast data ingestion?

7. **How do you handle late-arriving data** in Item Performance Daily?

8. **What's the reconciliation strategy** between Store Item Mapping and Online Item Mapping?

9. **How is data lineage tracked** from source to Elasticsearch indices?

### Search & Indexing

10. **What's the indexing latency** from Aggregated dataset to Product Index?

11. **How are Elasticsearch indices versioned/blue-green deployed?**

12. **What's the reindexing strategy** when schema changes?

13. **How do you handle Elasticsearch cluster failures?** Cross-region replication?

### Itemset Management

14. **What's the maximum itemset size supported?** (10K items? 1M items?)

15. **How are itemset updates propagated?** Full rebuild or incremental?

16. **What's the SLA for itemset creation** via the Build Event Processor?

17. **How is Kafka consumer lag monitored** for the Event Queue?

### API Layer

18. **What's the API rate limiting strategy?** Per-advertiser? Global?

19. **How does the MCP Server integrate with LLMs?** What context does it provide?

20. **How is API versioning handled** between Legacy and DIS versions?

### Performance & Scalability

21. **What are the current QPS for each API?** What's the target for Catalog 2.0?

22. **How does the system handle Black Friday / peak traffic?**

23. **What's the total data size** in Product Index? Growth rate?

24. **How are Spark resources allocated** between batch and fast data jobs?

### Security & Compliance

25. **How is PII handled** in the catalog data? Any GDPR/CCPA considerations?

26. **What's the audit trail** for itemset modifications?

27. **How does Permissions API integrate** with Walmart's IAM?

### Operational Concerns

28. **What's the disaster recovery strategy?** RTO/RPO targets?

29. **How is data quality monitored** at each stage?

30. **What alerting exists** for pipeline failures?

---

## Potential Improvements

### Short-term (Quick Wins)

1. **Add Data Quality Gates**
   - Implement Great Expectations or similar at ingestion boundaries
   - Fail fast on schema violations

2. **Implement Circuit Breakers**
   - Add resilience patterns for external dependencies (Iron Bank, AMAAS)

3. **Unified Monitoring Dashboard**
   - Single pane of glass for pipeline health
   - End-to-end latency tracking

### Medium-term

4. **Consider Apache Flink for Fast Data**
   - Better suited for true real-time processing
   - Native exactly-once semantics

5. **Implement Change Data Capture (CDC)**
   - Replace batch pulls with CDC from upstream sources
   - Reduce latency from hours to minutes

6. **GraphQL API Layer**
   - Add GraphQL for flexible querying
   - Reduce over-fetching for mobile clients

### Long-term

7. **Move to Event Sourcing**
   - Immutable event log as source of truth
   - Enable time-travel queries

8. **Federated Search Architecture**
   - Instead of copying data to indices, federate queries
   - Reduces data duplication

9. **ML Pipeline Integration**
   - Native ML feature store integration
   - Real-time inference for item recommendations

---

## Risk Assessment

| Risk | Severity | Likelihood | Mitigation |
|------|----------|------------|------------|
| Aggregated Dataset becomes bottleneck | High | Medium | Horizontal scaling, partition strategy |
| Elasticsearch index corruption | High | Low | Multi-AZ, regular snapshots |
| Kafka consumer lag during peak | Medium | Medium | Auto-scaling consumers, backpressure |
| Legacy API deprecation breaks clients | Medium | High | Versioning, deprecation timeline |
| Data inconsistency across indices | Medium | Medium | Reconciliation jobs, monitoring |
| Single point of failure in Spark jobs | High | Low | Checkpointing, job monitoring |

---

## Appendix: Component Status Summary

| Component | Status | Priority |
|-----------|--------|----------|
| Catalog Data Ingestion & Processing | 🟢 New | P0 |
| Ingestion (Fast Data) | 🟢 New | P0 |
| Catalog MCP Server | 🟢 New | P1 |
| Permissions API | 🟢 New | P1 |
| Store Item Mapping | 🟢 New | P1 |
| Itemset Items | 🟢 New | P2 |
| Itemset Metadata | 🟢 New | P2 |
| DMAS (product_attrs) | 🟡 Modified | P1 |
| SP Seasonality | 🟡 Modified | P2 |
| Item Correlation | 🟡 Modified | P2 |
| Taxonomy Index | 🟡 Modified | P1 |
| Product Index | 🟡 Modified | P0 |
| Data Discovery Trino | 🟡 Modified | P2 |
| Itemset Catalog DAG | 🟡 Modified | P2 |
| Build Event Processor | 🟡 Modified | P2 |
| Event Queue (Kafka) | 🟡 Modified | P1 |
| Catalog Search API | 🟡 Modified | P0 |
| Itemset API | 🟡 Modified | P1 |
| Item & Performance [Aggregated] | 🔴 Revisit | P0 |
| Online Item Mapping | 🔴 Revisit | P1 |
| Curation Index | 🔴 Revisit | P1 |

---

## Conclusion

The Catalog 2.0 architecture represents a significant evolution of Walmart's AdTech catalog infrastructure. The design follows proven patterns (Lambda architecture, CQRS, Event-driven) and leverages mature technologies (Spark, Elasticsearch, Kafka).

**Key Strengths:**
- Clear separation of concerns
- Scalable technology choices
- Modern AI integration (MCP Server)
- Event-driven decoupling

**Areas of Concern:**
- Three components marked "needs revisit" are critical path
- Legacy + new system coexistence adds complexity
- Multiple data copies increase consistency challenges

**Recommended Next Steps:**
1. Deep-dive into red-marked components
2. Define clear SLAs for each data path
3. Establish data quality metrics and monitoring
4. Create migration timeline for legacy deprecation

---

*Document prepared for architecture review. Please schedule a follow-up session to discuss findings and prioritize action items.*

