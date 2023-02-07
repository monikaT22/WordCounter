# word-counter-library

It have following features
1. Add words (only alphabetic)
2. Count of how many times word was added

# API Endpoints
1. Add words

![Add Words](https://user-images.githubusercontent.com/124272753/216463082-e694948e-e549-4c6a-a9df-dac41c590dff.PNG)


2. Count word (api/word/count/{word_to_get_count}

![Count Word](https://user-images.githubusercontent.com/124272753/216463117-0c7769b6-3c7d-406b-aad5-b63ca638efea.PNG)

# Design Patterns Used
1. Singleton Pattern
2. Map


# Software Design Principles
1. SOLID
2. Loose Coupling 

# Clients to access this API
As library is written in RESTful web service clients can access it using standard HTTP request and can call API endpoints mentioned above. By default they will get the response in JSON.

# Service Hosting and Application in Production
Hosting will depends on various factors such as scalability,cost,security,performance

We can host word counter library as container like docker. We can host containers on cloud/on-premises server.

# Service Resiliency 
1. We can create multiple instances and can implement load balancing. So in this case even if any instance fails , service will be available.
2. Failover - If we are deploying service on cloud/k8s , then we can use auto-scaling feature
3. Monitoring - By implemening monitoring/alerts. we can dignose the service issues before they become critical
 



