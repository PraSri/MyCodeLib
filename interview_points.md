# Interview Preparation Points

Here are your key achievements elaborated using the **STAR method (Situation, Task, Action, Result)**. This structure is ideal for behavioral and managerial interviews to demonstrate technical depth, problem-solving, and impact.

---

## 1. International Expansion of Catalog Services

**Theme:** scalability, Architecture Design, Multi-tenancy

*   **Situation:** The catalog service was originally designed for a single region/market, tightly coupling business logic with a specific locale. As the business planned to launch in multiple international markets, the existing architecture could not support distinct regional requirements (taxes, languages, compliance) efficiently.
*   **Task:** I needed to re-architect the catalog services to support multi-tenancy and locale-specific data isolation to enable global expansion without deploying separate instances for every country.
*   **Action:**
    *   Designed and implemented a **multi-tenant architecture** where tenant context is propagated through the request lifecycle.
    *   Decoupled locale-specific logic from the core platform, allowing for configuration-driven regional overrides.
    *   Updated the database schema to support internationalization (i18n) and localization (l10n) at the storage layer.
*   **Result:** Successfully enabled the launch into **X new countries** (e.g., EU, APAC regions) with zero code duplication. Reduced onboarding time for new markets from weeks to days by standardizing the locale configuration process.

---

## 2. Scalable GCS Download/Upload (Memory Optimization)

**Theme:** Performance Optimization, Scalability, System Stability

*   **Situation:** The legacy system processed CSV uploads/downloads completely in-memory. This worked for small datasets, but as the customer base grew, large files caused frequent **OutOfMemory (OOM)** exceptions and server crashes, leading to poor reliability.
*   **Task:** I had to modernize the file processing pipeline to handle gigabyte-scale datasets reliably, regardless of the server's available RAM.
*   **Action:**
    *   Refactored the file handling logic to use **streaming I/O** instead of loading entire files into memory.
    *   Integrated with Google Cloud Storage (GCS) to handle direct upload/download streams.
    *   Implemented **chunked processing** (or backpressure handling) to process records typically in constant memory space.
*   **Result:** completely eliminated OOM crashes related to file processing. The system can now handle files **10x-100x larger** than before (e.g., scaling from MBs to GBs) with consistent performance and lower resource utilization.

<details>
<summary>Efficiency of File upload & download feature</summary>

STAR

1. **Situation**:
   1. Advertisers sometimes want to upload/download items data directly in bulk through our campaign creation portal instead of searching and then adding.
   2. Our legacy system was processing the files in-memory completely. Means, if user request for download, it will be downloaded via and if user wants to upload, the file will be uploaded as a whole.
   3. This is nice, till we have small users and small catalog of products.
   4. But as customers base increases and high volume of products catalog merged, system started having severe issuses.
   5. First, due to heavy processing on server side, our thread contention started elevating and due to which our simple read apis calls were affected impacting the user experience.
   6. Second, large files starting causing OOM, server crashes, new server, service downtime, re-work for new request.

2. **Task**: 
   1. I had to re-design the file processing system to handle the GBs scale datasets reliably, which improves the overall user experience without affecting any SLAs
3. **Action**:
   1. Purposed a design 
4. **Result**:
    1.

</details>

---

## 3. MCP Server for Catalog Services

**Theme:** Innovation, AI Integration, Future-Proofing

*   **Situation:** With the rise of AI agents, there was a need for external and internal automated agents to interact with our catalog data. However, our existing APIs were designed for frontend consumption, not for agentic reasoning or standard protocol discovery.
*   **Task:** I aimed to build an interface that allows AI agents to seamlessly query, update, and reason about catalog data using standard protocols.
*   **Action:**
    *   Developed an **MCP (Model Context Protocol) server** wrapper around the catalog services.
    *   Exposed catalog tools and resources in a standardized format that LLMs/Agents can natively understand and invoke.
    *   Implemented security boundaries to ensure agents only access authorized data scopes.
*   **Result:** Enabled a new class of automation where Agents can autonomously resolve catalog queries. This reduced manual support operations and laid the foundation for an "Agent-first" API strategy.

---

## 4. Item Variants in Catalog Search

**Theme:** User Experience (UX), Search Relevance, Data Modeling

*   **Situation:** Customers searching for products often saw search results cluttered with multiple similar entries (e.g., the same shirt in 5 different colors listed separately). This "variant explosion" degraded the discovery experience and made comparison difficult.
*   **Task:** I needed to implement a "Unified View" in the search flow that aggregates related item variants under a single parent product.
*   **Action:**
    *   Redesigned the search indexing strategy to support **parent-child relationships** (grouping variants).
    *   Modified the search query logic to return consolidated "parent" items while preserving the ability to filter by specific variant attributes (size, color).
    *   Optimized the aggregation query performance to ensure no latency regression in search results.
*   **Result:** improved the search experience significantly, offering a cleaner UI. This likely contributed to higher **click-through rates (CTR)** and conversion, as users could find the right product variant faster.

---

## 5. Infrastructure Modernization (Sandbox Environment)

**Theme:** DevOps, Developer Productivity, QA Agility

*   **Situation:** We lacked a true "Sandbox" environment. Testing critical features often happened in Staging (which was sometimes unstable) or required risky testing in Production. Customers/Partners had no safe place to integrate with our APIs.
*   **Task:** I lead the initiative to build a dedicated Sandbox environment that mirrors production functionality but with lenient SLAs, strictly for testing and integration.
*   **Action:**
    *   Provisioned a new isolated environment using Infrastructure-as-Code (Terraform/CloudFormation).
    *   Configured **lenient SLAs** and cost-optimized infrastructure (e.g., using spot instances or lower-tier databases) since high availability wasn't critical for Sandbox.
    *   Established data mocking/seeding pipelines so the Sandbox always had relevant test data without exposing PII from production.
*   **Result:** Accelerated feature development and partner onboarding. External partners could now integrate and certifiy their implementations in Sandbox before going live. Internal teams could run destructive tests safely, improving overall production stability.
