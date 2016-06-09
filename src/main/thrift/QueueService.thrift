namespace java com.github.miyasumas.armeria.sandbox.service

struct QueueRequest {
	1: optional i32 priority = 100,
	2: required i32 userId
}

struct QueueResponse {
	1: optional i32 priority = 100,
	2: required i32 userId,
	3: required i32 status
}

service QueueService {
	QueueResponse enqueue(1: QueueRequest request),
	
	QueueResponse dequeue(1: QueueRequest request)
}