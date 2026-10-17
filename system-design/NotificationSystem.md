

FR
- send notification to user immediately or scheduled
- send campaigns, immediately or schedules
- user should able to set notifications preferences

NFR
- At-least-once delivery with best effort de-duplication
- Handle surges of ~5000 notification per second
- Deliver critical notifications within 5 sec, even during a surge

Core entities
- Notification
- Template
- Campaign 
- Segment
- User

APIs

POST /notifications -> 200 OK {id, status}
Body: {
    userId,
    channel, // push | email | sms
    priority, // high | standard
    content, // {title: "", body: ""}
    scheduledAt // optional, defaults to now
}

POST /campaigns -> 202 Accepted { id }
Body: {
    segmentId, 
    channel,
    priority,
    templateId,
    scheduledAt
}

PUT /users/{userId}/preferences -> Preferences
Body: {
    optOuts, // eg. ["sms"]
    quietHours // eg. {start: "22:00", end: "08:00", tz}   
}

