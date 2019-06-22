---
title: List AWS S3 bucket objects with HTTP and XML
authors: Alain M. Lafon
category: cloud
date-published: 2018-02-26
tags: 
- aws
- s3
uuid: b5cfdbb4-88bf-4708-92a3-e3df16c41bc8
---

AWS S3 has a great, but little known functionality: You can request
metadata about the content of the bucket in XML. This makes it a great
storage space for all kinds of batch oriented applications. No need
for a self-written API.

Requesting the metadata is easy, it's just a `GET` request to the root
of the buckets' URL: `http://[bucket_name].s3.amazonaws.com/`

The response will look like this:

```xml
<ListBucketResult>
  <Name>your-bucket-name</Name>
  <Prefix/>
  <Marker/>
  <MaxKeys>1000</MaxKeys>
  <IsTruncated>false</IsTruncated>
  <Contents>
    <Key>your_filename.json</Key>
    <LastModified>2018-02-24T21:33:05.000Z</LastModified>
    <ETag>"da3cf2b69a251d0545fb67feb7b1e7ea"</ETag>
    <Size>5649</Size>
    <StorageClass>STANDARD</StorageClass>
  </Contents>
</ListBucketResult>
```

The premise for this to work is that your bucket has to be configured
correctly - your users will need to be able to `list` the bucket and
to `get` objects from it. This is the appropriate AWS policy:

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadGetObject",
      "Effect": "Allow",
      "Principal": "*",
      "Action": [
        "s3:GetObject",
        "s3:ListBucket"
      ],
      "Resource": [
        "arn:aws:s3:::{{ bucket_name }}",
        "arn:aws:s3:::{{ bucket_name }}/*"
      ]
    }
  ]
}

```

Furthermore your bucket needs to be configured to "host a website".
This is a property that you can set in the AWS S3 console.
