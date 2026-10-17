# **🚀 Interview Script & Story Framework for Prakhar Srivastava**

**General Advice for Delivery:**

* **Pacing:** Speak slowly. You have a lot of complex technical achievements. Give the interviewer time to digest the scale (e.g., "100 million products").  
* **"I" vs. "We":** While teamwork is great, make sure you emphasize what *you* did in the "Action" phase. Use "I architected," "I developed," etc.  
* **The Hook:** Start each story with a high-level summary of the business impact before diving into the tech.

## **🏢 Company 1: Walmart Global Tech India (Senior Software Engineer)**

### **Story 1: Scaling the AdTech Catalog (High Throughput/Low Latency)**

**Best for answering:** *"Tell me about a time you scaled a system,"* *"Describe a complex architectural challenge you solved,"* or *"How do you handle performance bottlenecks?"*

* **Situation:** "At Walmart, our AdTech platform was experiencing massive growth. We had over 100,000 advertisers trying to run campaigns, and the underlying catalog data management system needed to support this at an unprecedented scale."  
* **Task:** "My objective was to architect a solution that could handle over 1 million daily search requests across a massive catalog of more than 100 million products, without bottlenecking the advertisers' campaign creation."  
* **Action:** "I took the lead on architecting a suite of 5+ microservices from the ground up using Scala and the Play framework, choosing Scala specifically for its strong concurrency model. To handle the complex, high-volume search queries, I integrated and optimized ElasticSearch. I spent a lot of time fine-tuning the data ingestion and search indexing to ensure the system wouldn't choke under peak loads."  
* **Result:** "The new architecture was a huge success. We successfully supported the 100K+ advertisers and actually managed to reduce overall search latency by 30%, making the platform significantly faster and more reliable."

### **Story 2: The AdTech MCP Server (AI & Innovation)**

**Best for answering:** *"Tell me about a time you worked with GenAI/LLMs in production,"* *"Describe a time you innovated or adopted a brand new technology,"* or *"How have you improved user experience?"*

* **Situation:** "Walmart was rolling out its internal AI Agent platform, 'Marty,' to improve operational efficiency. However, for our advertisers to actually benefit from it, Marty needed a secure and context-aware way to interact with our AdTech catalog."  
* **Task:** "I was tasked with building the bridge—a Model Context Protocol (MCP) server—that would allow the AI agent to understand our catalog and autonomously help advertisers."  
* **Action:** "I designed and implemented the AdTech Catalog MCP Server using Spring AI. I had to ensure the API contracts were perfectly designed so the LLM could predictably pull catalog data, format context windows, and execute actions without hallucinating or breaking data security boundaries. I essentially gave the AI the 'tools' it needed to interact with our backend."  
* **Result:** "This enabled seamless, conversational advertiser onboarding and campaign activation through the AI agent. It took a complex manual process and turned it into an intuitive, AI-driven experience."

### **Story 3: Display Ads Inventory Forecasting (Revenue & Reliability)**

**Best for answering:** *"Tell me about a project that directly impacted the business's bottom line,"* or *"How do you ensure high availability in your systems?"*

* **Situation:** "Our display ads business is highly lucrative but requires exact precision. We had over 10,000 advertisers competing for space, and we needed a way to accurately forecast and manage ad inventory in real-time."  
* **Task:** "I needed to build the forecasting APIs that would serve as the backbone for this inventory management, and because revenue was directly tied to it, downtime wasn't an option."  
* **Action:** "I developed the APIs using Java and Spring Boot. Because we needed real-time big data serving and computation, I integrated Vespa as our engine. I focused heavily on fault-tolerance, implementing robust fallback mechanisms and extensive monitoring to ensure the service was resilient."  
* **Result:** "The APIs achieved a 99.9% uptime. More importantly, by providing highly accurate, real-time inventory forecasting, we optimized our ad placements and directly contributed to a 25% increase in display ad revenue."

## **🏢 Company 2: Paytm (Senior Software Engineer)**

### **Story 4: The Recommendation Service (Personalization & Search)**

**Best for answering:** *"Tell me about a time you built a service from scratch,"* *"How do you handle data-heavy applications?"* or *"Describe a project focused on user engagement."*

* **Situation:** "At Paytm, user engagement is highly dependent on showing the right product or service to the right user at the right time. We needed a dedicated system to handle complex personalization."  
* **Task:** "I was responsible for architecting and building a new Recommendation Service that could serve highly relevant suggestions to millions of users with extremely low latency."  
* **Action:** "I built the microservice using Spring Boot. Given the need to query massive amounts of user behavior and catalog data dynamically, I chose Elasticsearch as the primary datastore. I designed the Elasticsearch mapping to optimize for complex, multi-layered queries—balancing the relevance algorithms with the need for millisecond response times."  
* **Result:** "I successfully delivered the recommendation microservice, which significantly improved the relevance of the content shown to users, easily scaling to handle Paytm's massive daily traffic volume."

**💡 Closing Tip:** When discussing your recent AI certifications (like the Google AI Agents course), tie it directly back to your *AdTech MCP* project. It shows that you don't just take courses—you actively apply cutting-edge research to solve real-world enterprise problems.