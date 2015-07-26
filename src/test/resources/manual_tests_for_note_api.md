Simple commands for manual testing via [httpie tool](https://github.com/jakubroztocil/httpie).
==============================================================================================

INSERT NEW NOTE
---------------
Use command:
```
http -f POST localhost:8080/notes \
    name='Test note' \
    description='Some information'
```
Console output:
```js
HTTP/1.1 201 Created
Content-Type: application/json;charset=UTF-8
Date: Sun, 26 Jul 2015 21:00:25 GMT
Location: /notes/55b54a6976d29bf358086f85
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked
X-Application-Context: application
{
    "created": "2015-07-27T00:00:25.879",
    "description": "Some information",
    "id": "55b54a6976d29bf358086f85",
    "name": "Test note"
}

```

GET NOTE
--------
Use command:
```
http GET localhost:8080/notes/55b54a6976d29bf358086f85
```
Console output:
```js
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Sun, 26 Jul 2015 21:01:33 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked
X-Application-Context: application
{
    "created": "2015-07-27T00:00:25.879",
    "description": "Some information",
    "id": "55b54a6976d29bf358086f85",
    "name": "Test note"
}
```

PARTIAL UPDATE NOTE #1
----------------------
Use command:
```
http PUT localhost:8080/notes/55b54a6976d29bf358086f85 \
    name='Test note v2'
```
Console output:
```js
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Sun, 26 Jul 2015 21:02:38 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked
X-Application-Context: application
{
    "created": "2015-07-27T00:00:25.879",
    "description": "Some information",
    "id": "55b54a6976d29bf358086f85",
    "name": "Test note v2"
}
```

PARTIAL UPDATE NOTE #2
----------------------
Use command:
```
http PUT localhost:8080/notes/55b54a6976d29bf358086f85 \
    description='Some information v2'
```
Console output:
```js
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Sun, 26 Jul 2015 21:03:25 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked
X-Application-Context: application
{
    "created": "2015-07-27T00:00:25.879",
    "description": "Some information v2",
    "id": "55b54a6976d29bf358086f85",
    "name": "Test note v2"
}
```

FULL UPDATE NOTE
----------------
Use command:
```
http PUT localhost:8080/notes/55b54a6976d29bf358086f85 \
    id=55b54a6976d29bf358086f85 \
    created='2015-07-27T00:00:00.000' \
    name='Test note v3' \
    description='Some information v3'
```
Console output:
```js
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Sun, 26 Jul 2015 21:06:01 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked
X-Application-Context: application
{
    "created": "2015-07-27T00:00",
    "description": "Some information v3",
    "id": "55b54a6976d29bf358086f85",
    "name": "Test note v3"
}
```

GET ALL NOTES
-------------
Use command:
```
http GET localhost:8080/notes
```
Console output:
```js
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Sun, 26 Jul 2015 21:06:57 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked
X-Application-Context: application
[
    {
        "created": "2015-07-27T00:00",
        "description": "Some information v3",
        "id": "55b54a6976d29bf358086f85",
        "name": "Test note v3"
    }
]
```

DELETE NOTE
-----------
Use command:
```
http DELETE localhost:8080/notes/55b54a6976d29bf358086f85
```
Console output:
```js
HTTP/1.1 200 OK
Content-Length: 0
Date: Sun, 26 Jul 2015 21:07:54 GMT
Server: Apache-Coyote/1.1
X-Application-Context: application
```