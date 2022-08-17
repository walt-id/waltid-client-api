# ISS VP

## Create Init SIOP2 request at IDP
```http request
GET http://localhost:8080/api/oidc/authorize?response_type=code&scope=openid%20profile%20email&client_id=s6BhdRkqt3&state=af0ifjsldkj&redirect_uri=https%3A%2F%2Fclient.example.org%2Fcb
```
-> loc header

## initCredentialPresentation SIOPv2 request at Client API
```http request
GET /api/wallet/siopv2/initPresentation/?response_type=id_token&response_mode=form_post&client_id=http%3A%2F%2Flocalhost%3A8080%2Fapi%2Fsiop%2Fverify&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fapi%2Fsiop%2Fverify&scope=openid&nonce=ccb33ea4-694f-49ae-b9dd-eb1efa77bb10&claims=%7B%22vp_token%22+%3A+%7B%22presentation_definition%22+%3A+%7B%22format%22+%3A+null%2C+%22id%22+%3A+%221%22%2C+%22input_descriptors%22+%3A+%5B%7B%22constraints%22+%3A+%7B%22fields%22+%3A+%5B%7B%22filter%22+%3A+%7B%22const%22%3A+%22VerifiableId%22%7D%2C+%22id%22+%3A+%221%22%2C+%22path%22+%3A+%5B%22%24.type%22%5D%2C+%22purpose%22+%3A+null%7D%5D%7D%2C+%22format%22+%3A+null%2C+%22group%22+%3A+%5B%22A%22%5D%2C+%22id%22+%3A+%220%22%2C+%22name%22+%3A+null%2C+%22purpose%22+%3A+null%2C+%22schema%22+%3A+null%7D%5D%2C+%22name%22+%3A+null%2C+%22purpose%22+%3A+null%2C+%22submission_requirements%22+%3A+%5B%7B%22count%22+%3A+null%2C+%22from%22+%3A+%22A%22%2C+%22from_nested%22+%3A+null%2C+%22max%22+%3A+null%2C+%22min%22+%3A+null%2C+%22name%22+%3A+null%2C+%22purpose%22+%3A+null%2C+%22rule%22+%3A+%22all%22%7D%5D%7D%7D%7D&state=eyJpZHBTZXNzaW9uSWQiIDogIjViMTI5ZThmLWQwNDktNDU4My04N2IwLTczMzA0NTQxN2Q4NSIsICJpZHBUeXBlIiA6ICJPSURDIn0= HTTP/1.1
Host: localhost:8091
User-Agent: curl/7.84.0
Accept: */*
```

```text
> step: initCredentialPresentation
==================================
NEW SIOPv2 REQUEST FOR CREDENTIALS
ID: 903d0826-10d5-4252-82c2-344b171a2c38
Please enter DID: 
```
`did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb`
```text
PRESENTABLE CREDENTIAL: PresentableCredential(credentialId=urn:uuid:39f242ee-c97c-4414-8c2a-bc1ca5971f9d, claimId=0) - {"claimId" : "0", "credentialId" : "urn:uuid:39f242ee-c97c-4414-8c2a-bc1ca5971f9d"}
Please enter selected credentials:
```
`[{"claimId" : "0", "credentialId" : "urn:uuid:39f242ee-c97c-4414-8c2a-bc1ca5971f9d"}]`
```text
FULFILLING PRESENTATION...

111374 [DefaultDispatcher-worker-1] DEBUG id.walt.services.vc.WaltIdJsonLdCredentialService - Creating a presentation for VCs:
[{"@context" : ["https://www.w3.org/2018/credentials/v1"], "credentialSchema" : {"id" : "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xb77f8516a965631b4f197ad54c65a9e2f9936ebfb76bae4906d33744dbcc60ba", "type" : "FullJsonSchemaValidator2021"}, "credentialSubject" : {"currentAddress" : ["1 Boulevard de la Liberté, 59800 Lille"], "dateOfBirth" : "1993-04-08", "familyName" : "DOE", "firstName" : "Jane", "gender" : "FEMALE", "id" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb", "nameAndFamilyNameAtBirth" : "Jane DOE", "personalIdentifier" : "0904008084H", "placeOfBirth" : "LILLE, FRANCE"}, "evidence" : [{"documentPresence" : ["Physical"], "evidenceDocument" : ["Passport"], "subjectPresence" : "Physical", "type" : ["DocumentVerification"], "verifier" : "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN"}], "id" : "urn:uuid:39f242ee-c97c-4414-8c2a-bc1ca5971f9d", "issued" : "2022-08-10T23:38:44.321479468Z", "issuer" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb", "proof" : {"created" : "2022-08-10T23:38:44Z", "creator" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb", "domain" : "https://api.preprod.ebsi.eu", "jws" : "eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFUzI1NksifQ..eJE3rc1SsuNJJFgjzbm7w1Plw4dM7D73BL-43qUzZwCZssJ52wNywobfNgY6qpzyrJdcBtTrqlos2QLkyjM8iA", "nonce" : "1daaaf4b-cd7d-4205-ada4-a392ca50cd34", "proofPurpose" : "assertion", "type" : "EcdsaSecp256k1Signature2019"}, "validFrom" : "2022-08-10T23:38:44.321482223Z", "issuanceDate" : "2022-08-10T23:38:44.321482223Z", "type" : ["VerifiableCredential", "VerifiableAttestation", "VerifiableId"]}]
111376 [DefaultDispatcher-worker-1] DEBUG DidUrl - Trying to create DidUrl from URL: "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb"...
111396 [DefaultDispatcher-worker-1] DEBUG FileSystemHKVStore - Mapping "did%3Akey%3Az7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb" to "WARR2ZP2HERP2CMSGFHLYKUF557ALT7XOE4QXH7VKG57TGHVW3OA7IFXD4OYAWT3Q4ZY7RZMSHYI7ZA4KBJAVDQKYO24PSBX44R7XTA"
111397 [DefaultDispatcher-worker-1] DEBUG FileSystemHKVStore - File mapping is hashed: Path was "/home/gatgeagent/IdeaProjects/LetsTrust/waltid-client-api/./data/did/created/did%3Akey%3Az7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb", new path is ./data/did/created/WARR2ZP2HERP2CMSGFHLYKUF557ALT7XOE4QXH7VKG57TGHVW3OA7IFXD4OYAWT3Q4ZY7RZMSHYI7ZA4KBJAVDQKYO24PSBX44R7XTA
111417 [DefaultDispatcher-worker-1] DEBUG DidUrl - Trying to create DidUrl from URL: "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb"...
111440 [DefaultDispatcher-worker-1] DEBUG id.walt.services.vc.WaltIdJsonLdCredentialService - Signing jsonLd object with: issuerDid (did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb), domain (null), nonce (ccb33ea4-694f-49ae-b9dd-eb1efa77bb10
111634 [DefaultDispatcher-worker-1] DEBUG id.walt.services.keystore.SqlKeyStoreService - Loading key "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb"...
111685 [DefaultDispatcher-worker-1] DEBUG id.walt.services.vc.WaltIdJsonLdCredentialService - Signing: {"@context":["https://www.w3.org/2018/credentials/v1"],"holder":"did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb","id":"urn:uuid:40beb1d4-f381-4c85-836f-47753a716b8a","type":["VerifiablePresentation"],"verifiableCredential":[{"@context":["https://www.w3.org/2018/credentials/v1"],"credentialSchema":{"id":"https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xb77f8516a965631b4f197ad54c65a9e2f9936ebfb76bae4906d33744dbcc60ba","type":"FullJsonSchemaValidator2021"},"credentialSubject":{"currentAddress":["1 Boulevard de la Liberté, 59800 Lille"],"dateOfBirth":"1993-04-08","familyName":"DOE","firstName":"Jane","gender":"FEMALE","id":"did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb","nameAndFamilyNameAtBirth":"Jane DOE","personalIdentifier":"0904008084H","placeOfBirth":"LILLE, FRANCE"},"evidence":[{"documentPresence":["Physical"],"evidenceDocument":["Passport"],"subjectPresence":"Physical","type":["DocumentVerification"],"verifier":"did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN"}],"id":"urn:uuid:39f242ee-c97c-4414-8c2a-bc1ca5971f9d","issued":"2022-08-10T23:38:44.321479468Z","issuer":"did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb","proof":{"created":"2022-08-10T23:38:44Z","creator":"did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb","domain":"https://api.preprod.ebsi.eu","jws":"eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFUzI1NksifQ..eJE3rc1SsuNJJFgjzbm7w1Plw4dM7D73BL-43qUzZwCZssJ52wNywobfNgY6qpzyrJdcBtTrqlos2QLkyjM8iA","nonce":"1daaaf4b-cd7d-4205-ada4-a392ca50cd34","proofPurpose":"assertion","type":"EcdsaSecp256k1Signature2019"},"validFrom":"2022-08-10T23:38:44.321482223Z","issuanceDate":"2022-08-10T23:38:44.321482223Z","type":["VerifiableCredential","VerifiableAttestation","VerifiableId"]}]}
111699 [DefaultDispatcher-worker-1] DEBUG id.walt.services.keystore.SqlKeyStoreService - Loading key "d2c3158e8428462f8eb7186787e030af"...
111699 [DefaultDispatcher-worker-1] DEBUG com.zaxxer.hikari.pool.ProxyConnection - HikariPool-2 - Executed rollback on connection org.sqlite.jdbc4.JDBC4Connection@6d1dcdff due to dirty commit state on close().
111765 [DefaultDispatcher-worker-1] DEBUG id.walt.services.vc.WaltIdJsonLdCredentialService - VP created:{
  "@context" : [ "https://www.w3.org/2018/credentials/v1" ],
  "holder" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb",
  "id" : "urn:uuid:40beb1d4-f381-4c85-836f-47753a716b8a",
  "type" : [ "VerifiablePresentation" ],
  "verifiableCredential" : [ {
    "@context" : [ "https://www.w3.org/2018/credentials/v1" ],
    "credentialSchema" : {
      "id" : "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xb77f8516a965631b4f197ad54c65a9e2f9936ebfb76bae4906d33744dbcc60ba",
      "type" : "FullJsonSchemaValidator2021"
    },
    "credentialSubject" : {
      "currentAddress" : [ "1 Boulevard de la Liberté, 59800 Lille" ],
      "dateOfBirth" : "1993-04-08",
      "familyName" : "DOE",
      "firstName" : "Jane",
      "gender" : "FEMALE",
      "id" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb",
      "nameAndFamilyNameAtBirth" : "Jane DOE",
      "personalIdentifier" : "0904008084H",
      "placeOfBirth" : "LILLE, FRANCE"
    },
    "evidence" : [ {
      "documentPresence" : [ "Physical" ],
      "evidenceDocument" : [ "Passport" ],
      "subjectPresence" : "Physical",
      "type" : [ "DocumentVerification" ],
      "verifier" : "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN"
    } ],
    "id" : "urn:uuid:39f242ee-c97c-4414-8c2a-bc1ca5971f9d",
    "issued" : "2022-08-10T23:38:44.321479468Z",
    "issuer" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb",
    "proof" : {
      "created" : "2022-08-10T23:38:44Z",
      "creator" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb",
      "domain" : "https://api.preprod.ebsi.eu",
      "jws" : "eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFUzI1NksifQ..eJE3rc1SsuNJJFgjzbm7w1Plw4dM7D73BL-43qUzZwCZssJ52wNywobfNgY6qpzyrJdcBtTrqlos2QLkyjM8iA",
      "nonce" : "1daaaf4b-cd7d-4205-ada4-a392ca50cd34",
      "proofPurpose" : "assertion",
      "type" : "EcdsaSecp256k1Signature2019"
    },
    "validFrom" : "2022-08-10T23:38:44.321482223Z",
    "issuanceDate" : "2022-08-10T23:38:44.321482223Z",
    "type" : [ "VerifiableCredential", "VerifiableAttestation", "VerifiableId" ]
  } ],
  "proof" : {
    "type" : "EcdsaSecp256k1Signature2019",
    "creator" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb",
    "created" : "2022-08-15T23:04:23Z",
    "domain" : "https://api.preprod.ebsi.eu",
    "nonce" : "ccb33ea4-694f-49ae-b9dd-eb1efa77bb10",
    "proofPurpose" : "authentication",
    "verificationMethod" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb#z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb",
    "jws" : "eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFUzI1NksifQ..TGFgKOWsrmkhkR5KNsB4oC8EKveua22tjEm1Is9atPunoTyhX59K-jTCS5Jxa1LQlOg9k-mv-IDbTxyy26kZRQ"
  }
}
111799 [DefaultDispatcher-worker-1] DEBUG id.walt.services.keystore.SqlKeyStoreService - Loading key "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb"...
111802 [DefaultDispatcher-worker-1] DEBUG id.walt.services.keystore.SqlKeyStoreService - Loading key "d2c3158e8428462f8eb7186787e030af"...
111802 [DefaultDispatcher-worker-1] DEBUG com.zaxxer.hikari.pool.ProxyConnection - HikariPool-2 - Executed rollback on connection org.sqlite.jdbc4.JDBC4Connection@6d1dcdff due to dirty commit state on close().
111806 [DefaultDispatcher-worker-1] DEBUG id.walt.services.jwt.WaltIdJwtService - Signed JWT:  eyJraWQiOiJkaWQ6a2V5Ono3cjhvcnZpaG5lMXBzVXRFeE5Xa2JyejV1OFpoZEd3TXhkekh6TmszblpMRkNaWHhpaFFUMzhBOXFvR0hkSk1mRzl0UGhteTJaTkE2Nnk4RnFLQ0JiTnptZ2NEYiIsInR5cCI6IkpXVCIsImFsZyI6IkVTMjU2SyJ9.eyJhdWQiOiJodHRwOlwvXC9sb2NhbGhvc3Q6ODA4MFwvYXBpXC9zaW9wXC92ZXJpZnkiLCJzdWIiOiJkaWQ6a2V5Ono3cjhvcnZpaG5lMXBzVXRFeE5Xa2JyejV1OFpoZEd3TXhkekh6TmszblpMRkNaWHhpaFFUMzhBOXFvR0hkSk1mRzl0UGhteTJaTkE2Nnk4RnFLQ0JiTnptZ2NEYiIsImlzcyI6Imh0dHBzOlwvXC9zZWxmLWlzc3VlZC5tZVwvdjIiLCJleHAiOjE2NjA2MDgyNjMsImlhdCI6MTY2MDYwNDY2Mywibm9uY2UiOiJjY2IzM2VhNC02OTRmLTQ5YWUtYjlkZC1lYjFlZmE3N2JiMTAiLCJfdnBfdG9rZW4iOnsicHJlc2VudGF0aW9uX3N1Ym1pc3Npb24iOnsiZGVzY3JpcHRvcl9tYXAiOlt7InBhdGgiOiIkIiwiZm9ybWF0IjoibGRwX3ZwIiwicGF0aF9uZXN0ZWQiOnsicGF0aCI6IiQudmVyaWZpYWJsZUNyZWRlbnRpYWxbMF0iLCJmb3JtYXQiOiJsZHBfdmMiLCJpZCI6IjAifSwiaWQiOiIwIn1dLCJkZWZpbml0aW9uX2lkIjoiMSIsImlkIjoiMSJ9fX0.ZSsqO7FwIpF5PagPzq9icY7-UqUiBYWwL47s8rKu-IndtfdBttNW_v3LQBeFXwgJzGzBbL8sH9se9PvrKkyi2g

id_token: eyJraWQiOiJkaWQ6a2V5Ono3cjhvcnZpaG5lMXBzVXRFeE5Xa2JyejV1OFpoZEd3TXhkekh6TmszblpMRkNaWHhpaFFUMzhBOXFvR0hkSk1mRzl0UGhteTJaTkE2Nnk4RnFLQ0JiTnptZ2NEYiIsInR5cCI6IkpXVCIsImFsZyI6IkVTMjU2SyJ9.eyJhdWQiOiJodHRwOlwvXC9sb2NhbGhvc3Q6ODA4MFwvYXBpXC9zaW9wXC92ZXJpZnkiLCJzdWIiOiJkaWQ6a2V5Ono3cjhvcnZpaG5lMXBzVXRFeE5Xa2JyejV1OFpoZEd3TXhkekh6TmszblpMRkNaWHhpaFFUMzhBOXFvR0hkSk1mRzl0UGhteTJaTkE2Nnk4RnFLQ0JiTnptZ2NEYiIsImlzcyI6Imh0dHBzOlwvXC9zZWxmLWlzc3VlZC5tZVwvdjIiLCJleHAiOjE2NjA2MDgyNjMsImlhdCI6MTY2MDYwNDY2Mywibm9uY2UiOiJjY2IzM2VhNC02OTRmLTQ5YWUtYjlkZC1lYjFlZmE3N2JiMTAiLCJfdnBfdG9rZW4iOnsicHJlc2VudGF0aW9uX3N1Ym1pc3Npb24iOnsiZGVzY3JpcHRvcl9tYXAiOlt7InBhdGgiOiIkIiwiZm9ybWF0IjoibGRwX3ZwIiwicGF0aF9uZXN0ZWQiOnsicGF0aCI6IiQudmVyaWZpYWJsZUNyZWRlbnRpYWxbMF0iLCJmb3JtYXQiOiJsZHBfdmMiLCJpZCI6IjAifSwiaWQiOiIwIn1dLCJkZWZpbml0aW9uX2lkIjoiMSIsImlkIjoiMSJ9fX0.ZSsqO7FwIpF5PagPzq9icY7-UqUiBYWwL47s8rKu-IndtfdBttNW_v3LQBeFXwgJzGzBbL8sH9se9PvrKkyi2g
vp_token: {"@context" : ["https://www.w3.org/2018/credentials/v1"], "holder" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb", "id" : "urn:uuid:40beb1d4-f381-4c85-836f-47753a716b8a", "proof" : {"created" : "2022-08-15T23:04:23Z", "creator" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb", "domain" : "https://api.preprod.ebsi.eu", "jws" : "eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFUzI1NksifQ..TGFgKOWsrmkhkR5KNsB4oC8EKveua22tjEm1Is9atPunoTyhX59K-jTCS5Jxa1LQlOg9k-mv-IDbTxyy26kZRQ", "nonce" : "ccb33ea4-694f-49ae-b9dd-eb1efa77bb10", "proofPurpose" : "authentication", "type" : "EcdsaSecp256k1Signature2019", "verificationMethod" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb#z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb"}, "type" : ["VerifiablePresentation"], "verifiableCredential" : [{"@context" : ["https://www.w3.org/2018/credentials/v1"], "credentialSchema" : {"id" : "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xb77f8516a965631b4f197ad54c65a9e2f9936ebfb76bae4906d33744dbcc60ba", "type" : "FullJsonSchemaValidator2021"}, "credentialSubject" : {"currentAddress" : ["1 Boulevard de la Liberté, 59800 Lille"], "dateOfBirth" : "1993-04-08", "familyName" : "DOE", "firstName" : "Jane", "gender" : "FEMALE", "id" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb", "nameAndFamilyNameAtBirth" : "Jane DOE", "personalIdentifier" : "0904008084H", "placeOfBirth" : "LILLE, FRANCE"}, "evidence" : [{"documentPresence" : ["Physical"], "evidenceDocument" : ["Passport"], "subjectPresence" : "Physical", "type" : ["DocumentVerification"], "verifier" : "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN"}], "id" : "urn:uuid:39f242ee-c97c-4414-8c2a-bc1ca5971f9d", "issued" : "2022-08-10T23:38:44.321479468Z", "issuer" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb", "proof" : {"created" : "2022-08-10T23:38:44Z", "creator" : "did:key:z7r8orvihne1psUtExNWkbrz5u8ZhdGwMxdzHzNk3nZLFCZXxihQT38A9qoGHdJMfG9tPhmy2ZNA66y8FqKCBbNzmgcDb", "domain" : "https://api.preprod.ebsi.eu", "jws" : "eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFUzI1NksifQ..eJE3rc1SsuNJJFgjzbm7w1Plw4dM7D73BL-43qUzZwCZssJ52wNywobfNgY6qpzyrJdcBtTrqlos2QLkyjM8iA", "nonce" : "1daaaf4b-cd7d-4205-ada4-a392ca50cd34", "proofPurpose" : "assertion", "type" : "EcdsaSecp256k1Signature2019"}, "validFrom" : "2022-08-10T23:38:44.321482223Z", "issuanceDate" : "2022-08-10T23:38:44.321482223Z", "type" : ["VerifiableCredential", "VerifiableAttestation", "VerifiableId"]}]}
state:    eyJpZHBTZXNzaW9uSWQiIDogIjViMTI5ZThmLWQwNDktNDU4My04N2IwLTczMzA0NTQxN2Q4NSIsICJpZHBUeXBlIiA6ICJPSURDIn0=
```

# RECV VC

## Get initPassiveIssuance SIOPv2 request
```bash
curl -v 'https://issuer.walt.id/issuer-api/credentials/issuance/request?walletId=walt.id' -X POST -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:103.0) Gecko/20100101 Firefox/103.0' -H 'Accept: application/json, text/plain, */*' -H 'Accept-Language: en-US,en;q=0.5' -H 'Accept-Encoding: gzip, deflate, br' -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWFhYWFhQGJ5b20uZGUifQ.HcJqppZCP39yEyGZq-CaqkFnzDdGmx5kX8j9WQLAP_8' -H 'Content-Type: application/json' -H 'Origin: https://issuer.walt.id' -H 'DNT: 1' -H 'Connection: keep-alive' -H 'Referer: https://issuer.walt.id/' -H 'Cookie: i18n_redirected=en' -H 'Sec-Fetch-Dest: empty' -H 'Sec-Fetch-Mode: cors' -H 'Sec-Fetch-Site: same-origin' -H 'TE: trailers' --data-raw $'{"credentials":[{"credentialData":{"credentialSubject":{"currentAddress":["1 Boulevard de la Libert\xe9, 59800 Lille"],"dateOfBirth":"1993-04-08","familyName":"DOE","firstName":"Jane","gender":"FEMALE","id":"did:ebsi:2AEMAqXWKYMu1JHPAgGcga4dxu7ThgfgN95VyJBJGZbSJUtp","nameAndFamilyNameAtBirth":"Jane DOE","personalIdentifier":"0904008084H","placeOfBirth":"LILLE, FRANCE"}},"schemaId":"https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xb77f8516a965631b4f197ad54c65a9e2f9936ebfb76bae4906d33744dbcc60ba","type":"VerifiableId"}]}'
```

```http request
POST /issuer-api/credentials/issuance/request?walletId=walt.id HTTP/2
Host: issuer.walt.id
user-agent: Mozilla/5.0 (X11; Linux x86_64; rv:103.0) Gecko/20100101 Firefox/103.0
accept: application/json, text/plain, */*
accept-language: en-US,en;q=0.5
accept-encoding: gzip, deflate, br
authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWFhYWFhQGJ5b20uZGUifQ.HcJqppZCP39yEyGZq-CaqkFnzDdGmx5kX8j9WQLAP_8
content-type: application/json
origin: https://issuer.walt.id
connection: keep-alive
referer: https://issuer.walt.id/
cookie: i18n_redirected=en
te: trailers
```

### Response
```text
https://wallet.walt.id/api/wallet/siopv2/initPassiveIssuance/?response_type=id_token&response_mode=post&client_id=https%3A%2F%2Fissuer.walt.id%2Fissuer-api%2Fcredentials%2Fissuance%2Ffulfill&redirect_uri=https%3A%2F%2Fissuer.walt.id%2Fissuer-api%2Fcredentials%2Fissuance%2Ffulfill&scope=openid&nonce=29add0d8-fd53-4d81-8303-906473200f24&claims=%7B%22vp_token%22+%3A+%7B%22presentation_definition%22+%3A+%7B%22format%22+%3A+null%2C+%22id%22+%3A+%221%22%2C+%22input_descriptors%22+%3A+%5B%5D%2C+%22name%22+%3A+null%2C+%22purpose%22+%3A+null%2C+%22submission_requirements%22+%3A+null%7D%7D%7D&state=29add0d8-fd53-4d81-8303-906473200f24
```

```text
curl -v "http://localhost:8091/api/wallet/siopv2/initPassiveIssuance/?response_type=id_token&response_mode=post&client_id=https%3A%2F%2Fissuer.walt.id%2Fissuer-api%2Fcredentials%2Fissuance%2Ffulfill&redirect_uri=https%3A%2F%2Fissuer.walt.id%2Fissuer-api%2Fcredentials%2Fissuance%2Ffulfill&scope=openid&nonce=fc2fe376-ee39-4ccf-9439-fb8557d34042&claims=%7B%22vp_token%22+%3A+%7B%22presentation_definition%22+%3A+%7B%22format%22+%3A+null%2C+%22id%22+%3A+%221%22%2C+%22input_descriptors%22+%3A+%5B%5D%2C+%22name%22+%3A+null%2C+%22purpose%22+%3A+null%2C+%22submission_requirements%22+%3A+null%7D%7D%7D&state=fc2fe376-ee39-4ccf-9439-fb8557d34042"
```
