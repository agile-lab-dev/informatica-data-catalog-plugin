# Informatica Cloud Data Marketplace — API Reference
July 2026 · Published 2026-08-03

© Copyright Informatica LLC 2021, 2026

## Contents
- Preface  (PDF p. 9)
- Part I: Introduction (PDF p. 10)
  - Chapter 1: Introduction (PDF p. 11)
  - Chapter 2: How to call a Data Marketplace API (PDF p. 12)
    - Authenticate using JWT (PDF p. 12)
      - Authentication (PDF p. 12)
      - Send Requests (PDF p. 13)
    - Authenticate using Application Integration (PDF p. 15)
      - Create a system service action (PDF p. 16)
      - Invoke a system service action (PDF p. 19)
      - Download templates to design a system service action (PDF p. 19)
    - Response body and response codes (PDF p. 20)
- Part II: Authenticate using JWT (PDF p. 21)
  - Chapter 3: Define settings for data collections (PDF p. 22)
    - Categories (PDF p. 22)
      - Create categories (PDF p. 23)
      - Retrieve a category (PDF p. 28)
      - Retrieve all categories (PDF p. 31)
      - Modify categories (PDF p. 36)
      - Delete categories (PDF p. 42)
  - Chapter 4: Manage data collections (PDF p. 43)
    - Create data collections (PDF p. 44)
    - Retrieve a data collection (PDF p. 49)
    - Retrieve all data collections (PDF p. 51)
    - Modify data collections (PDF p. 55)
    - Delete data collections (PDF p. 60)
    - Add or remove data assets from data collections (PDF p. 61)
    - Retrieve the data assets of a data collection (PDF p. 63)
    - Retrieve the consumer accesses of a data collection (PDF p. 66)
    - Manage the delivery targets of a data collection (PDF p. 68)
      - Create delivery targets (PDF p. 69)
      - Retrieve a delivery target (PDF p. 74)
      - Retrieve all delivery targets (PDF p. 77)
      - Modify delivery targets (PDF p. 80)
      - Delete delivery targets (PDF p. 84)
  - Chapter 5: Manage orders and consumer accesses (PDF p. 85)
    - Orders (PDF p. 85)
      - Create orders (PDF p. 86)
      - Retrieve an order (PDF p. 92)
      - Retrieve all orders (PDF p. 94)
      - Approve orders (PDF p. 98)
      - Fulfill orders (PDF p. 99)
      - Cancel orders (PDF p. 102)
      - Reject orders (PDF p. 103)
      - Delete orders (PDF p. 104)
    - Consumer accesses (PDF p. 104)
      - Create consumer accesses (PDF p. 105)
      - Retrieve a consumer access (PDF p. 109)
      - Retrieve all consumer accesses (PDF p. 111)
      - Modify custom attributes of consumer accesses (PDF p. 115)
      - Submit consumer access withdrawals (PDF p. 117)
      - Withdraw consumer accesses (PDF p. 118)
      - Cancel consumer access withdrawals (PDF p. 119)
      - Delete consumer accesses (PDF p. 120)
- Part III: Authenticate using Application Integration (PDF p. 121)
  - Chapter 6: Define settings for data collections (PDF p. 122)
    - Categories (PDF p. 122)
      - Create categories (PDF p. 122)
      - Retrieve categories (PDF p. 124)
      - Modify categories (PDF p. 130)
      - Delete categories (PDF p. 133)
    - Delivery formats (PDF p. 134)
      - Create delivery formats (PDF p. 134)
      - Retrieve delivery formats (PDF p. 136)
      - Modify delivery formats (PDF p. 139)
    - Delivery methods (PDF p. 141)
      - Create delivery methods (PDF p. 141)
      - Retrieve delivery methods (PDF p. 143)
      - Modify delivery methods (PDF p. 146)
    - Delivery templates (PDF p. 148)
      - Create delivery templates (PDF p. 148)
      - Retrieve delivery templates (PDF p. 152)
      - Modify delivery templates (PDF p. 158)
    - Terms of use (PDF p. 162)
      - Create terms of use (PDF p. 162)
      - Retrieve terms of use (PDF p. 165)
      - Modify terms of use (PDF p. 168)
      - Update the status of a terms of use (PDF p. 171)
    - Usage types (PDF p. 172)
      - Create usage type (PDF p. 172)
      - Retrieve usage type (PDF p. 174)
      - Modify usage type (PDF p. 177)
      - Update the status of an usage type (PDF p. 180)
    - Cost centers (PDF p. 181)
      - Create cost centers (PDF p. 181)
      - Retrieve cost centers (PDF p. 183)
      - Modify cost centers (PDF p. 186)
  - Chapter 7: Manage Data Marketplace content (PDF p. 189)
    - Data elements (PDF p. 189)
      - Create data elements (PDF p. 189)
      - Retrieve data elements (PDF p. 191)
      - Modify data elements (PDF p. 194)
      - Delete data elements (PDF p. 196)
      - Manage the data quality information of a data element (PDF p. 197)
    - Data assets (PDF p. 200)
      - Create data assets (PDF p. 200)
      - Retrieve data assets (PDF p. 203)
      - Modify data assets (PDF p. 207)
      - Delete data assets (PDF p. 210)
  - Chapter 8: Manage data collections (PDF p. 212)
    - Create data collections (PDF p. 212)
      - Endpoint and method (PDF p. 212)
      - Request (PDF p. 212)
      - Example request (PDF p. 215)
      - Response (PDF p. 216)
    - Retrieve data collections (PDF p. 217)
      - Endpoint and method (PDF p. 217)
      - Request (PDF p. 218)
      - Example request (PDF p. 221)
      - Response (PDF p. 221)
    - Modify data collections (PDF p. 226)
      - Endpoint and method (PDF p. 227)
      - Request (PDF p. 227)
      - Example request (PDF p. 229)
      - Response (PDF p. 230)
    - Delete data collections (PDF p. 231)
      - Endpoint and method (PDF p. 231)
      - Request (PDF p. 232)
      - Example request (PDF p. 232)
      - Response (PDF p. 232)
    - Add or remove data assets from data collections (PDF p. 232)
      - Endpoint and method (PDF p. 232)
      - Request (PDF p. 234)
      - Example request (PDF p. 235)
      - Response (PDF p. 235)
    - Retrieve the data assets of a data collection (PDF p. 235)
      - Endpoint and method (PDF p. 235)
      - Request (PDF p. 236)
      - Example request (PDF p. 236)
      - Response (PDF p. 236)
    - Manage the delivery targets of a data collection (PDF p. 238)
      - Create delivery targets (PDF p. 238)
      - Retrieve delivery targets (PDF p. 241)
      - Modify delivery targets (PDF p. 249)
    - Add or remove terms of use from data collections (PDF p. 251)
      - Endpoint and method (PDF p. 252)
      - Request (PDF p. 253)
      - Example request (PDF p. 253)
      - Response (PDF p. 253)
    - Retrieve the terms of use of a data collection (PDF p. 254)
      - Endpoint and method (PDF p. 254)
      - Request (PDF p. 254)
      - Example request (PDF p. 254)
      - Response (PDF p. 254)
    - Retrieve data collection rating (PDF p. 256)
      - Endpoint and method (PDF p. 256)
      - Request (PDF p. 256)
      - Example request (PDF p. 256)
      - Response (PDF p. 257)
  - Chapter 9: Manage requests, orders and consumer accesses (PDF p. 258)
    - Data collection requests (PDF p. 258)
      - Retrieve data collection requests (PDF p. 258)
      - Reject data collection requests (PDF p. 264)
      - Fulfill data collection requests (PDF p. 265)
      - Cancel data collection requests (PDF p. 266)
      - Delete data collection requests (PDF p. 267)
    - Orders (PDF p. 268)
      - Retrieve orders (PDF p. 268)
      - Approve orders (PDF p. 280)
      - Reject orders (PDF p. 285)
      - Fulfill orders (PDF p. 288)
      - Cancel orders (PDF p. 299)
      - Delete orders (PDF p. 301)
    - Consumer accesses (PDF p. 302)
      - Create consumer accesses (PDF p. 302)
      - Modify custom attributes of consumer accesses (PDF p. 306)
      - Retrieve consumer accesses (PDF p. 315)
      - Make consumer accesses available (PDF p. 326)
      - Submit consumer access withdrawals (PDF p. 334)
      - Withdraw consumer accesses (PDF p. 343)
      - Delete consumer accesses (PDF p. 351)
  - Chapter 10: Custom attributes (PDF p. 353)
    - Retrieve custom attributes (PDF p. 353)
      - Endpoint and method (PDF p. 353)
      - Request (PDF p. 354)
      - Example request (PDF p. 354)
      - Response (PDF p. 354)
  - Chapter 11: Collaboration on objects (PDF p. 359)
    - Comment on an object (PDF p. 359)
      - Endpoint and method (PDF p. 359)
      - Request (PDF p. 360)
      - Example request (PDF p. 360)
      - Response (PDF p. 360)
    - Retrieve comments on an object (PDF p. 360)
      - Endpoint and method (PDF p. 361)
      - Request (PDF p. 361)
      - Example request (PDF p. 361)
      - Response (PDF p. 361)
  - Chapter 12: Data Marketplace customizations (PDF p. 363)
    - Retrieve customizations (PDF p. 363)
      - Endpoint and method (PDF p. 363)
      - Request (PDF p. 363)
      - Example request (PDF p. 363)
      - Response (PDF p. 363)
    - Update customizations (PDF p. 364)
      - Endpoint and method (PDF p. 364)
      - Request (PDF p. 365)
      - Example request (PDF p. 365)
      - Response (PDF p. 365)
    - Reset customizations (PDF p. 366)
      - Endpoint and method (PDF p. 366)
      - Request (PDF p. 366)
      - Example request (PDF p. 366)
      - Response (PDF p. 366)
  - Chapter 13: Technical content (PDF p. 368)
    - Retrieve the technical content of an object (PDF p. 368)
      - Endpoint and method (PDF p. 368)
      - Request (PDF p. 368)
      - Example request (PDF p. 369)
      - Response (PDF p. 369)
    - Update the technical content of an object (PDF p. 369)
      - Endpoint and method (PDF p. 369)
      - Request (PDF p. 370)
      - Example request (PDF p. 370)
      - Response (PDF p. 370)
- Part IV: Frequently Asked Questions (PDF p. 371)
  - Chapter 14: Frequently Asked Questions (PDF p. 372)
    - When to authenticate with JWT and when with Application Integration? (PDF p. 372)
    - Can I use data360 endpoints to design an Application Integration process? (PDF p. 372)
    - How do I retrieve the system-generated unique identifier of a Data Marketplace item? (PDF p. 373)
    - How do I retrieve the system-generated unique identifier of a user account or user group? (PDF p. 376)
    - How do I retrieve the system-generated unique identifier of an asset group or user role? (PDF p. 376)

# Preface
Read _API Reference_ to create and manage assets in Data Marketplace using the APIs that Informatica provides. 

This help is written for developers and administrators who are responsible for implementing Data Marketplace. This help assumes that you have a basic knowledge of how to use Data Marketplace and Informatica® Intelligent Data Management Cloud™ (IDMC) Application Integration. It also assumes that you understand JSON and API programming techniques. 

**Note:** If some of the terms that are used on this help differ from what is displayed on the Data Marketplace interface, contact your Data Marketplace Administrator to understand the terminology defined for your organization. For more information, see the _Label Customizations_ topic in the _Set Up Data Marketplace_ help.

# Part I: Introduction
This part contains the following chapters: 

- Introduction, 11 

- How to call a Data Marketplace API, 12

## Chapter 1: Introduction
Data Marketplace APIs enable you to customize Data Marketplace, and also create, manage, and retrieve Data Marketplace items.

## Chapter 2: How to call a Data Marketplace API
Use a REST client, the cURL tool, or a suitable programming interface to call Data Marketplace APIs. You can also use Application Integration to make API calls to Data Marketplace. 

### Authenticate using JWT
Authenticate yourself with JSON Web token authentication to call an API. 

**Note:** When you use JWT authentication to call an API, consider the following: 

- The number of items that you can create or modify per API call depends on the cloud provider where the IDMC POD is hosted. The following table shows the number of items that you can create or modify per API call on each cloud provider: 

|**Cloud provider**|**Number of items**|
|---|---|
|Amazon Web Services|30|
|Microsoft Azure|10|


- You can call a maximum of 120 APIs per minute and 10,000 APIs per day. 

#### Authentication
Before you make REST API calls, you must authenticate yourself with JWT authentication. First, use the Login API with your organization user name and password to receive the session ID and org ID. Then, use the session ID and org ID to generate a JSON Web (JW) token. Subsequently, you use the JW token to secure your API calls without having to authenticate yourself for each call. 

#### Obtain the session ID
To get a session ID, use the Login API V1. The Login API is also available with SAML. For more information about logging in using SAML, see the _REST API Reference_ in the Administrator help. Ensure that you have your IDMC user name and password. 

Send the POST request with the following URI, header, and request body.

The following table describes the components of the POST request: 

**Component Description** URI **<LoginURL>/identity-service/api/v1/Login** Note that `<LoginURL>` is the IDMC login URL based on your region, such as https://dm- **us** .informaticacloud.com, https://dm- **em** .informaticacloud.com, https://dm- **ap** .informaticacloud.com, https://dm- **uk** .informaticacloud.com. Header `Content-Type: application/json` Request Body `{ "username": "<your_IICS_user>", "password": "<your_IICS_password>", }` 

The response generates the session ID and the org ID. Note the value of the `sessionId` field to include in the subsequent API call. 

In Administrator, session ID is specified as the default authentication method. This authentication involves generating a session ID and then using the session ID value to generate the JW token. The organization administrator can switch from session ID-based authentication to JW token-based authentication. 

#### Generate a JW Access Token
Use the value from the `sessionId` field that you obtained from the previous Login API response, and send the POST request with the following URL and headers to generate a JW access token. 

The following table describes the components of the POST request: 

|**Component**|**Description**|
|---|---|
|URI|**<LoginURL>/identity-service/api/v1/jwt/Token?client_id=idmc_api&nonce=1234**|
||Note that `<LoginURL>` is the IDMC login URL based on the region, such as https://dm-**us**.informaticacloud.com, https://dm-**em**.informaticacloud.com, https://dm-**ap**.informaticacloud.com,<br>https://dm-**uk**.informaticacloud.com, and so on.|
|Headers|`IDS-SESSION-ID: <sessionId value>`|


The response generates the JW access token that you include in the authorization header for all subsequent API calls. The access token expires after 30 minutes from the initial grant of the token. You must regenerate the access token after every 30 minutes to authenticate your API calls. 

**Note:** If the administrator configures JW token-based authentication in Administrator, the login API response includes the JW token value in the `session ID` field. 

#### Send Requests
When you make an API call in the REST client, the request is sent in JSON format. Depending on the request, the REST client receives a response in JSON format that contains the information for the request that was sent. 

To make an API call, enter the URL to access the API, header requests, methods, and request parameters. 


#### URL
To call a Data Marketplace API, use the following format: 

```
<CDMP_URL><endpoint>
```

For example, consider the following URL: 

```
https://{{CDMP_URL}}/api/v2/delivery-targets
```

Here, `https://{{CDMP_URL}}/` is the base URL and `/api/v2/delivery-targets` is the API endpoint. 

The base URL varies based on your region. The following table shows the regions and their corresponding base URLs: 

|**Region**|**Base URL**|
|---|---|
|United States of America|https://idmc-api.dm-us.informaticacloud.com/data360/marketplace/|
|United Kingdom|https://idmc-api.dm-uk.informaticacloud.com/data360/marketplace/|
|Canada|https://idmc-api.dm-na.informaticacloud.com/data360/marketplace/|
|Europe, Middle East, Africa (EMEA)|https://idmc-api.dm-em.informaticacloud.com/data360/marketplace/|
|Asia, Pacific|https://idmc-api.dm-ap.informaticacloud.com/data360/marketplace/|
|Japan|https://idmc-api.dm-apne.informaticacloud.com/data360/<br>marketplace/|


If you want to use Application Integration to call a Data Marketplace API, you must use a different base URL. For more information, see “Authenticate using Application Integration” on page 15. 

#### Headers
The following table describes the header request that you use to send the Data Marketplace API requests: 

|**Header**|**Description**|
|---|---|
|`Content-Type:application/json`|This header is required for the POST and PATCH methods. It indicates that the<br>client is sending a JSON request.|
|`Authorization:Bearer`<br>`<jwt_token>`|This header is required to secure all your API requests.|
|`X-INFA-ORG-ID:<Org ID>`|Indicates the user's organization to which the API requests is sent.<br>The org ID is obtained from the Login API.|
|`IDS-SESSION-ID`|Optionally, you can use this header for the GET method.|

### Authenticate using Application Integration
Use an Application Integration system service action to call a Data Marketplace API. 

#### **What is Application Integration?**
Application Integration is an event-driven and service-oriented IDMC offering that encompasses event processing, service orchestration, and process management. 

#### **How to use Application Integration to call a Data Marketplace API**
The following image shows the overall process of using Application Integration to call a Data Marketplace API: 


<!-- Start of picture text -->
, — [==aemanes<br>—<br>1 |eatntronaeeSete REESE<br>eeseaannConfigure pr o cesspa i n putepe<br>see<br>SSS ©<br>8 ees ee 8 —<br>A [EE i<br>i ee tee i io<br>a<S———I A= | cicadaoo<br>ecess ouput<br>a ae gome<br>[seatConfigue —|<br>©<br>Publish process<br><!-- End of picture text -->

To call a Data Marketplace API via Application Integration, perform the following steps: 

1. In Application Integration, create an `Execute Data Marketplace API` type system service action. 

2. Invoke the system service action that you created to call the Data Marketplace API. 

**Note:** You require Informatica Processing Units (IPUs) to use Application Integration. For more information about how to calculate your IPU usage, see https://network.informatica.com/docs/DOC-19140. 


#### **How can I retrieve the base URL?**
Consider following sample URL: 

```
https://{{CDMP_URL}}/api/v1/integration/provisioning/deliveryTemplates?status=ACTIVE
```

Here, `https://{{CDMP_URL}}/` is the base URL and `/api/v1/integration/provisioning/ deliveryTemplates` is the API endpoint. 

The base URL varies based on your region. The following table shows the regions and their corresponding base URLs: 

|**Region**|**Base URL**|
|---|---|
|United States of America|https://cdgc-api.dm-us.informaticacloud.com/cdmp-marketplace/|
|United Kingdom|https://cdgc-api.dm-uk.informaticacloud.com/cdmp-marketplace/|
|Canada|https://cdgc-api.dm-na.informaticacloud.com/cdmp-marketplace/|
|Europe, Middle East, Africa (EMEA)|https://cdgc-api.dm-em.informaticacloud.com/cdmp-marketplace/|
|Asia, Pacific|https://cdgc-api.dm-ap.informaticacloud.com/cdmp-marketplace/|
|Japan|https://cdgc-api.dm-apne.informaticacloud.com/cdmp-marketplace/|


If you want to use JWT authentication to call a Data Marketplace API, you must use a different base URL. For more information, see “Send Requests” on page 13. 

**Note:** When you use an Application Integration process to invoke an API, consider the following: 

- The number of items that you can create or modify per API call depends on the cloud provider where the IDMC POD is hosted. The following table shows the number of items that you can create or modify per API call on each cloud provider: 

|**Cloud provider**|**Number of items**|
|---|---|
|Amazon Web Services|30|
|Microsoft Azure|10|


- You can call a maximum of 100 APIs per minute. 

#### Create a system service action
Before you can call a Data Marketplace API, you must design an `Execute Data Marketplace API` type system service action in Application Integration. 

#### Start properties
In Application Integration, define the binding type and access details for the system service action that you want to create.

The following table describes how to configure the **Start** properties for a system service action to call a Data Marketplace API: 

|**Property**|**Description**|
|---|---|
|Binding|If you want to run the process by using a service URL, select` REST/SOAP` as the binding type|
|Allowed Groups|Specify which user groups can access the process service URL at run time.|
|Allowed Users|Specify which users can access the process service URL at run time.|
|Allow anonymous|Ensure that you do not select the**Allow anonymous access**property.|
|access|If you select**Allow anonymous access**, you cannot call Data Marketplace APIs.|


For more information about the **Start** properties, see the _Start Properties_ topic in the _Design_ help in Application Integration. 

#### Input Field properties
Define the service properties and input properties for the system service action that you want to create. 

The following table describes how to configure the **Service** properties for a system service action to call a Data Marketplace API: 

|**Property**|**Description**|
|---|---|
|Service Type|Select` System Service` as the service type.|
|Action|Select` Execute Data Marketplace API` as the action.|


The following table describes how to configure the **Input Field** properties for a system service action to call a Data Marketplace API: 

|**Property**|**Description**|
|---|---|
|HTTP<br>Method|Enter one of the following methods to invoke the Data Marketplace API:<br>-<br>`GET `<br>-<br>` PUT `<br>-<br>` PATCH `<br>-<br>` POST`|
|Relative Path|Enter the relative path of the API that you want to invoke.<br>You can use an API endpoint to discover the relative path. Consider the following examples:<br>- For the API endpoint`/api/v1/integration/categories`, the relative path is` categories`.<br>- For the API endpoint`/api/v1/integration/provisioning/deliveryFormats`, the relative<br>path is` provisioning/deliveryFormats`.<br>- For the API endpoint`/api/v1/integration/orders/<orderId>/fulfill `, the relative path is<br>` orders/<orderId>/fulfill`.|


#### **Query parameter**
If the Data Marketplace API that you want to invoke requires you to specify query parameters, you can enter the query parameters in XML format. 


The following example shows how you must enter the query parameters: 

```
<DataMarketplaceQueryParameters>
<queryParams>
<name>deliveryTargetId</name>
<value>6ddc722a-a4e3-4738-8880-913af86204b2</value>
</queryParams>
<queryParams>
<name>comment</name>
<value><p>Fulfilled</p></value>
</queryParams>
</DataMarketplaceQueryParameters>
```

#### **Path variables**
If the Data Marketplace API that you want to invoke requires you to specify path variables, you can enter the path variables in XML format. 

The following example shows how you must enter the path variables: 

```
<DataMarketplacePathVariables>
<pathVariables>
<name>orderId</name>
<value>6ddc722a-a4e3-4738-8880-913af86204b2</value>
</pathVariables>
</DataMarketplacePathVariables>
```

#### **Payload**
If an API call requires a payload, enter the payload information within single quotation marks. 

The following example shows how you must enter the payload information for an API call: 

```
‘{
  "items": [
    {
      "refId": "3d0af52d-4ed1-44e2-9e09-ea9e3a77baaa",
      "name": "CreateUsageContext1",
      "description": "desc",
      "status": "ACTIVE"
    },
 {
      "refId": "3d0-jsnbj-52d-4ed1-44e2-9e09-ea9e3a77a",
      "name": "CreateUsageContext2",
      "description": "desc",
      "status": "INACTIVE"
    }
]
}’
```

For more information about the **Input Field** properties, see the _Input Field Properties_ topic in the _Design_ help in Application Integration. 

#### Output Field properties
In the **Start** step of a new process in Application Integration, you can define the properties of the output fields.

The following table describes how to configure the **Output Field** properties for a system service action to call a Data Marketplace API: 

|**Property**|**Description**|
|---|---|
|Output Format|Specify whether you want the output field to represent one or more fields of the response, or the<br>entire contents of the response.<br>Select on of the following output formats:<br>- If you want the output field to represent one or more fields of the response, select the` Fields`<br>output format.<br>- If you want the output field to represent the entire contents of the response, select the` Whole `<br>` Payload` output format.<br>To call a Data Marketplace, Informatica recommends that you select the` Fields` output format.|
|Name|Enter the name of the output field.|
|Type|Specify the type of the output field.<br>Select on of the following field types:<br>-<br>`Date `<br>-<br>` Date Time `<br>-<br>` Time `<br>-<br>` Integer `<br>-<br>` Text`<br>If the preceding types don't meet your requirements, select the` More types ` option to reveal<br>additional field types. In the**Edit Type**dialog box, you can select a field type that is listed under one<br>of the following category:<br>-<br>` Simple Types `<br>-<br>` Custom Types `<br>-<br>` Connection defined Types`|
|Description|Enter the description for the output field.|
|Initial Value|Enter the initial value for the output field.|


For more information about the **Output Field** properties, see the _Output Field Properties_ topic in the _Design_ help in Application Integration. 

Before you can configure the API response in an **Assignment** step or a **Decision** step, ensure that you have defined the output field properties of the process in the **Start** step. For more information about an **Assignment** step or a **Decision** step, see _Adding Process Steps_ topic in the _Design_ help in Application Integration. 

For more information about how you can design system service action, see the _Execute Data Marketplace API_ topic in the _Design_ help in Application Integration. 

#### Invoke a system service action
To make a REST API call, invoke the `Execute Data Marketplace API` system service action that you designed for the Data Marketplace API. 

For more information, see the _Invoke_ help in Application Integration. 

#### Download templates to design a system service action
Informatica provides templates that you can use to design a system service action. 

For more information, see the Knowledge Base article 000198575. 


### Response body and response codes
When you invoke an API, the response returns one of the standard HTTP response codes with information about the success or failure of the API call. 

#### Response body
The REST API sends a response header and response body in JSON format with information about the success or failure of the REST API call. 

#### Response codes
The following table describes the standard response codes: 

|**Response Code**|**Description**|
|---|---|
|200 OK|The request is completed successfully.|
|201 Created|The request is completed and the new resource is created successfully.|
|202 Accepted|The request is accepted for processing, but is incomplete.|
|204|No content to send back.|
|400 Bad Request|The request is unprocessed because it contains missing or invalid information.|
|401 Unauthorized|The request is unauthorized. The authentication might be missing or invalid.|
|403 Forbidden|The user is unauthorized to perform this request.|
|404 Not Found|The request includes a resource URL that doesn't exist.|
|405 Method Not Allowed|The HTTP method specified in the request is unsupported for this request.|
|429 Too Many Requests|The user sent too many requests in a given amount of time and reached the API rate limit.|
|500 Internal Server Error|The server encountered an error because of which the request is unfulfilled.|

# Part II: Authenticate using JWT
Use a REST client, the cURL tool, or a suitable programming interface to call Data Marketplace APIs.

## Chapter 3: Define settings for data collections
Use REST APIs to create and manage various items that are used with data collections in Data Marketplace. This includes categories that contain the data collections, terms of use for data collections, usage types to certify the data collections, and so on. 

The following table lists the items that you can create and manage: 

|**Items**|**Topic**|**Authentication Method**|
|---|---|---|
|Category|See “Categories” on page 22.|JWT|
|Delivery format|See “Delivery formats” on page 134.|Application Integration|
|Delivery method|See “Delivery methods” on page 141.|Application Integration|
|Delivery template|See “Delivery templates” on page 148.|Application Integration|
|Usage type|See “Usage types” on page 172.|Application Integration|
|Terms of use|See “Terms of use” on page 162.|Application Integration|
|Cost center|See “Cost centers” on page 181.|Application Integration|


### Categories
A category is a grouping of related data collections. It is a predefined classification where stakeholders can publish the data collections for which they are responsible.

#### Create categories
Use a REST API to create categories in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/categories**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`parentCategoryId `|Optional. Enter the system-generated unique<br>identifier of the parent category for the<br>category that you want to create.<br>If the category that you want to create isn't a<br>subcategory to another category, you can leave<br>this parameter empty.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a category, see<br>“Retrieve all categories” on page 31.<br>To get the system-generated<br>unique identifier of a category<br>from the Data Marketplace user<br>interface, open the category. The<br>category page's URL contains the<br>system-generated unique<br>identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-`<br>`a1e412c1ce13&dtn=_AfterEB `<br>` F%20may20 `, the system-generated unique identifier is<br>` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|`externalId`|Optional. Enter a reference identifier for the<br>category.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique<br>value to the item. The reference<br>identifier that Data Marketplace<br>automatically generates contains<br>a prefix. The administrator can<br>specify the prefix of the<br>automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a<br>unique value. Ensure that you<br>don't use the prefix value that is<br>configured in Metadata<br>Command Center.|
|`name `|Required. Enter a name for the category.|Ensure that you enter a unique<br>value.<br>Data Marketplace doesn't<br>consider the letter case when it<br>verifies the uniqueness of the<br>` name` parameter's value. For<br>example, if you try to name a<br>category as "Retail Department"<br>while a category called "Retail<br>department" already exists, the<br>API call fails.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`description`|Required. Enter a description for the category.|-|
|`status`|Required. Specify the status of the category.<br>The status determines whether the category is<br>discoverable and usable in Data Marketplace.<br>Enter one of the following values:<br>- To make the category discoverable and<br>usable in Data Marketplace, enter` ACTIVE `.<br>- To make the category not discoverable and<br>unusable in Data Marketplace, enter<br>` INACTIVE`.|-|
|`assetGroups`|Optional. Enter the system-generated unique<br>identifier of the asset group for the category.|Asset groups enable you to<br>control access to the category,<br>the subcategories and data<br>collections within it.<br>For more information about<br>asset groups, see the_Implement_<br>_access controls on metadata_<br>topic in the_Set Up Data_<br>_Marketplace_help.<br>To get the system-generated<br>unique identifier of an asset<br>group, click**My Services >**<br>**Metadata Command Center >**<br>**Customize**. On the**Customize**<br>page, select the**Asset Groups**<br>tab. On the**Asset Groups**tab,<br>click an asset group. The user<br>role page's URL contains the<br>unique identifier.<br>For example, in the URL`/assetGroup/`<br>`07a3ef9c-9410-421f-9b21-6 `<br>` 4ec9ac9778f `, the unique<br>identifier is<br>` 07a3ef9c-9410-421f-9b21-6 `<br>` 4ec9ac9778f`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`stakeholdership >`<br>`stakeholderId`|Optional. Enter the system-generated unique<br>identifier of the user account or user group<br>that you want to assign as a stakeholder on the<br>category.|- To get the system-generated<br>unique identifier of a user<br>account, click**My Services >**<br>**Administrator > Users**. On the<br>**Users**page, click a user<br>account. The user account<br>page's URL contains the<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/usersAsset/`<br>`0LH3xBJC9A6haZ26htGZyT `,<br>the unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system-generated<br>unique identifier of a user<br>group, click**My Services >**<br>**Administrator > User Groups**.<br>On the**User Groups**page, click<br>a user group. The user group<br>page's URL contains the<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userGroupsAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `,<br>the unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|
|`stakeholdership >`<br>`roleId `|Optional. Enter the system-generated unique<br>identifier of the user role that is assigned to<br>the stakeholder that you specified in the<br>` stakeholderId ` parameter.|If you specify a value for the<br>` stakeholderId` parameter,<br>ensure that you also specify a<br>value for the` roleId` parameter.<br>To get the system-generated<br>unique identifier of a user role,<br>click**My Services >**<br>**Administrator > User Roles**. On<br>the**User Roles**page, click a user<br>role. The user role page's URL<br>contains the unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userRolesAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `, the<br>unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|


#### Example request
The following example shows how you can use an API to create a category: 

```
{
```

> `"parentCategoryId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",` 

> `"externalId": "CAT-1",` 

```
  "name": "Sales",
```

`"description": "Data collections related to sales made by the organization.", "status": "INACTIVE", "assetGroups": [ "b93c4539-a93e-440d-a4b4-95d69b8911e6" ], "stakeholdership": [ { "stakeholderId": "jqPFaKmJRGhdfJ35eWUzoW", "roleId": "5Qvy6Uiiq9IjoocWeJhccW" } ] }` Response 

When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a category: 

```
{
  "externalId": "CAT-123",
  "id": "2593524d-82f7-4dfd-bc6b-17088c046f9d",
  "name": "Sales",
  "description": "Data collections related to sales made by the organization.",
  "status": "INACTIVE",
  "effectiveStatus": "INACTIVE",
  "assetGroups": [
    {
      "name": "ASIA",
      "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
      "isInherited": false
    }
  ]
}
```

The following table describes the parameters of each category that is created: 

|**Parameter**|**Description**|
|---|---|
|`externalId`|Reference identifier of the category.|
|`id`|System-generated unique identifier of the category.|
|`name`|Name of the category.|
|`description`|Description of the category.|
|`status `|Status of the category. A category can have one of the following statuses:<br>-<br>` ACTIVE `<br>-<br>` INACTIVE`|
|`effectiveStatus `|Indicates whether the category is available in Data Marketplace. A category can have<br>one of the following statuses:<br>-<br>` ACTIVE `. The category is available.<br>-<br>` INACTIVE`. The category is unavailable.|
|`assetGroups`|Details of the asset group that is assigned to the category.|
|`assetGroups > id`|System-generated identifier of the asset group.|


|**Parameter**|**Description**|
|---|---|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset<br>group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


#### Retrieve a category
Use a REST API to retrieve the details of an individual category in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/categories/<categoryId>**|
||`<categoryID>`: Required. Enter the system-generated unique identifier of the category for which you<br>want to retrieve the details.|
||To get the system-generated unique identifier of a category from the Data Marketplace user interface,<br>open the category. The category page's URL contains the system-generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/category/view?ac=67417f72-e5ab-44f0-add9-a1e412c1ce13&dtn=_AfterEBF%20may20`, the system-generated unique identifier is` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12.

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details about the<br>category.<br>-<br>` parentCategory `. Returns the details about<br>the parent category that contains the<br>category.<br>-<br>` systemAttributes `. Returns the details<br>about the category's creation and<br>modification, such as the details of the user<br>that created the category, the latest user<br>that modified the category, and so on.<br>-<br>` stakeholdership`. Returns the type of user<br>and role that are assigned as stakeholder on<br>the category.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the category<br>such as name, description and so<br>on.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of a category: 

```
https://{{CDMP_URL}}/api/v2/categories/2593524d-82f7-4dfd-bc6b-17088c046f9d?segments=all
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a category: 

```
{
  "externalId": "CAT-123",
  "id": "2593524d-82f7-4dfd-bc6b-17088c046f9d",
  "parentId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "name": "Sales",
  "description": "Data collections related to sales made by the organization.",
  "status": "INACTIVE",
  "effectiveStatus": "INACTIVE",
  "parentCategory": {
    "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
    "externalId": "CAT-001",
    "name": "Financials",
    "description": "Data collections related to financial operations.",
    "status": "ACTIVE",
    "effectiveStatus": "ACTIVE",
    "systemAttributes": {
      "createdBy": "analyst@company.com",
      "createdOn": "2025-08-11T10:30:00Z",
      "modifiedBy": "dataowner@company.com",
      "modifiedOn": "2025-08-11T15:45:00Z"
    },
    "href": "/categories/b93c4539-a93e-440d-a4b4-95d69b8911e6"
  },
  "stakeholdership": [
    {
      "stakeholderId": "jqPFaKmJRGhdfJ35eWUzoW",
      "userType": "USER",
```


```
      "roleId": "5Qvy6Uiiq9IjoocWeJhccW",
      "isInherited": false
    }
  ],
  "assetGroups": [
    {
      "id": "4795b4ac-94a6-413b-9349-64cf455d5abb",
      "name": "USA",
      "isInherited": false
    }
  ],
  "systemAttributes": {
    "createdBy": "jaXM6NrqsKXffdwg2NkTMR",
    "createdOn": "2025-08-11T10:30:00Z",
    "modifiedBy": "jaXM6NrqsKXffdwg2NkTMR",
    "modifiedOn": "2025-08-11T15:45:00Z"
  }
}
```

The following table describes the parameters of the category that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`externalId`|Reference identifier of the category.|
|`id`|System-generated unique identifier of the category.|
|`parentId`|System-generated unique identifier of the parent category.|
|`name`|Name of the category.|
|`description`|Description of the category.|
|`status `|Status of the category as configured by a stakeholder of the category.<br>A category can have one of the following statuses:<br>-<br>` ACTIVE `<br>-<br>` INACTIVE`|
|`effectiveStatus`|The effective status of the category indicates whether a category is discoverable and<br>usable in Data Marketplace.<br>The effective status of a category is influenced by the effective status of its parent<br>category. For example, if the value of the` status` parameter of a category is` Active `<br>but the category is added to an effectively inactive parent category, the category<br>becomes inactive.<br>A category can have one of the following statuses:<br>-<br>` ACTIVE `. The category is discoverable and usable in Data Marketplace.<br>-<br>` INACTIVE`. The category isn't discoverable and usable in Data Marketplace.|
|`parentCategory`|Details of the parent category.|
|`stakeholdership`|Details of the stakeholders of the category.|
|`stakeholdership >`<br>`stakeholderId`|System-generated identifier of the user account or user group that is assigned as a<br>stakeholder of the category.|
|`stakeholdership >`<br>`userType `|Indicates whether the stakeholder is an individual user account or user group.<br>This parameter can have one of the following values:<br>-<br>` USER `. The stakeholder is an individual user account.<br>-<br>` USER-GROUP`. The stakeholder is a user group.|

|**Parameter**|**Description**|
|---|---|
|`stakeholdership >`<br>`roleId`|System-generated identifier of the user role that is assigned to the stakeholder of the<br>category.|
|`stakeholdership >`<br>`isInherited `|Indicates whether the stakeholder is directly assigned to the category or if the<br>stakeholder is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The stakeholder is inherited from the category hierarchy.<br>-<br>` false`. The stakeholder is directly assigned to the category.|
|`assetGroups`|Details of the asset group that is assigned to the category.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset<br>group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|
|`systemAttributes >`<br>`createdBy`|System-generated identifier of the user account that created the category.|
|`systemAttributes >`<br>`createdOn`|Date when the category was created.|
|`systemAttributes >`<br>`modifiedBy`|System-generated identifier of the latest user account that modified the category.|
|`systemAttributes >`<br>`modifiedOn`|Latest date when the category was modified.|


#### Retrieve all categories
Use a REST API to retrieve the details of all categories in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/categories**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details of each category<br>that is retrieved.<br>-<br>` parentCategory `. Returns the details of the<br>parent category that contains a category.<br>-<br>` systemAttributes `. Returns the details<br>about a category's creation and<br>modification, such as the details of the user<br>that created the category, the latest user<br>that modified the category, and so on.<br>-<br>` stakeholdership`. Returns the type of user<br>and role that are assigned as stakeholder on<br>a category.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the category<br>such as name, description and so<br>on.|
|`search`|Optional. Enter the search term to find a<br>category by name or description.|Default is an asterisk (*) which<br>retrieves all categories. To<br>search by name, enter a term<br>that doesn't contain an asterisk.|
|`offset`|Optional. Specify the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Specify the maximum number of<br>results that are displayed on a page.|Default is` 20`.<br>Maximum value is` 200`.|
|`sortby `|Optional. Specify the parameters to sort the<br>search results.<br>Enter one of the following values:<br>-<br>` id `<br>-<br>` name `<br>-<br>` status `<br>-<br>` createdBy `<br>-<br>` createdOn `<br>-<br>` modifiedBy `<br>-<br>` modifiedOn`|Default is` modifiedOn`.|
|`sortOrder`|Optional. Set the sorting order of the search<br>results.<br>Enter one of the following values:<br>- To sort the search results by ascending<br>order, enter` asc`.<br>- To sort the search results by descending<br>order, enter` desc`.|Default is` desc`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of all categories: 

```
https://{{CDMP_URL}}/api/v2/categories?segments=all
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of all categories: 

```
{
  "pageInfo": {
    "offset": 40,
    "limit": 20,
    "totalCount": 120
  },
  "links": {
    "self": {
      "href": "/categories?segments=all&offset=40&limit=20"
    },
    "first": {
      "href": "/categories?segments=all&offset=0&limit=20"
    },
    "next": {
      "href": "/categories?segments=all&offset=60&limit=20"
    },
    "previous": {
      "href": "/categories?segments=all&offset=20&limit=20"
    },
    "last": {
      "href": "/categories?segments=all&offset=100&limit=20"
    }
  },
  "items": [
    {
      "externalId": "CAT-123",
      "id": "2593524d-82f7-4dfd-bc6b-17088c046f9d",
      "parentId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
      "name": "Sales",
      "description": "Data collections related to sales made by the organization.",
      "status": "INACTIVE",
      "effectiveStatus": "INACTIVE",
      "parentCategory": {
        "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
        "externalId": "CAT-001",
        "name": "Financials",
        "description": "Data collections related to financial operations.",
        "status": "ACTIVE",
        "effectiveStatus": "ACTIVE",
        "systemAttributes": {
          "createdBy": "analyst@company.com",
          "createdOn": "2025-08-11T10:30:00Z",
          "modifiedBy": "dataowner@company.com",
          "modifiedOn": "2025-08-11T15:45:00Z"
        },
        "href": "/categories/b93c4539-a93e-440d-a4b4-95d69b8911e6"
      },
      "stakeholdership": [
        {
          "stakeholderId": "jqPFaKmJRGhdfJ35eWUzoW",
          "userType": "USER",
          "roleId": "5Qvy6Uiiq9IjoocWeJhccW",
          "isInherited": false
        }
      ],
      "assetGroups": [
        {
          "id": "4795b4ac-94a6-413b-9349-64cf455d5abb",
          "name": "USA",
          "isInherited": false
        }
      ],
      "systemAttributes": {
```


```
        "createdBy": "jaXM6NrqsKXffdwg2NkTMR",
        "createdOn": "2025-08-11T10:30:00Z",
        "modifiedBy": "jaXM6NrqsKXffdwg2NkTMR",
        "modifiedOn": "2025-08-11T15:45:00Z"
      }
    }
  ]
}
```

The following table describes the parameters of each category that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`pageInfo > offset`|Starting index of the paginated results.|
|`pageInfo > limit`|The maximum number of results that are displayed on a page.|
|`pageInfo > totalCount`|Number of categories retrieved.|
|`links > self > href`|The API that was invoked to generate this response.|
|`links > first > href`|The API endpoint that you can use to retrieve the search results that are displayed<br>on the first page.|
|`links > next > href`|The API endpoint that you can use to retrieve the search results from the page<br>following the one you currently have open.|
|`links > previous > href`|The API endpoint that you can use to retrieve the search results from the page<br>prior to the one you currently have open.|
|`links > last > href`|The API endpoint that you can use to retrieve the search results that are displayed<br>on the last page.|
|`externalId`|Reference identifier of the category.|
|`id`|System-generated unique identifier of the category.|
|`parentId`|System-generated unique identifier of the parent category.|
|`name`|Name of the category.|
|`description`|Description of the category.|
|`status `|Status of the category as configured by a stakeholder of the category.<br>A category can have one of the following statuses:<br>-<br>` ACTIVE `<br>-<br>` INACTIVE`|
|`effectiveStatus`|The effective status of the category indicates whether a category is discoverable<br>and usable in Data Marketplace.<br>The effective status of a category is influenced by the effective status of its parent<br>category. For example, if the value of the` status ` parameter of a category is<br>` Active ` but the category is added to an effectively inactive parent category, the<br>category becomes inactive.<br>A category can have one of the following statuses:<br>-<br>` ACTIVE `. The category is discoverable and usable in Data Marketplace.<br>-<br>` INACTIVE`. The category isn't discoverable and usable in Data Marketplace.|

|**Parameter**|**Description**|
|---|---|
|`parentCategory`|Details of the parent category.|
|`stakeholdership`|Details of the stakeholders of the category.|
|`stakeholdership >`<br>`stakeholderId`|System-generated identifier of the user account or user group that is assigned as a<br>stakeholder on the category.|
|`stakeholdership >`<br>`userType `|Indicates whether the stakeholder is an individual user account or user group.<br>This parameter can have one of the following values:<br>-<br>` USER `. The stakeholder is an individual user account.<br>-<br>` USER-GROUP`. The stakeholder is a user group.|
|`stakeholdership > roleId`|System-generated identifier of the user role that is assigned to the stakeholder of<br>the category.|
|`stakeholdership >`<br>`isInherited `|Indicates whether the stakeholder is directly assigned to the category or if the<br>stakeholder is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The stakeholder is inherited from the category hierarchy.<br>-<br>` false`. The stakeholder is directly assigned to the category.|
|`assetGroups`|Details of the asset group that is assigned to the category.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the<br>asset group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|
|`systemAttributes >`<br>`createdBy`|System-generated identifier of the user account that created the category.|
|`systemAttributes >`<br>`createdOn`|Date when the category was created.|
|`systemAttributes >`<br>`modifiedBy`|System-generated identifier of the latest user account that modified the category.|
|`systemAttributes >`<br>`modifiedOn`|Latest date when the category was modified.|


#### Modify categories
Use a REST API to modify categories in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/categories/<categoryId>**|
||`<categoryID>`: Required. Enter the system-generated unique identifier of the category that you want to<br>modify.|
||To get the system-generated unique identifier of a category from the Data Marketplace user interface,<br>open the category. The category page's URL contains the system-generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/category/view?ac=67417f72-e5ab-44f0-add9-a1e412c1ce13&dtn=_AfterEBF%20may20`, the system-generated unique identifier is` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|Method|PATCH|


For more information about how you can call an API, see “Authenticate using JWT” on page 12.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`operation `|Required. Specify the type of change you want<br>to make to the category.<br>Enter one of the following values:<br>-<br>` add `. To add a value to a parameter, enter .<br>-<br>` replace `. To replace an existing value of a<br>parameter.<br>-<br>` remove`. To remove a value from a<br>parameter.|A` replace ` operation modifies a<br>` parentCategory `,` category `,<br>` summary `, or a<br>` customAtrributes` segment.<br>You cannot use a` replace`<br>operation to modify stakeholders<br>of a data collection or a<br>category. Modify stakeholders of<br>a data collection or category<br>from its page in Data<br>Marketplace. For more<br>information about modifying data<br>collections and categories, see<br>the following:<br>- To modify data collections,<br>see_Modifying data collections_<br>from the_Working with data_<br>_collections_help.<br>- To modify categories, see<br>_Modifying categories_in the_Set_<br>_Up Data Marketplace_help.|
|`segment `|Required. Specify the type of details that you<br>want the API request to modify.<br>Enter one of the following values:<br>-<br>` parentCategory`. To modify the value<br>specified in the` parentCategoryId `<br>parameter.<br>-<br>` stakeholdership`. To add or remove the<br>values specified in the` stakeholderId ` and<br>` roleID ` parameters.<br>-<br>` summary`. To modify the values specified in<br>the` name `,` description `,` externalId ` and<br>` status` parameters.||
|`name `|Optional. Enter a name for the category.|Ensure that you enter a unique<br>value.<br>Data Marketplace doesn't<br>consider the letter case when it<br>verifies the uniqueness of the<br>` name` parameter's value. For<br>example, if you try to name a<br>category as "Retail Department"<br>while a category called "Retail<br>department" already exists, the<br>API call fails.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`parentCategoryId `|Optional. Enter the system-generated unique<br>identifier of the parent category for the<br>category that you want to create.|If you don't want to add this<br>category as a subcategory to<br>another category, you can leave<br>this parameter empty.<br>For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a category, see<br>“Retrieve all categories” on page 31.<br>To get the system-generated<br>unique identifier of a category<br>from the Data Marketplace user<br>interface, open the category. The<br>category page's URL contains the<br>system-generated unique<br>identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-`<br>`a1e412c1ce13&dtn=_AfterEB `<br>` F%20may20 `, the system-generated unique identifier is<br>` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|`description`|Optional. Enter a description for the category.|-|
|`externalId`|Optional. Enter a reference identifier for the<br>category.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique<br>value to the item. The reference<br>identifier that Data Marketplace<br>automatically generates contains<br>a prefix. The administrator can<br>specify the prefix of the<br>automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a<br>unique value. Ensure that you<br>don't use the prefix value that is<br>configured in Metadata<br>Command Center.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`assetGroups`|Optional. Specify an asset group for the<br>category.|Asset groups enable you to<br>control access to the category,<br>the subcategories and data<br>collections within it.<br>For more information about<br>asset groups, see the_Implement_<br>_access controls on metadata_<br>topic in the_Set Up Data_<br>_Marketplace_help.|
|`status`|Optional. Specify the status of the category.<br>The status determines whether the category is<br>discoverable and usable in Data Marketplace.<br>Enter one of the following values:<br>- To make the category discoverable and<br>usable in Data Marketplace, enter` ACTIVE `.<br>- To make the category not discoverable and<br>unusable in Data Marketplace, enter<br>` INACTIVE`.||


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`stakeholdership >`<br>`stakeholderId`|Optional. Enter the system-generated unique<br>identifier of the user account or user group<br>that you want to assign as a stakeholder on the<br>category.|- To get the system-generated<br>unique identifier of a user<br>account, click**My Services >**<br>**Administrator > Users**. On the<br>**Users**page, click a user<br>account. The user account<br>page's URL contains the<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/usersAsset/`<br>`0LH3xBJC9A6haZ26htGZyT `,<br>the unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system-generated<br>unique identifier of a user<br>group, click**My Services >**<br>**Administrator > User Groups**.<br>On the**User Groups**page, click<br>a user group. The user group<br>page's URL contains the<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userGroupsAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `,<br>the unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|
|`stakeholdership >`<br>`roleId `|Optional. Enter the system-generated unique<br>identifier of the user role that is assigned to<br>the stakeholder that you specified in the<br>` stakeholderId ` parameter.|If you specify a value for the<br>` stakeholderId` parameter,<br>ensure that you also specify a<br>value for the` roleId` parameter.<br>To get the system-generated<br>unique identifier of a user role,<br>click**My Services >**<br>**Administrator > User Roles**. On<br>the**User Roles**page, click a user<br>role. The user role page's URL<br>contains the unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userRolesAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `, the<br>unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|


#### Example request
The following example shows how you can use an API to modify a category: 

```
{

  "operation": "replace",

  "segment": "summary",
  "value": {

    "name": "Sales",
    "description": "Data collections related to sales made by the organization. ",
    "externalId": "CAT-001",
    "status": "ACTIVE"
  }
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the category is successfully modified: 

```
204 OK code
```

If you modify the asset group, parent or stakeholder of a category, the REST client displays the following response code to indicate that a job was created in Metadata Command Center to apply your changes: 

```
202 Accepted code
```

The following example shows the response of an API call to modify a category for which a job was created in Metadata Command Center to apply the changes: 

```
{
  "trackerJobId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "propagationJobId": "b93c4539-a93e-440d-a4b4-95d69b8911e6"
}
```

**Note:** When you modify the stakeholders, asset groups, or parent category of a category, the system checks for the number of items related to the category. If the category is related to more than 10 items, the API triggers a job to modify the category and returns a job ID. Items that are related to a category include the following: 

- Sub-categories of the category 

- Data collection requests that are linked to the category 

- Data collections and their related objects under the category and its sub-categories 

The following table describes the parameters of each job that is created in Metadata Command Center: 

|**Parameter**|**Description**|
|---|---|
|`trackerJobId`|System-generated identifier of the job that enables you to track the progress of the job in<br>Metadata Command Center.<br>For more information about jobs in Metadata Command Center, see the_Administration_help in<br>Metadata Command Center.|
|`propagationJobId`|System-generated identifier of the internal job that applies your changes to Data Marketplace .|


#### Delete categories
Use a REST API to delete categories in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/categories/<categoryId>**|
||`<categoryID>`: Required. Enter the system-generated unique identifier of the category that you want to<br>delete.|
||To get the system-generated unique identifier of a category from the Data Marketplace user interface,<br>open the category. The category page's URL contains the system-generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/category/view?ac=67417f72-e5ab-44f0-add9-a1e412c1ce13&dtn=_AfterEBF%20may20`, the system-generated unique identifier is` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|Method|DELETE|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to delete a category: 

```
https://{{CDMP_URL}}/api/v2/categories/67417f72-e5ab-44f0-add9-a1e412c1ce13
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the category is successfully deleted: 

```
204 OK code
```

## Chapter 4: Manage data collections
A data collection is a grouping of one or more data assets. Use REST APIs to create, retrieve, modify, or delete a data collection. You can also use APIs to perform other tasks, such as modify data assets and delivery targets of a data collection, retrieve the consumer accesses that are associated with a data collection, and so on. 

The following table lists the tasks you can perform related to a data collection: 

|**Tasks**|**Topic**|**Authentication Method**|
|---|---|---|
|Create a data collection|See “Create data collections” on page 44.|JWT|
|Retrieve a data collection|See “Retrieve a data collection” on page 49.|JWT|
|Retrieve all data collections|See “Retrieve all data collections” on page 51.|JWT|
|Modify a data collection|See “Modify data collections” on page 55.|JWT|
|Delete a data collection|See “Delete data collections” on page 60.|JWT|
|Add or remove data assets<br>from data collections|See “Add or remove data assets from data collections” on page 61.|JWT|
|Retrieve the data assets of a<br>data collection|See “Retrieve the data assets of a data collection” on page 63.|JWT|
|Retrieve the consumer<br>accesses of a data collection|See “Retrieve the consumer accesses of a data collection” on page 66.|JWT|
|Add or remove terms of use<br>from data collections|See “Add or remove terms of use from data collections” on page 251.|Application Integration|
|Retrieve the terms of use of a<br>data collection|See “Retrieve the terms of use of a data collection” on page 254.|Application Integration|
|Retrieve data collection rating|See “Retrieve data collection rating” on page 256.|Application Integration|
|Manage the delivery targets of<br>a data collection|See “Manage the delivery targets of a data collection” on page 68.|JWT|

### Create data collections
Use a REST API to create data collections in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/data-collections**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`externalId`|Optional. Enter a reference identifier for the<br>data collection that you want to create.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique<br>value to the item. The reference<br>identifier that Data Marketplace<br>automatically generates contains<br>a prefix. The administrator can<br>specify the prefix of the<br>automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a<br>unique value. Ensure that you<br>don't use the prefix value that is<br>configured in Metadata<br>Command Center.|
|`name `|Required. Enter a name for the data collection.|Ensure that you enter a unique<br>value.<br>Data Marketplace doesn't<br>consider the letter case when it<br>verifies the uniqueness of the<br>` name` parameter's value. For<br>example, if you try to name a<br>category as "Retail Department"<br>while a category called "Retail<br>department" already exists, the<br>API call fails.|
|`description`|Required. Enter a description for the data<br>collection.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`categoryId `|Required. The system-generated unique<br>identifier of the category to which you want to<br>add the data collection.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a category, see<br>“Retrieve all categories” on page 31.<br>To get the system-generated<br>unique identifier of a category<br>from the Data Marketplace user<br>interface, open the category. The<br>category page's URL contains the<br>system-generated unique<br>identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-`<br>`a1e412c1ce13&dtn=_AfterEB `<br>` F%20may20 `, the system-generated unique identifier is<br>` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|`status`|Required. Specify a status for the data<br>collection. The status determines whether the<br>data collection is discoverable to Data Users.<br>Enter one of the following values:<br>- To make the data collection discoverable to<br>Data Users, enter` PUBLISHED`.<br>- To make the data collection undiscoverable<br>to Data Users, enter` UNPUBLISHED`.|-|
|`usageContextId`|Optional. Enter the system-generated unique<br>identifier of the usage type that you want to<br>use to specify the context in which you intend<br>to use the data after you receive access to the<br>collection.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a usage type, see<br>“Retrieve usage type” on page 174.|
|`customAttributes`|Specify the custom attribute values for the<br>data collection.|Whether you must enter a value<br>in a custom attribute or not is<br>determined by how the custom<br>attribute was defined by your<br>administrator in Metadata<br>Command Center.<br>Custom attributes are additional<br>properties for Data Marketplace<br>items that are defined by your<br>administrator in Metadata<br>Command Center. For more<br>information about custom<br>attributes, see the_Create custom_<br>_attributes for items_topic in the<br>_Set Up Data Marketplace_help.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`customAttributes >`<br>`value`|Enter a value in the custom attribute.|-|
|`customAttributes > id`|Enter the system-generated unique identifier of<br>the custom attribute.|For more information about how<br>you can retrieve the system-generated unique identifier of a<br>custom attribute, see<br>“Retrieve custom attributes” on page 353.|
|`stakeholdership >`<br>`stakeholderId`|Optional. Enter the system-generated unique<br>identifier of the user account or user group<br>that you want to assign as a stakeholder for<br>the data collection.|- To get the system-generated<br>unique identifier of a user<br>account, click**My Services >**<br>**Administrator > Users**. On the<br>**Users**page, click a user<br>account. The user account<br>page's URL contains the<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/usersAsset/`<br>`0LH3xBJC9A6haZ26htGZyT `,<br>the unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system-generated<br>unique identifier of a user<br>group, click**My Services >**<br>**Administrator > User Groups**.<br>On the**User Groups**page, click<br>a user group. The user group<br>page's URL contains the<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userGroupsAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `,<br>the unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`stakeholdership >`<br>`roleId `|Optional. Enter the system-generated unique<br>identifier of the user role that is assigned to<br>the stakeholder that you specified in the<br>` stakeholderId ` parameter.|If you specify a value for the<br>` stakeholderId` parameter,<br>ensure that you also specify a<br>value for the` roleId` parameter.<br>To get the system-generated<br>unique identifier of a user role,<br>click**My Services >**<br>**Administrator > User Roles**. On<br>the**User Roles**page, click a user<br>role. The user role page's URL<br>contains the unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userRolesAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `, the<br>unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|
|`termsOfUse`|Optional. Enter the system-generated unique<br>identifier of the terms of use that you want to<br>associate with the data collection.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a terms of use, see<br>“Retrieve terms of use” on page 165.|


#### Example request
The following example shows how you can use an API to create a data collection: 

```
{
  "externalId": "DCL-1",
  "name": "Aggregated Sales Data",
  "description": "Sales data from all sources (physical online subscription) in one
place for easy analysis.",
  "categoryId": "2593524d-82f7-4dfd-bc6b-17088c046f9d",
  "status": "PUBLISHED",
  "usageContexts": [
    "2593524d-82f7-4dfd-bc6b-17088c046f9d"
  ],
  "customAttributes": [
    {
      "value": "PROJ-2024-CUSTOMER-INSIGHTS",
      "id": "com.infa.odin.models.custom.ca_2491832487117218775"
    }
  ],
  "stakeholdership": [
    {
      "stakeholderId": "jqPFaKmJRGhdfJ35eWUzoW",
      "roleId": "5Qvy6Uiiq9IjoocWeJhccW"
    }
  ],
  "termsOfUse": [
    "2593524d-82f7-4dfd-bc6b-17088c046f9d"
  ]
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 


The following example shows the response of an API call to create a data collection: 

```
{
  "id": "2593524d-82f7-4dfd-bc6b-17088c046f9d",
  "externalId": "DCL-1",
  "name": "Aggregated Sales Data",
  "description": "All sales data from around the world aggregated and standardized.",
  "status": "UNPUBLISHED",
  "assetGroups": [
    {
      "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
      "name": "ASIA",
      "isInherited": true
    }
  ]

}
```

The following table describes the parameters of each data collection that is created: 

|**Parameter**|**Description**|
|---|---|
|`externalId`|Reference identifier of the data collection.|
|`name`|Name of the data collection.|
|`description`|Description of the data collection.|
|`status `|Status of the data collection. The status indicates whether the data collection is discoverable<br>by Data Users when they search for it.<br>A data collection can have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to Data Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable to Data Users.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data collection.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups >`<br>`name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset group is<br>inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|

### Retrieve a data collection
Use a REST API to retrieve the details of an individual data collection in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/data-collections/<dataCollectionId>**<br>`<dataCollectionId>`: Required. Enter the system-generated unique identifier of the data collection for<br>which you want to retrieve the details.|
||For more information about how you can use an API to get the system-generated unique identifier of a<br>data collection, see<br>“Retrieve all data collections” on page 51.|
||To get the system-generated unique identifier of a data collection from the Data Marketplace user<br>interface, open the data collection. The data collection page's URL contains the unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary`, the unique identifier is` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details about the data<br>collection.<br>-<br>` category `. Returns the details about the<br>category that contains the data collection.<br>-<br>` customAttributes `. Returns the details<br>about the custom attributes that are<br>configured for the data collection.<br>-<br>` deliveryTargets `. Returns the details<br>about the delivery targets that are<br>associated with the data collection.<br>-<br>` stakeholdership `. Returns the type of user<br>and role that are assigned as stakeholder on<br>the data collection.<br>-<br>` systemAttributes `. Returns the details<br>about the data collection's creation and<br>modification, such as the date when the data<br>collection was created, the latest date when<br>it was modified, and so on.<br>-<br>` termsOfUse `. Returns the details about the<br>terms of use that are associated with the<br>data collection.<br>-<br>` usageContexts`. Returns the details about<br>the usage types that are associated with the<br>data collection.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the data<br>collection such as the name,<br>description and so on.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of a data collection: 

```
https://{{CDMP_URL}}/api/v2/data-collections/2593524d-82f7-4dfd-bc6b-17088c046f9d
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a data collection: 

```
{
  "id": "2593524d-82f7-4dfd-bc6b-17088c046f9d",
  "externalId": "DCL-1",
  "name": "Aggregated Sales Data",
  "description": "All sales data from around the world aggregated and standardized.",
  "status": "UNPUBLISHED",
  "assetGroups": [
    {
      "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
      "name": "ASIA",
      "isInherited": true
    }
  ]
}
```

The following table describes the parameters of the data collection that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`externalId`|Reference identifier of the data collection.|
|`name`|Name of the data collection.|
|`description`|Description of the data collection.|
|`status `|Status of the data collection. The status indicates whether the data collection is discoverable<br>by Data Users when they search for it.<br>A data collection can have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to Data Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable to Data Users.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data collection.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups >`<br>`name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset group is<br>inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


### Retrieve all data collections
Use a REST API to retrieve the details of all data collections in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/data-collections**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`search`|Optional. Enter the search term to find a data<br>collection by name or description.|Default is an asterisk (*) which<br>retrieves all categories. To<br>search by name, enter a term<br>that doesn't contain an asterisk|
|`offset`|Optional. Specify the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Specify the maximum number of<br>results that are displayed on a page.|Default is` 20`.<br>Maximum value is` 200`.|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details about each data<br>collection that is retrieved.<br>-<br>` category `. Returns the details about the<br>category that contains a data collection.<br>-<br>` customAttributes `. Returns the details<br>about the custom attributes that are<br>configured for a data collection.<br>-<br>` deliveryTargets `. Returns the details<br>about the delivery targets that are<br>associated with a data collection.<br>-<br>` stakeholdership `. Returns the type of user<br>and role that are assigned as stakeholder on<br>a data collection.<br>-<br>` systemAttributes `. Returns the details<br>about a data collection's creation and<br>modification, such as the date when a data<br>collection was created, the latest date when<br>it was modified, and so on.<br>-<br>` termsOfUse `. Returns the details about the<br>terms of use that are associated with a data<br>collection.<br>-<br>` usageContexts`. Returns the details about<br>the usage types that are associated with a<br>data collection.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the data<br>collection such as the name,<br>description and so on.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortby `|Optional. Specify the parameters to sort the<br>search results.<br>Enter one of the following values:<br>-<br>` id `<br>-<br>` name `<br>-<br>` status `<br>-<br>` createdBy `<br>-<br>` createdOn `<br>-<br>` modifiedBy `<br>-<br>` modifiedOn`|Default is` modifiedOn`.|
|`sortOrder`|Optional. Set the sorting order of the search<br>results.<br>Enter one of the following values:<br>- To sort the search results by ascending<br>order, enter` asc`.<br>- To sort the search results by descending<br>order, enter` desc`.|Default is` desc`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of all data collections: 

```
https://{{CDMP_URL}}/api/v2/data-collections
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of all data collections: 

```
{
  "pageInfo": {
    "offset": 40,
    "limit": 20,
    "totalCount": 120
  },
  "links": {
    "self": {
      "href": "/data-collections?offset=40&limit=20"
    },
    "first": {
      "href": "/data-collections?offset=0&limit=20"
    },
    "next": {
      "href": "/data-collections?offset=60&limit=20"
    },
    "previous": {
      "href": "/data-collections?offset=20&limit=20"
    },
    "last": {
      "href": "/data-collections?offset=100&limit=20"
    }
  },
  "items": [
    {
      "id": "2593524d-82f7-4dfd-bc6b-17088c046f9d",
      "externalId": "DCL-1",
      "name": "Aggregated Sales Data",
```


```
      "description": "All sales data from around the world aggregated and standardized.",
      "status": "UNPUBLISHED",
      "assetGroups": [
        {
          "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
          "name": "ASIA",
          "isInherited": true
        }
      ]
    }
  ]
}
```

The following table describes the parameters of each data collection that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`pageInfo > offset`|Starting index of the paginated results.|
|`pageInfo > limit`|The maximum number of results that are displayed on a page.|
|`pageInfo > totalCount`|Number of data collections retrieved.|
|`links > self > href`|The API that was invoked to generate this response.|
|`links > first > href`|The API endpoint that you can use to retrieve the search results that are displayed on<br>the first page.|
|`links > next > href`|The API endpoint that you can use to retrieve the search results from the page following<br>the one you currently have open.|
|`links > previous >`<br>`href`|The API endpoint that you can use to retrieve the search results from the page prior to<br>the one you currently have open.|
|`links > last > href`|The API endpoint that you can use to retrieve the search results that are displayed on<br>the last page.|
|`externalId`|Reference identifier of the data collection.|
|`name`|Name of the data collection.|
|`description`|Description of the data collection.|
|`status `|Status of the data collection. The status indicates whether the data collection is<br>discoverable by Data Users when they search for it.<br>A data collection can have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to Data Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable to Data Users.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data<br>collection.|
|`assetGroups > id`|System-generated identifier of the asset group.|

|**Parameter**|**Description**|
|---|---|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset<br>group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


### Modify data collections
Use a REST API to modify a data collection in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/data-collections/<dataCollectionId>**|
||`<dataCollectionId>`: Required. Enter the system-generated unique identifier of the data collection that<br>you want to modify.|
||For more information about how you can use an API to get the system-generated unique identifier of a<br>data collection, see<br>“Retrieve all data collections” on page 51.|
||To get the system-generated unique identifier of a data collection from the Data Marketplace user<br>interface, open the data collection. The data collection page's URL contains the unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary`, the unique identifier is` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|Method|PATCH|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`operation `|Required. Specify the type of change you want<br>to make to the data collection.<br>Enter one of the following values:<br>-<br>` add `. To add a value to a parameter, enter .<br>-<br>` replace `. To replace an existing value of a<br>parameter.<br>-<br>` remove`. To remove a value from a<br>parameter.|A` replace ` operation modifies a<br>` parentCategory `,` category `,<br>` summary `, or a<br>` customAtrributes` segment.<br>You cannot use a` replace`<br>operation to modify stakeholders<br>of a data collection or a<br>category. Modify stakeholders of<br>a data collection or category<br>from its page in Data<br>Marketplace. For more<br>information about modifying data<br>collections and categories, see<br>the following:<br>- To modify data collections,<br>see_Modifying data collections_<br>from the_Working with data_<br>_collections_help.<br>- To modify categories, see<br>_Modifying categories_in the_Set_<br>_Up Data Marketplace_help.|
|`segment `|Required. Specify the type of details that you<br>want the API request to modify.<br>Enter one of the following values:<br>-<br>` category`. To modify the value specified in<br>the` categoryId ` parameter.<br>-<br>` customAttributes`. To modify the value<br>specified in the` customAttributes `<br>parameter.<br>-<br>` summary`. To modify the values specified in<br>the` name `,` description `,` externalId ` and<br>` status ` parameters.<br>-<br>` stakeholdership`. To add or remove the<br>values specified in the` stakeholderId ` and<br>` roleID ` parameters.<br>-<br>` termsOfUse`. To modify the value specified<br>in the` termsOfUse ` parameter.<br>-<br>` usageContexts`. To modify the value<br>specified in the` usageContexts` parameter.||
|`name `|Optional. Enter a name for the data collection.|Ensure that you enter a unique<br>value.<br>Data Marketplace doesn't<br>consider the letter case when it<br>verifies the uniqueness of the<br>` name` parameter's value. For<br>example, if you try to name a<br>category as "Retail Department"<br>while a category called "Retail<br>department" already exists, the<br>API call fails.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`description`|Optional. Enter a description for the data<br>collection.|-|
|`externalId`|Optional. Enter a reference identifier for the<br>data collection.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique<br>value to the item. The reference<br>identifier that Data Marketplace<br>automatically generates contains<br>a prefix. The administrator can<br>specify the prefix of the<br>automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a<br>unique value. Ensure that you<br>don't use the prefix value that is<br>configured in Metadata<br>Command Center.|
|`status`|Optional. Specify a status for the data<br>collection. The status determines whether the<br>data collection is discoverable to Data Users.<br>Enter one of the following values:<br>- To make the data collection discoverable to<br>Data Users, enter` PUBLISHED`.<br>- To make the data collection undiscoverable<br>to Data Users, enter` UNPUBLISHED`.||
|`customAttributes`|Specify the custom attribute values for the<br>data collection or modify the values that are<br>already specified for the custom attribute.|Whether you must enter a value<br>in a custom attribute or not is<br>determined by how the custom<br>attribute was defined by your<br>administrator in Metadata<br>Command Center.<br>Custom attributes are additional<br>properties for Data Marketplace<br>items that are defined by your<br>administrator in Metadata<br>Command Center. For more<br>information about custom<br>attributes, see the_Create custom_<br>_attributes for items_topic in the<br>_Set Up Data Marketplace_help.|
|`customAttributes >`<br>`value`|Enter the updated value for the custom<br>attribute.|-|
|`customAttributes > id`|Enter the system-generated unique identifier of<br>the custom attribute that you want to modify.|For more information about how<br>you can retrieve the system-generated unique identifier of a<br>custom attribute, see<br>“Retrieve custom attributes” on page 353.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`stakeholdership >`<br>`stakeholderId`|Optional. Enter the system-generated unique<br>identifier of the user account or user group<br>that you want to assign as a stakeholder for<br>the data collection.|- To get the system-generated<br>unique identifier of a user<br>account, click**My Services >**<br>**Administrator > Users**. On the<br>**Users**page, click a user<br>account. The user account<br>page's URL contains the<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/usersAsset/`<br>`0LH3xBJC9A6haZ26htGZyT `,<br>the unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system-generated<br>unique identifier of a user<br>group, click**My Services >**<br>**Administrator > User Groups**.<br>On the**User Groups**page, click<br>a user group. The user group<br>page's URL contains the<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userGroupsAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `,<br>the unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|
|`stakeholdership >`<br>`roleId `|Optional. Enter the system-generated unique<br>identifier of the user role that is assigned to<br>the stakeholder that you specified in the<br>` stakeholderId ` parameter.|If you specify a value for the<br>` stakeholderId` parameter,<br>ensure that you also specify a<br>value for the` roleId` parameter.<br>To get the system-generated<br>unique identifier of a user role,<br>click**My Services >**<br>**Administrator > User Roles**. On<br>the**User Roles**page, click a user<br>role. The user role page's URL<br>contains the unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userRolesAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `, the<br>unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|
|`usageContexts`|Optional. Enter the system-generated unique<br>identifier of the usage type that you want to<br>use to specify the context in which you intend<br>to use the data after you receive access to the<br>collection.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a usage type, see<br>“Retrieve usage type” on page 174.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`termsOfUse`|Optional. Enter the system-generated unique<br>identifier of the terms of use that you want to<br>associate with the data collection.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a terms of use, see<br>“Retrieve terms of use” on page 165.|
|`categoryId `|Optional. The system-generated unique<br>identifier of the category to which you want to<br>move the data collection.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a category, see<br>“Retrieve all categories” on page 31.<br>To get the system-generated<br>unique identifier of a category<br>from the Data Marketplace user<br>interface, open the category. The<br>category page's URL contains the<br>system-generated unique<br>identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-`<br>`a1e412c1ce13&dtn=_AfterEB `<br>` F%20may20 `, the system-generated unique identifier is<br>` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|


#### Example request
The following example shows how you can use an API to modify a data collection: 

```
{
  "operation": "replace",
  "segment": "summary",
  "value": {
    "name": "Aggregated Sales Data",
    "description": "Sales data from all sources (physical online subscription) in one
place for easy analysis.",
    "externalId": "DCL-1",
    "status": "PUBLISHED"
  }
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data collection is successfully modified: 

```
204 OK code
```

If you modify the category or stakeholder of a data collection, the REST client displays the following response code to indicate that a job was created in Metadata Command Center to apply your changes: 

```
202 Accepted code
```


The following example shows the response of an API call to modify a category for which a job was created in Metadata Command Center to apply the changes: 

- `{ "trackerJobId": "b93c4539-a93e-440d-a4b4-95d69b8911e6", "propagationJobId": "b93c4539-a93e-440d-a4b4-95d69b8911e6" }` 

**Note:** When you modify the stakeholders or category of a data collection, the system checks for the number of items related to the data collection. If the data collection is related to more than 10 items, the API triggers a job to modify the data collection and returns a job ID. Items that are related to a data collection include the following: 

- Orders for the data collection 

- Consumer accesses for the data collection 

- Delivery targets that are available for the data collection 

- Data collection requests that are linked to the data collection 

The following table describes the parameters of each job that is created in Metadata Command Center: 

|**Parameter**|**Description**|
|---|---|
|`trackerJobId`|System-generated identifier of the job that enables you to track the progress of the job in<br>Metadata Command Center.<br>For more information about jobs in Metadata Command Center, see the_Administration_help in<br>Metadata Command Center.|
|`propagationJobId`|System-generated identifier of the internal job that applies your changes to Data Marketplace .|


### Delete data collections
Use a REST API to delete a data collection in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/data-collections/<dataCollectionId>**<br>`<dataCollectionId>`: Required. Enter the system-generated unique identifier of the data collection that<br>you want to delete.|
||For more information about how you can use an API to get the system-generated unique identifier of a<br>data collection, see<br>“Retrieve all data collections” on page 51.|
||To get the system-generated unique identifier of a data collection from the Data Marketplace user<br>interface, open the data collection. The data collection page's URL contains the unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary`, the unique identifier is` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|Method|DELETE|

**Note:** If the data collection that you want to delete is linked to another collection, this link is removed when you delete the data collection. In this scenario, the other collection that is linked does not get deleted. 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to delete a data collection: 

```
https://{{CDMP_URL}}/api/v2/data-collections/2593524d-82f7-4dfd-bc6b-17088c046f9d
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data collection is successfully deleted: 

```
204 OK code
```

### Add or remove data assets from data collections
Use a REST API to add a data asset to a data collection or to remove a data asset from a data collection. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/data-collections/<dataCollectionId>/data-assets**<br>`<dataCollectionId>`: Required. Enter the system-generated unique identifier of the data collection to<br>which you want to add the data assets.<br>For more information about how you can use an API to get the system-generated unique identifier of a<br>data collection, see<br>“Retrieve all data collections” on page 51.|
||To get the system-generated unique identifier of a data collection from the Data Marketplace user<br>interface, open the data collection. The data collection page's URL contains the unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary`, the unique identifier is` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|Method|PATCH|


**Note:** You can add a maximum of 40 data assets to a data collection per API call. 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`operation`|Required. Specify whether you want to add or<br>remove the data asset.<br>Enter one of the following values:<br>- To add a data asset to the data collection,<br>enter` add`.<br>- To remove a data asset from the data<br>collection, enter` remove`.|-|
|`dataAssetIds `|Required. Enter the system-generated unique<br>identifier of the data asset that you want to<br>add to the data collection.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a data asset, see<br>“Retrieve data assets” on page 203.<br>To get the system-generated<br>unique identifier of a data asset<br>from the Data Marketplace user<br>interface, open the data asset.<br>The data asset page's URL<br>contains the unique identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/dataAsset/`<br>`8c4c2089-6c7e-4696-a8f6-44f87639d65c?`<br>`dtn=Table_Profiling_AK_16 `<br>` 76302415587&tab=dataEleme `<br>` nts `, the unique identifier is<br>` 8c4c2089-6c7e-4696-a8f6-44f87639d65c`.|


#### Example request
The following example shows how you can use an API to add data assets to a data collection: 

```
{
```

- `"operation": "add",` 

```
  "dataAssetIds": [
```

- `"b93c4539-a93e-440d-a4b4-95d69b8911e6",` 

```
    "2593524d-82f7-4dfd-bc6b-17088c046f9d"
  ]
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data asset is successfully added to the data collection: 

- `204 OK code`

### Retrieve the data assets of a data collection
Use a REST API to retrieve the data assets of a data collection. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v2/data-collections/<dataCollectionId>/data-assets** `<dataCollectionId>` : Required. Enter the system-generated unique identifier of the data collection for which you want to retrieve the data assets. For more information about how you can use an API to get the system-generated unique identifier of a data collection, see “Retrieve all data collections” on page 51. To get the system-generated unique identifier of a data collection from the Data Marketplace user interface, open the data collection. The data collection page's URL contains the unique identifier. For example, in the URL `https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3ecec1e171d0f1?dtn=&tab=summary ` , the unique identifier is ` 25158afc-3dfb-44ef-8f3ecec1e171d0f1` . Method GET 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`offset`|Optional. Specify the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Specify the maximum number of<br>results that are displayed on a page.|Default is` 20`.<br>Maximum is` 200`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of the data assets that are associated with a data collection: 

```
https://{{CDMP_URL}}/api/v2/data-collections/2593524d-82f7-4dfd-bc6b-17088c046f9d/data-
assets
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of the data assets that are associated with a data collection: 

```
{
  "pageInfo": {
    "offset": 40,
    "limit": 20,
```


```
    "totalCount": 150
  },
  "links": {
    "self": {
      "href": "/data-collections/{dataCollectionId}/data-assets?offset=40&limit=20"
    },
    "first": {
      "href": "/data-collections/{dataCollectionId}/data-assets?offset=0&limit=20"
    },
    "next": {
      "href": "/data-collections/{dataCollectionId}/data-assets?offset=60&limit=20"
    },
    "previous": {
      "href": "/data-collections/{dataCollectionId}/data-assets?offset=20&limit=20"
    },
    "last": {
      "href": "/data-collections/{dataCollectionId}/data-assets?offset=140&limit=20"
    }
  },
  "items": [
    {
      "id": "2593524d-82f7-4dfd-bc6b-17088c046f9d",
      "externalId": "DAS-1",
      "name": "NA Sales Data",
      "description": "Data related to sales made in the 'North America' region.",
      "status": "ENABLED",
      "type": "Table",
      "source": "Snowflake",
      "descriptiveSource": "CDGC",
      "refLink": "www.snowflake.provisioning/datashare56",
      "assetLocation": "Snowflake",
      "assetLocationDescription": "Sales data for NA region stored in Snowflake DB of
respective Data Center",
      "technicalAssetName": "NA Sales Data",
      "averageRating": 4.5
    }
  ]
}
```

The following table describes the parameters of each data asset that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`pageInfo > offset`|Starting index of the paginated results.|
|`pageInfo > limit`|The maximum number of results that are displayed on a page.|
|`pageInfo > totalCount`|Number of data assets retrieved.|
|`links > self > href`|The API that was invoked to generate this response.|
|`links > first > href`|The API endpoint that you can use to retrieve the search results that are displayed<br>on the first page.|
|`links > next > href`|The API endpoint that you can use to retrieve the search results from the page<br>following the one you currently have open.|
|`links > previous > href`|The API endpoint that you can use to retrieve the search results from the page prior<br>to the one you currently have open.|
|`links > last > href`|The API endpoint that you can use to retrieve the search results that are displayed<br>on the last page.|
|`id`|System-generated unique identifier of the data asset.|

|**Parameter**|**Description**|
|---|---|
|`externalId`|Reference identifier of the data asset.|
|`name`|Name of the data asset.|
|`description`|Description of the data asset.|
|`status `|Status of the data asset. The status indicates whether the data asset is available to<br>be added to data collections.<br>A data asset can have one of the following statuses:<br>-<br>` ENABLED `. The data asset is available.<br>-<br>` DISABLED`. The data asset isn't available.|
|`type`|Type of the data asset.|
|`source`|Source system from which the data is supplied to Data Marketplace.|
|`descriptiveSource`|Source application from which the description of the data asset is taken.|
|`refLink`|Uniform resource identifier of the location in the data source where the data asset<br>is stored.|
|`assetLocation`|Location of the data asset in the data source.|
|`assetLocationDescription`|Description of the location in the data source where the data asset is stored.|
|`technicalAssetName`|Name of the data asset as it appears in the data source.|
|`averageRating`|If the asset is imported from Data Governance and Catalog into Data Marketplace,<br>the system also retrieves its rating. A rating in Data Governance and Catalog<br>represents a user's assessment of an asset. Data Governance and Catalog users<br>rate assets between one to five stars. The value that you see is the average of the<br>ratings provided by all the Data Governance and Catalog users. You can refer to the<br>rating of a Data Governance and Catalog asset to gauge its reliability.|


### Retrieve the consumer accesses of a data collection
Use a REST API to retrieve the consumer accesses that are associated with a data collection. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v2/data-collections/<dataCollectionId>/consumer-accesses** `<dataCollectionId>` : Required. Enter the system-generated unique identifier of the data collection for which you want to retrieve the consumer access details. For more information about how you can use an API to get the system-generated unique identifier of a data collection, see “Retrieve all data collections” on page 51. To get the system-generated unique identifier of a data collection from the Data Marketplace user interface, open the data collection. The data collection page's URL contains the unique identifier. For example, in the URL `https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3ecec1e171d0f1?dtn=&tab=summary ` , the unique identifier is ` 25158afc-3dfb-44ef-8f3ecec1e171d0f1` . Method GET 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`offset`|Optional. Specify the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Specify the maximum number of<br>results that are displayed on a page.|Default is` 20`.<br>Maximum is` 200`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of the consumer accesses that are associated with a data collection: 

```
https://{{CDMP_URL}}/api/v2/data-collections/2593524d-82f7-4dfd-bc6b-17088c046f9d/
consumer-accesses
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of the consumer accesses that are associated with a data collection: 

```
{
  "pageInfo": {
    "offset": 40,
    "limit": 20,

    "totalCount": 150
  },
  "links": {
    "self": {
      "href": "/data-collections/{dataCollectionId}/consumer-accesses?offset=40&limit=20"
    },
    "first": {
      "href": "/data-collections/{dataCollectionId}/consumer-accesses?offset=0&limit=20"
    },
    "next": {
      "href": "/data-collections/{dataCollectionId}/consumer-accesses?offset=60&limit=20"
    },
    "previous": {
      "href": "/data-collections/{dataCollectionId}/consumer-accesses?offset=20&limit=20"
    },
    "last": {
      "href": "/data-collections/{dataCollectionId}/consumer-accesses?
offset=140&limit=20"
    }
  },
  "items": [
    {
      "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
      "externalId": "CAS-123",
      "costCenter": "IT-DEPT-001",
      "status": "AVAILABLE",
      "accessGrantedOn": "2025-08-01",
      "consumer":
         {
            "stakeholderId": "jaXM6NrqsKXffdwg2NkTMR",
            "userType": "USER",
            "roleId": "Stakeholder_Role_Marketplace_Editor",
            "isInherited": false
          },
      "assetGroups": [
          {
            "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
            "name": "ASIA",
            "isInherited": true
          }
       ]
     }
  ]
}
```

The following table describes the parameters of each consumer access that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`pageInfo > offset`|Starting index of the paginated results.|
|`pageInfo > limit`|The maximum number of results that are displayed on a page.|
|`pageInfo > totalCount`|Number of consumer accesses retrieved.|
|`links > self > href`|The API that was invoked to generate this response.|
|`links > first > href`|The API endpoint that you can use to retrieve the search results that are displayed on<br>the first page.|
|`links > next > href`|The API endpoint that you can use to retrieve the search results from the page<br>following the one you currently have open.|
|`links > previous >`<br>`href`|The API endpoint that you can use to retrieve the search results from the page prior<br>to the one you currently have open.|


|**Parameter**|**Description**|
|---|---|
|`links > last > href`|The API endpoint that you can use to retrieve the search results that are displayed on<br>the last page.|
|`id`|System-generated unique identifier of the consumer access.|
|`externalId`|Reference identifier of the consumer access.|
|`costCenter`|Cost center of the Data User that was granted access to the data collection.|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to withdraw the Data User's access to<br>the data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the data collection.|
|`consumer`|Details about the Data User that was granted access to the data collection.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data<br>collection that is associated with the consumer access.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset<br>group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


### Manage the delivery targets of a data collection
A delivery target is a delivery option that you as a stakeholder of a data collection can use to deliver data to a Data User. Stakeholders use delivery templates to create new delivery targets for their data collections.

#### Create delivery targets
Use a REST API to create delivery targets in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/delivery-targets**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`externalId`|Optional. Enter a reference identifier for the<br>delivery target.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique<br>value to the item. The reference<br>identifier that Data Marketplace<br>automatically generates contains<br>a prefix. The administrator can<br>specify the prefix of the<br>automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a<br>unique value. Ensure that you<br>don't use the prefix value that is<br>configured in Metadata<br>Command Center.|
|`name `|Required. Enter a name for the delivery target.|Ensure that you enter a unique<br>value.<br>Data Marketplace doesn't<br>consider the letter case when it<br>verifies the uniqueness of the<br>` name` parameter's value. For<br>example, if you try to name a<br>delivery target as "Excel over<br>ipfs" while a delivery target<br>called "Excel over IPFS" already<br>exists, the API call fails.|
|`description`|Required. Enter a description for the delivery<br>target.|-|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`status`|Required. Specify a status for the delivery<br>target. The status determines whether the<br>delivery target is available for use to the Data<br>Users that order the data collection.<br>Enter one of the following values:<br>- To make the delivery targets available for<br>use to the Data Users that order the data<br>collection, enter` ACTIVE`.<br>- To make the delivery targets unavailable for<br>use to the Data Users that order the data<br>collection, enter` INACTIVE`.|-|
|`isDefault`|Optional. Configure the delivery target as the<br>default delivery option for a data collection.<br>Enter one of the following values:<br>- To configure the delivery target as the<br>default delivery option for a data collection,<br>enter` true`.<br>- To not configure the delivery target as the<br>default delivery option for a data collection,<br>enter` false`.|-|
|`targetSystemReference`|Optional. Enter the target system or resource<br>reference from where the data is obtained.|-|
|`physicalLocation `|Optional. Enter the location where the data is<br>delivered to a Data User.|If the value of the<br>` managedAccess ` parameter is<br>` ENABLED` for the delivery<br>template that you specified in<br>the` deliveryTemplateId `<br>parameter, the data won't be<br>delivered to the location that you<br>specify in the<br>` physicalLocation` parameter.<br>Instead, the data will be<br>delivered to a location that is<br>generated at the time of order<br>fulfillment. The generated<br>location is unique to each order<br>that is fulfilled using this target.<br>For more information, see the<br>_Manage access to data with Data_<br>_Access Management_topic in the<br>_Set Up Data Marketplace_help.<br>For more information about how<br>you can use APIs to manage<br>delivery templates, see<br>“Delivery templates” on page 148.|
|`deliveryTemplateId`|Required. Enter the system-generated unique<br>identifier of the delivery template that you want<br>to use to create the delivery target.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a delivery template,<br>see<br>“Retrieve delivery templates” on page 152.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`deliveryMethodId `|Optional. Enter the system-generated unique<br>identifier of a delivery method that is used in<br>the delivery template that you specified in the<br>` deliveryTemplateId` parameter.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a delivery method,<br>see<br>“Retrieve delivery methods” on page 143.<br>By default, the API uses the first<br>delivery method that is<br>configured for the delivery<br>template that you specified in<br>the` deliveryTemplateId`<br>parameter.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`deliveryFormatId `|Optional. Enter the system-generated unique<br>identifier of a delivery format that is used in<br>the delivery template that you specified in the<br>` deliveryTemplateId ` parameter.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a delivery format,<br>see<br>“Retrieve delivery formats” on page 136.<br>By default, the API uses the first<br>delivery format that is configured<br>for the delivery template that you<br>specified in the<br>` deliveryTemplateId`<br>parameter.|
|`dataCollectionId `|Required. Enter the system-generated unique<br>identifier of the data collection for which you<br>want to create the delivery target.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a data collection,<br>see<br>“Retrieve all data collections” on page 51.<br>To get the system-generated<br>unique identifier of a data<br>collection from the Data<br>Marketplace user interface, open<br>the data collection. The data<br>collection page's URL contains<br>the unique identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?`<br>`dtn=&tab=summary `, the unique<br>identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.<br>**Note:**If the**Managed Access**<br>option is enabled for the delivery<br>template that you specified in<br>the` deliveryTemplateId `<br>parameter, ensure that the data<br>collection that you specify in the<br>` dataCollectionId` parameter<br>is comprised only of data assets<br>that belong to the same data<br>source. For more information<br>about how you can add data<br>assets to a data collection, see<br>“Add or remove data assets from data collections” on page 61.|


#### Example request
The following example shows how you can use an API to create a delivery target: 

```
{
```

> `"externalId": "DTG-123",` 

> `"name": "Snowflake Data Exchange - US Sales",` 

> `"description": "Snowflake Data Exchange Portal for NA Analytics references and` 

> `enrichment data sources.",`

```
  "status": "ACTIVE",
  "isDefault": false,
  "targetSystemReference": "Snowflake",
  "physicalLocation": "https://provision.snowflakecomputing.com",
  "deliveryTemplateId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "deliveryMethodId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "deliveryFormatId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "dataCollectionId": "b93c4539-a93e-440d-a4b4-95d69b8911e6"
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a delivery target: 

```
{
  "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "externalId": "DTG-123",
  "name": "Snowflake Data Exchange - US Sales",
  "description": "Snowflake Data Exchange Portal for NA Analytics references and
enrichment data sources.",
  "isDefault": true,
  "status": "ACTIVE",
  "assetGroups": [
    {
      "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
      "name": "ASIA",
      "isInherited": true
    }
  ],
  "physicalLocation": "https://provision.snowflakecomputing.com",
  "targetSystemReference": "Snowflake"
}
```

The following table describes the parameters of each delivery target that is created: 

|**Parameter**|**Description**|
|---|---|
|`id`|System-generated unique identifier of the delivery target.|
|`externalId`|Reference identifier of the delivery target.|
|`name`|Name of the delivery target.|
|`description`|Description of the delivery target.|
|`isDefault `|Indicates whether the delivery target is the default delivery option for a data<br>collection. This parameter can have one of the following values:<br>-<br>` true `. The delivery target is the default delivery option for a data collection.<br>-<br>` false`. The delivery target isn't the default delivery option for a data collection.|
|`status `|Status of the delivery target. Indicates whether the delivery target is available for<br>use to the Data Users that order the data collection.<br>A delivery target can have one of the following statuses:<br>-<br>` ACTIVE `. The delivery target is available for use.<br>-<br>` INACTIVE`. The delivery target is unavailable for use.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data<br>collection for the delivery target.|
|`assetGroups > id`|System-generated identifier of the asset group.|


|**Parameter**|**Description**|
|---|---|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the<br>asset group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|
|`physicalLocation`|The location where the data is delivered.<br>**Note:**If the value of the` managedAccess` parameter is` ENABLED`, the data is not<br>delivered to the location that is specified in the` physicalLocation` parameter.<br>Instead, the data will be delivered to a location that is generated at the time of<br>order fulfillment. The generated location is unique to each order that is fulfilled<br>using this target.<br>For more information, see the_Manage access to data with Data Access Management_<br>topic in the_Set Up Data Marketplace_help.|
|`targetSystemReference`|System where the data was delivered.|


#### Retrieve a delivery target
Use a REST API to retrieve the details of an individual delivery target in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/delivery-targets/<deliveryTargetId>**|
||`<deliveryTargetId>`: Required. Enter the system-generated unique identifier of the delivery target that<br>you want to modify.|
||For more information about how you can use an API to get the system-generated unique identifier of a<br>delivery target, see<br>“Retrieve all delivery targets” on page 77.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12.

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details about the<br>delivery target.<br>-<br>` deliveryTemplate `. Returns the properties<br>about the delivery template that is used to<br>create the delivery target.<br>-<br>` deliveryFormat `. Returns the properties<br>about the delivery format that is associated<br>with the delivery template used to create the<br>delivery target.<br>-<br>` deliveryMethod `. Returns the properties<br>about the delivery method that is associated<br>with the delivery template used to create the<br>delivery target.<br>-<br>` systemAttributes`. Returns the details<br>about the delivery target's creation and<br>modification, such as the details of the user<br>that created the delivery target, the latest<br>user that modified the delivery target, and so<br>on.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the delivery<br>target such as name, description<br>and so on.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of a delivery target: 

```
https://{{CDMP_URL}}/api/v2/delivery-targets/b93c4539-a93e-440d-a4b4-95d69b8911e6
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a delivery target: 

```
{
  "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "externalId": "DTG-123",
  "name": "Snowflake Data Exchange - US Sales",
  "description": "Snowflake Data Exchange Portal for NA Analytics references and
enrichment data sources.",
  "isDefault": true,
  "status": "ACTIVE",
  "assetGroups": [
    {
      "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
      "name": "ASIA",
      "isInherited": true
    }
  ],
  "physicalLocation": "https://provision.snowflakecomputing.com",
  "targetSystemReference": "Snowflake"
}
```


The following table describes the parameters of the delivery target that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`id`|System-generated unique identifier of the delivery target.|
|`externalId`|Reference identifier of the delivery target.|
|`name`|Name of the delivery target.|
|`description`|Description of the delivery target.|
|`isDefault `|Indicates whether the delivery target is the default delivery option for a data<br>collection. This parameter can have one of the following values:<br>-<br>` true `. The delivery target is the default delivery option for a data collection.<br>-<br>` false`. The delivery target isn't the default delivery option for a data collection.|
|`status `|Status of the delivery target. Indicates whether the delivery target is available for<br>use to the Data Users that order the data collection. A delivery target can have one<br>of the following statuses:<br>-<br>` ACTIVE `. The delivery target is available for use.<br>-<br>` INACTIVE`. The delivery target is unavailable for use.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data<br>collection for the delivery target.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the<br>asset group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|
|`physicalLocation`|The location where the data is delivered.<br>**Note:**If the value of the` managedAccess` parameter is` ENABLED`, the data is not<br>delivered to the location that is specified in the` physicalLocation` parameter.<br>Instead, the data will be delivered to a location that is generated at the time of<br>order fulfillment. The generated location is unique to each order that is fulfilled<br>using this target.<br>For more information, see the_Manage access to data with Data Access Management_<br>topic in the_Set Up Data Marketplace_help.|
|`targetSystemReference`|System where the data was delivered.|

#### Retrieve all delivery targets
Use a REST API to retrieve the details of all delivery targets in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/delivery-targets**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details about each<br>delivery target that is retrieved.<br>-<br>` deliveryTemplate `. Returns the properties<br>about the delivery template that is used to<br>create a delivery target.<br>-<br>` deliveryFormat `. Returns the properties<br>about the delivery format that is associated<br>with the delivery template used to create a<br>delivery target.<br>-<br>` deliveryMethod `. Returns the properties<br>about the delivery method that is associated<br>with the delivery template used to create a<br>delivery target.<br>-<br>` systemAttributes`. Returns the details<br>about a delivery target's creation and<br>modification, such as the details of the user<br>that created the delivery target, the latest<br>user that modified the delivery target, and so<br>on.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the delivery<br>target such as name, description<br>and so on.|
|`search`|Optional. Enter the search term to find a<br>delivery target by name.|-|
|`offset`|Optional. Specify the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Specify the maximum number of<br>results that are displayed on a page.|Default is` 20`.<br>Maximum is` 200`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortby `|Optional. Specify the parameters to sort the<br>search results.<br>Enter one of the following values:<br>-<br>` id `<br>-<br>` name `<br>-<br>` status `<br>-<br>` createdOn `<br>-<br>` modifiedOn`|Default is` modifiedOn`.|
|`sortOrder`|Optional. Set the sorting order of the search<br>results.<br>Enter one of the following values:<br>- To sort the search results by ascending<br>order, enter` asc`.<br>- To sort the search results by descending<br>order, enter` desc`.|Default is` desc`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of all delivery targets: 

```
https://{{CDMP_URL}}/api/v2/delivery-targets
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of all delivery targets: 

```
{
  "pageInfo": {
    "offset": 40,
    "limit": 20,
    "totalCount": 120
  },
  "links": {
    "self": {
      "href": "/delivery-targets?offset=40&limit=20"
    },
    "first": {
      "href": "/delivery-targets?offset=0&limit=20"
    },
    "next": {
      "href": "/delivery-targets?offset=60&limit=20"
    },
    "previous": {
      "href": "/delivery-targets?offset=20&limit=20"
    },
    "last": {
      "href": "/delivery-targets?offset=100&limit=20"
    }
  },
  "items": [
    {
      "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
      "externalId": "DTG-123",
      "name": "Snowflake Data Exchange - US Sales",
      "description": "Snowflake Data Exchange Portal for NA Analytics references and
enrichment data sources.",
      "isDefault": true,

      "status": "ACTIVE",
      "assetGroups": [
        {
          "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
          "name": "ASIA",
          "isInherited": true
        }
      ],
      "physicalLocation": "https://provision.snowflakecomputing.com",
      "targetSystemReference": "Snowflake"
    }
  ]
}
```

The following table describes the parameters of each delivery target that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`pageInfo > offset`|Starting index of the paginated results.|
|`pageInfo > limit`|The maximum number of results that are displayed on a page.|
|`pageInfo > totalCount`|Number of delivery targets retrieved.|
|`links > self > href`|The API that was invoked to generate this response.|
|`links > first > href`|The API endpoint that you can use to retrieve the search results that are displayed<br>on the first page.|
|`links > next > href`|The API endpoint that you can use to retrieve the search results from the page<br>following the one you currently have open.|
|`links > previous > href`|The API endpoint that you can use to retrieve the search results from the page<br>prior to the one you currently have open.|
|`links > last > href`|The API endpoint that you can use to retrieve the search results that are displayed<br>on the last page.|
|`id`|System-generated unique identifier of the delivery target.|
|`externalId`|Reference identifier of the delivery target.|
|`name`|Name of the delivery target.|
|`description`|Description of the delivery target.|
|`isDefault `|Indicates whether the delivery target is the default delivery option for a data<br>collection. This parameter can have one of the following values:<br>-<br>` true `. The delivery target is the default delivery option for a data collection.<br>-<br>` false`. The delivery target isn't the default delivery option for a data collection.|
|`status `|Status of the delivery target. Indicates whether the delivery target is available for<br>use to the Data Users that order the data collection. A delivery target can have one<br>of the following statuses:<br>-<br>` ACTIVE `. The delivery target is available for use.<br>-<br>` INACTIVE`. The delivery target is unavailable for use.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data<br>collection for the delivery target.|


|**Parameter**|**Description**|
|---|---|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups > isInherited `|Indicates whether the asset group is directly assigned to the category or if the<br>asset group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|
|`physicalLocation`|The location where the data is delivered.<br>**Note:**If the value of the` managedAccess` parameter is` ENABLED`, the data is not<br>delivered to the location that is specified in the` physicalLocation` parameter.<br>Instead, the data will be delivered to a location that is generated at the time of<br>order fulfillment. The generated location is unique to each order that is fulfilled<br>using this target.<br>For more information, see the_Manage access to data with Data Access_<br>_Management_topic in the_Set Up Data Marketplace_help.|
|`targetSystemReference`|System where the data was delivered.|


#### Modify delivery targets
Use a REST API to modify a delivery target in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/delivery-targets/<deliveryTargetId>**|
||`<deliveryTargetId>`: Required. Enter the system generated unique identifier of the delivery target that<br>you want to modify.|
||For more information about how you can use an API to get the system-generated unique identifier of a<br>delivery target, see<br>“Retrieve all delivery targets” on page 77.|
|Method|PATCH|


For more information about how you can call an API, see “Authenticate using JWT” on page 12.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`operation `|Required. Specify the type of change you want<br>to make to the delivery target.<br>Enter one of the following values:<br>-<br>` add `. To add a value to a parameter, enter .<br>-<br>` replace `. To replace an existing value of a<br>parameter.<br>-<br>` remove`. To remove a value from a<br>parameter.|-|
|`segment`|Required. Specify the type of details that you<br>want the API request to modify.<br>Enter one of the following values:<br>- To modify the values specified in the` name`<br>and` description ` parameters, enter<br>` summary `.<br>- To modify the value specified in the<br>` deliveryTemplateId ` parameter, enter<br>` deliveryTemplate `.<br>- To modify the value specified in the<br>` deliveryFormatId ` parameter, enter<br>` deliveryFormat `.<br>- To modify the value specified in the<br>` deliveryMethodId ` parameter, enter<br>` deliveryMethod `.<br>- To modify the values specified in the<br>` externalId `,` status `,` isDefault `,<br>` targetSystemReference `, and<br>` physicalLocation ` parameters, enter<br>` selfAttributes`.|-|
|`name `|Optional. Enter a name for the delivery target.|Ensure that you enter a unique<br>value.<br>Data Marketplace doesn't<br>consider the letter case when it<br>verifies the uniqueness of the<br>` name` parameter's value. For<br>example, if you try to name a<br>delivery target as "Excel over<br>ipfs" while a delivery target<br>called "Excel over IPFS" already<br>exists, the API call fails.|
|`description`|Optional. Enter a description for the delivery<br>target.|-|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`externalId`|Optional. Enter a reference identifier for the<br>delivery target.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique<br>value to the item. The reference<br>identifier that Data Marketplace<br>automatically generates contains<br>a prefix. The administrator can<br>specify the prefix of the<br>automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a<br>unique value. Ensure that you<br>don't use the prefix value that is<br>configured in Metadata<br>Command Center.|
|`status`|Optional. Specify a status for the delivery<br>target. The status determines whether the<br>delivery target is available for use to the Data<br>Users that order the data collection.<br>Enter one of the following values:<br>- To make the delivery targets available for<br>use to the Data Users that order the data<br>collection, enter` ACTIVE`.<br>- To make the delivery targets unavailable for<br>use to the Data Users that order the data<br>collection, enter` INACTIVE`.|=|
|`isDefault`|Optional. Configure the delivery target as the<br>default delivery option for a data collection.<br>Enter one of the following values:<br>- To configure the delivery target as the<br>default delivery option for a data collection,<br>enter` true`.<br>- To not configure the delivery target as the<br>default delivery option for a data collection,<br>enter` false`.|-|
|`targetSystemReference`|Optional. Enter the target system or resource<br>reference from where the data is obtained.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`physicalLocation `|Optional. Enter the location where the data is<br>delivered to a Data User.|If the value of the<br>` managedAccess ` parameter is<br>` ENABLED` for the delivery<br>template that you specified in<br>the` deliveryTemplateId `<br>parameter, the data won't be<br>delivered to the location that you<br>specify in the<br>` physicalLocation` parameter.<br>Instead, the data will be<br>delivered to a location that is<br>generated at the time of order<br>fulfillment. The generated<br>location is unique to each order<br>that is fulfilled using this target.<br>For more information, see the<br>_Manage access to data with Data_<br>_Access Management_topic in the<br>_Set Up Data Marketplace_help.<br>For more information about how<br>you can use APIs to manage<br>delivery templates, see<br>“Delivery templates” on page 148.|
|`deliveryTemplateId`|Optional. Enter the system generated unique<br>identifier of the delivery template that you want<br>to use to for the delivery target.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a delivery template,<br>see<br>“Retrieve delivery templates” on page 152.|
|`deliveryMethodId `|Optional. Enter the system generated unique<br>identifier of the delivery method that is used in<br>the delivery template that you specified in the<br>` deliveryTemplateId` parameter.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a delivery method,<br>see<br>“Retrieve delivery methods” on page 143.|
|`deliveryFormatId `|Optional. Enter the system generated unique<br>identifier of the delivery format that is used in<br>the delivery template that you specified in the<br>` deliveryTemplateId` parameter.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a delivery format,<br>see<br>“Retrieve delivery formats” on page 136.|


#### Example request
The following example shows how you can use an API to modify a delivery target: 

```
{
```

> `"operation": "replace", "segment": "summary", "value": {` 

> `"name": "Snowflake Data Exchange - US Sales",` 

> `"description": "Snowflake Data Exchange Portal for NA Analytics references and enrichment data sources." }` 

```
}
```


#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the delivery target is successfully modified: 

```
204 OK code
```

#### Delete delivery targets
Use a REST API to delete a delivery target of a data collection. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/delivery-targets/<deliveryTargetId>**|
||`<deliveryTargetId>`: Required. Enter the system-generated unique identifier of the delivery target that<br>you want to delete.|
||For more information about how you can use an API to get the system-generated unique identifier of a<br>delivery target, see<br>“Retrieve all delivery targets” on page 77.|
|Method|DELETE|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to delete a delivery target: 

```
https://{{CDMP_URL}}/api/v2/delivery-targets/67417f72-e5ab-44f0-add9-a1e412c1ce13
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the delivery target is successfully deleted: 

```
204 OK code
```

## Chapter 5: Manage orders and consumer accesses
Use REST APIs to manage the orders and consumer accesses that are associated with a data collection. You can also use APIs to manage data collection requests. 

The following table lists the tasks you can perform related to orders, consumer accesses and data collection requests: 

|**Tasks**|**Topic**|**Authentication Method**|
|---|---|---|
|Manage orders|See “Orders” on page 85.|JWT|
|Manage consumer accesses|See “Consumer accesses” on page 104.|JWT|
|Make a consumer access<br>available|See “Make consumer accesses available” on page 326.|Application Integration|
|Manage data collection<br>requests|See “Data collection requests” on page 258.|Application Integration|


### Orders
An order is a request made by a Data User to gain access to a data collection. A Data User's order is reviewed by the stakeholders of the data collection who are responsible for the approval and fulfillment of the order.

#### Create orders
Use a REST API to create an order in Data Marketplace. You can use this API to create orders only for yourself, not on behalf of other users. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/orders**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using JWT” on page 12.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`externalId`|Optional. Enter a reference identifier for the<br>order that you want to create.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique<br>value to the item. The reference<br>identifier that Data Marketplace<br>automatically generates contains<br>a prefix. The administrator can<br>specify the prefix of the<br>automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a<br>unique value. Ensure that you<br>don't use the prefix value that is<br>configured in Metadata<br>Command Center.|
|`dataCollectionId `|Required. Enter the system-generated unique<br>identifier of the data collection for which you<br>want to create the order.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a data collection,<br>see<br>“Retrieve all data collections” on page 51.<br>To get the system-generated<br>unique identifier of a data<br>collection from the Data<br>Marketplace user interface, open<br>the data collection. The data<br>collection page's URL contains<br>the unique identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?`<br>`dtn=&tab=summary `, the unique<br>identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`usageContextId`|Optional. Enter the system-generated unique<br>identifier of the usage type that you want to<br>use to specify the context in which you intend<br>to use the data after you receive access to the<br>collection.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a usage type, see<br>“Retrieve usage type” on page 174.|
|`justification`|Required. Enter a brief description of how you<br>intend to use the data after you receive access<br>to the collection.|-|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`deliveryRequests`|Optional. Enter any additional information that<br>pertains to your order.<br>For example, you can specify the urgency or<br>the preferred delivery method in this field.|-|
|`requestedProvisionedTar `<br>` getRef `|Optional. Enter the system-generated unique<br>identifier of the delivery target that you want a<br>stakeholder to use to deliver the data.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a delivery target, see<br>“Retrieve all delivery targets” on page 77.<br>If you don't specify a value for<br>the<br>` requestedProvisionedTarge `<br>` tRef` parameter, the order is<br>created without a specific<br>delivery target but rather uses<br>the default delivery template.<br>For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a delivery template,<br>see<br>“Retrieve delivery templates” on page 152.|
|`costCenter`|Optional. Enter your cost center.|-|
|`customAttributes`|Specify the custom attribute values for the<br>order.|Whether you must enter a value<br>in a custom attribute or not is<br>determined by how the custom<br>attribute was defined by your<br>administrator in Metadata<br>Command Center.<br>Custom attributes are additional<br>properties for Data Marketplace<br>items that are defined by your<br>administrator in Metadata<br>Command Center. For more<br>information about custom<br>attributes, see the_Create custom_<br>_attributes for items_topic in the<br>_Set Up Data Marketplace_help.|
|`customAttributes >`<br>`value`|Enter a value for the custom attribute.|-|
|`customAttributes > id`|Enter the system-generated unique identifier of<br>the custom attribute.|For more information about how<br>you can retrieve the system-generated unique identifier of a<br>custom attribute, see<br>“Retrieve custom attributes” on page 353.|

#### Example request
The following example shows how you can use an API to create an order: 

```
{
  "externalId": "ORD-1",
  "dataCollectionId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "usageContextId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",

  "justification": "Need customer analytics data for Q4 planning and market analysis to
improve product recommendations",
```

- `"deliveryRequests": "Data should be delivered in Parquet format to S3 bucket s3:// analytics-bucket/orders/ on a weekly basis",` 

```
  "requestedProvisionedTargetRef": "b93c4539-a93e-440d-a4b4-95d69b8911e6",

  "costCenter": "US-IT-2025",

  "customAttributes": [
    {
      "value": "PROJ-2024-CUSTOMER-INSIGHTS",
      "id": "com.infa.odin.models.custom.ca_2491832487117218775"
    }
  ]
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create an order that requires manual approval and fulfillment by a stakeholder: 

```
{
  "id": "d93c4539-a93e-440d-a4b4-95d69b8911e7",
  "externalId": "ORD-1",
  "justification": "Need customer analytics data for Q4 planning and market analysis to
improve product recommendations",

  "deliveryRequest": "Data should be delivered in Parquet format to S3 bucket s3://
analytics-bucket/orders/ on a weekly basis",
  "requestedProvisionedTargetRef": "b93c4539-a93e-440d-a4b4-95d69b8911e6",

  "costCenter": "US-IT-2025",
  "status": "PENDING",
  "assetGroups": [
    {
      "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
      "name": "ASIA",
      "isInherited": true
    }
  ]

}
```

The following table describes the parameters of each order that is created that requires manual approval and fulfillment by a stakeholder: 

|**Parameter**|**Description**|
|---|---|
|`id`|System-generated unique identifier of the order.|
|`externalId`|Reference identifier of the order.|
|`justification`|The reason provided at the time of order for requesting access to the data.|
|`deliveryRequests`|Additional information that pertains to the order.|
|`requestedProvisionedTargetRef`|The delivery target requested by the Data User.|


|**Parameter**|**Description**|
|---|---|
|`costCenter`|Cost center of the Data User that ordered the data collection.|
|`status `|Status of the order. An order can have one of the following statuses:<br>-<br>` PENDING `. The order is submitted.<br>-<br>` APPROVED `. The order is approved for fulfillment. A stakeholder of the<br>ordered data collection must deliver the data to the Data User.<br>-<br>` REJECTED `. The order is rejected by a stakeholder of the ordered data<br>collection.<br>-<br>` COMPLETE `. The ordered data is delivered to the Data User.<br>-<br>` CANCELLED`. The order is cancelled.<br>An order that requires manual approval and fulfillment by a stakeholder is<br>created with` PENDING` status.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the<br>ordered data collection.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups > isInherited `|Indicates whether the asset group is directly assigned to the category or if<br>the asset group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


The following example shows the response of an API call to create an order for which the approval and fulfillment is automated: 

```
{
  "id": "d93c4539-a93e-440d-a4b4-95d69b8911e7",
  "externalId": "ORD-1",
  "justification": "Need customer analytics data for Q4 planning and market analysis to
improve product recommendations",
  "deliveryRequest": "Data should be delivered in Parquet format to S3 bucket s3://
analytics-bucket/orders/ on a weekly basis",
  "requestedProvisionedTargetRef": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "costCenter": "US-IT-2025",
  "status": "COMPLETE",
  "assetGroups": [
    {
      "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
      "name": "ASIA",
      "isInherited": true
    }
  ],
  "consumerAccess": {
    "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
    "externalId": "CAS-123",
    "costCenter": "IT-DEPT-001",
    "status": "AVAILABLE",
    "accessGrantedOn": "2025-08-01",
    "assetGroups": [
      {
        "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
        "name": "ASIA",
        "isInherited": true
      }
    ],
    "_links": {

      "self": {
        "href": "/consumer-accesses/b93c4539-a93e-440d-a4b4-95d69b8911e6"
      }
    }
  }
}
```

The following table describes the parameters of each order that is created for which the approval and fulfillment is automated: 

|**Parameter**|**Description**|
|---|---|
|`id`|System-generated unique identifier of the order.|
|`externalId`|Reference identifier of the order.|
|`justification`|The reason provided at the time of order for requesting access to the data.|
|`deliveryRequests`|Additional information that pertains to the order.|
|`requestedProvisionedTargetRef`|The delivery target requested by the Data User.|
|`costCenter`|Cost center of the Data User that ordered the data collection.|
|`status `|Status of the order. An order can have one of the following statuses:<br>-<br>` PENDING `. The order is submitted.<br>-<br>` APPROVED `. The order is approved for fulfillment. A stakeholder of the<br>ordered data collection must deliver the data to the Data User.<br>-<br>` REJECTED `. The order is rejected by a stakeholder of the ordered data<br>collection.<br>-<br>` COMPLETE `. The ordered data is delivered to the Data User.<br>-<br>` CANCELLED `. The order is cancelled.<br>An order for which the approval and fulfillment is automated is created with<br>` COMPLETE` status.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the<br>ordered data collection.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups > isInherited `|Indicates whether the asset group is directly assigned to the category or if<br>the asset group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|
|`consumerAccess`|Details of the consumer access that is created after the order is fulfilled.|


#### Retrieve an order
Use a REST API to retrieve the details of an individual order in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/orders/<orderId>**|
||`<orderId>`: Required. Enter the system-generated identifier of the order for which you want to retrieve the<br>details.|
||For more information about how you can use an API to get the system-generated unique identifier of an<br>order, see<br>“Retrieve all orders” on page 94.|
||To get the system-generated unique identifier of a order from the Data Marketplace user interface, open<br>the order. The order page's URL contains the unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848c-b6821fc33f74?dtn=Order~579b`, the unique identifier is` 3d48daf6-5e75-4e1a-848c-b6821fc33f74`.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details about the order.<br>-<br>` dataCollection `. Returns the details about<br>the ordered data collection.<br>-<br>` customAttributes `. Returns the details<br>about the custom attributes that are<br>configured for the order.<br>-<br>` stakeholdership `. Returns the type of user<br>and role that are assigned as stakeholder on<br>the ordered data collection.<br>-<br>` systemAttributes `. Returns the details<br>about the order's creation and modification,<br>such as the date when the order was placed,<br>the latest date when it was modified, and so<br>on.<br>-<br>` terms `. Returns the details about the terms<br>of use snapshot that represents the state of<br>the terms of use of the data collection when<br>the order was placed.<br>-<br>` usageContext`. Returns the details about<br>the usage type that the Data User selected at<br>the time of order.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the order such<br>as the ordered data collection,<br>business justification and so on.|


**Note:** The API has no payload.

#### Example request
The following example shows how you can use an API to retrieve the details of an order: 

```
https://{{CDMP_URL}}/api/v2/orders/2593524d-82f7-4dfd-bc6b-17088c046f9d
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of an order: 

```
{
  "externalId": "ORD-1",
  "dataCollectionId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "justification": "Need customer analytics data for Q4 planning and market analysis to
improve product recommendations",
  "deliveryRequests": "Data should be delivered in Parquet format to S3 bucket s3://
analytics-bucket/orders/ on a weekly basis",
  "requestedProvisionedTargetRef": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "costCenter": "US-IT-2025",
  "assetGroups": [
    {
      "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
      "name": "ASIA",
      "isInherited": true
    }
  ]
}
```

The following table describes the parameters of the order that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`externalId`|Reference identifier of the order.|
|`dataCollectionId`|System-generated unique identifier of the ordered data collection.|
|`justification`|The reason provided at the time of order for requesting access to the data.|
|`deliveryRequests`|Additional information that pertains to the order.|
|`requestedProvisionedTargetRef`|The delivery target requested by the Data User.|
|`costCenter`|Cost center of the Data User that ordered the data collection.|
|`status `|Status of the order. An order can have one of the following statuses:<br>-<br>` PENDING `. The order is submitted.<br>-<br>` APPROVED `. The order is approved for fulfillment. A stakeholder of the<br>ordered data collection must deliver the data to the Data User.<br>-<br>` REJECTED `. The order is rejected by a stakeholder of the ordered data<br>collection.<br>-<br>` COMPLETE `. The ordered data is delivered to the Data User.<br>-<br>` CANCELLED`. The order is cancelled.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the<br>ordered data collection.|
|`assetGroups > id`|System-generated identifier of the asset group.|


|**Parameter**|**Description**|
|---|---|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups > isInherited `|Indicates whether the asset group is directly assigned to the category or if<br>the asset group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


#### Retrieve all orders
Use a REST API to retrieve the details of all orders in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/orders/**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12.

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details about each order<br>that is retrieved.<br>-<br>` dataCollection `. Returns the details about<br>the ordered data collection.<br>-<br>` customAttributes `. Returns the details<br>about the custom attributes that are<br>configured for an order.<br>-<br>` stakeholdership `. Returns the type of user<br>and role that are assigned as stakeholder on<br>the ordered data collection.<br>-<br>` systemAttributes `. Returns the details<br>about an order's creation and modification,<br>such as the date when the order was placed,<br>the latest date when it was modified, and so<br>on.<br>-<br>` terms `. Returns the details about the terms<br>of use snapshot that represents the state of<br>the terms of use of the data collection when<br>the order was placed.<br>-<br>` usageContext`. Returns the details about<br>the usage type that the Data User selected at<br>the time of order.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the order such<br>as the ordered data collection,<br>business justification and so on.|
|`offset`|Optional. Specify the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Specify the maximum number of<br>results that are displayed on a page.|Default is` 20`.<br>Maximum is` 200`.|
|`sortby `|Optional. Specify the parameters to sort the<br>search results.<br>Enter one of the following values:<br>-<br>` id `<br>-<br>` justification `<br>-<br>` status `<br>-<br>` createdBy `<br>-<br>` createdOn `<br>-<br>` modifiedBy `<br>-<br>` modifiedOn`|Default is` modifiedOn`.|
|`sortOrder`|Optional. Set the sorting order of the search<br>results.<br>Enter one of the following values:<br>- To sort the search results by ascending<br>order, enter` asc`.<br>- To sort the search results by descending<br>order, enter` desc`.|Default is` desc`.|


**Note:** The API has no payload. 


#### Example request
The following example shows how you can use an API to retrieve the details of all orders: 

```
https://{{CDMP_URL}}/api/v2/orders
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of all orders: 

```
{
  "pageInfo": {
    "offset": 40,
    "limit": 20,
    "totalCount": 120
  },
  "links": {
    "self": {
      "href": "/orders?offset=40&limit=20"
    },
    "first": {
      "href": "/orders?offset=0&limit=20"
    },
    "next": {
      "href": "/orders?offset=60&limit=20"
    },
    "previous": {
      "href": "/orders?offset=20&limit=20"
    },
    "last": {
      "href": "/orders?offset=100&limit=20"
    }
  },
  "items": {
    "externalId": "ORD-1",
    "dataCollectionId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
    "justification": "Need customer analytics data for Q4 planning and market analysis
to improve product recommendations",
    "deliveryRequests": "Data should be delivered in Parquet format to S3 bucket s3://
analytics-bucket/orders/ on a weekly basis",
    "requestedProvisionedTargetRef": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
    "costCenter": "US-IT-2025",
    "assetGroups": [
      {
        "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
        "name": "ASIA",
        "isInherited": true
      }
    ]
  }
}
```

The following table describes the parameters of each order that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`pageInfo > offset`|Starting index of the paginated results.|
|`pageInfo > limit`|The maximum number of results that are displayed on a page.|
|`pageInfo > totalCount`|Number of orders retrieved.|
|`links > self > href`|The API that was invoked to generate this response.|

|**Parameter**|**Description**|
|---|---|
|`links > first > href`|The API endpoint that you can use to retrieve the search results that are<br>displayed on the first page.|
|`links > next > href`|The API endpoint that you can use to retrieve the search results from the<br>page following the one you currently have open.|
|`links > previous > href`|The API endpoint that you can use to retrieve the search results from the<br>page prior to the one you currently have open.|
|`links > last > href`|The API endpoint that you can use to retrieve the search results that are<br>displayed on the last page.|
|`externalId`|Reference identifier of the order.|
|`dataCollectionId`|System-generated unique identifier of the ordered data collection.|
|`justification`|The reason provided at the time of order for requesting access to the data.|
|`deliveryRequests`|Additional information that pertains to the order.|
|`requestedProvisionedTargetRef`|The delivery target requested by the Data User.|
|`costCenter`|Cost center of the Data User that ordered the data collection.|
|`status `|Status of the order. An order can have one of the following statuses:<br>-<br>` PENDING `. The order is submitted.<br>-<br>` APPROVED `. The order is approved for fulfillment. A stakeholder of the<br>ordered data collection must deliver the data to the Data User.<br>-<br>` REJECTED `. The order is rejected by a stakeholder of the ordered data<br>collection.<br>-<br>` COMPLETE `. The ordered data is delivered to the Data User.<br>-<br>` CANCELLED`. The order is cancelled.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the<br>ordered data collection.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups > isInherited `|Indicates whether the asset group is directly assigned to the category or if<br>the asset group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


#### Approve orders
Use REST APIs to approve orders in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v2/orders/<orderId>/approve** `<orderId>` : Required. Enter the system-generated identifier of the order that you want to approve. For more information about how you can use an API to get the system-generated unique identifier of an order, see “Retrieve all orders” on page 94. To get the system-generated unique identifier of a order from the Data Marketplace user interface, open the order. The order page's URL contains the unique identifier. For example, in the URL `https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848cb6821fc33f74?dtn=Order~579b ` , the unique identifier is ` 3d48daf6-5e75-4e1a-848cb6821fc33f74` . Method PATCH 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`comment`|Optional. Enter a comment about the<br>action taken.|-|
|`costCenter`|Optional. Specify the cost center of<br>the Data User that placed the order<br>or modify the cost center value<br>specified by the Data User.|-|
|`customAttributes`|Specify the custom attribute values<br>for the order or modify the custom<br>attribute values specified by the Data<br>User at the time of order.|Whether you must enter a value in a<br>custom attribute or not is determined<br>by how the custom attribute was<br>defined by your administrator in<br>Metadata Command Center.<br>Custom attributes are additional<br>properties for Data Marketplace<br>items that are defined by your<br>administrator in Metadata Command<br>Center. For more information about<br>custom attributes, see the_Create_<br>_custom attributes for items_topic in<br>the_Set Up Data Marketplace_help.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`customAttributes > value`|Enter the updated value for the<br>custom attribute.|-|
|`customFields > id`|Enter the system-generated unique<br>identifier of the custom attribute that<br>you want to modify.|For more information about how you<br>can retrieve the system-generated<br>unique identifier of a custom<br>attribute, see<br>“Retrieve custom attributes” on page 353.|


#### Example request
The following example shows how you can use an API to approve an order: 

```
https://{{CDMP_URL}}/api/v2/orders/2593524d-82f7-4dfd-bc6b-17088c046f9d/approve
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the order is successfully approved: 

```
204 OK code
```

#### Fulfill orders
Use REST APIs to mark orders as fulfilled in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/orders/<orderId>/fulfill**<br>`<orderId>`: Required. Enter the system-generated identifier of the order that you want to mark as<br>fulfilled.|
||For more information about how you can use an API to get the system-generated unique identifier of an<br>order, see<br>“Retrieve all orders” on page 94.|
||To get the system-generated unique identifier of a order from the Data Marketplace user interface, open<br>the order. The order page's URL contains the unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848c-b6821fc33f74?dtn=Order~579b`, the unique identifier is` 3d48daf6-5e75-4e1a-848c-b6821fc33f74`.|
|Method|PATCH|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`comment`|Optional. Enter a comment about the<br>action taken.|-|
|`deliveryTargetId`|Required. Enter the system<br>generated unique identifier of the<br>delivery target that you want to use<br>to deliver the data.|For more information about how you<br>can use an API to get the system-generated unique identifier of a<br>delivery target, see<br>“Retrieve all delivery targets” on page 77.|
|`costCenter`|Optional. Specify the cost center of<br>the Data User that placed the order<br>or modify the cost center value<br>specified by the Data User.|-|
|`customAttributes`|Specify the custom attribute values<br>for the order or modify the custom<br>attribute values specified by the Data<br>User at the time of order.|Whether you must enter a value in a<br>custom attribute or not is determined<br>by how the custom attribute was<br>defined by your administrator in<br>Metadata Command Center.<br>Custom attributes are additional<br>properties for Data Marketplace<br>items that are defined by your<br>administrator in Metadata Command<br>Center. For more information about<br>custom attributes, see the_Create_<br>_custom attributes for items_topic in<br>the_Set Up Data Marketplace_help.|
|`customAttributes > value`|Enter the updated value for the<br>custom attribute.|-|
|`customFields > id`|Enter the system-generated unique<br>identifier of the custom attribute that<br>you want to modify.|For more information about how you<br>can retrieve the system-generated<br>unique identifier of a custom<br>attribute, see<br>“Retrieve custom attributes” on page 353.|


#### Example request
The following example shows how you can use an API to mark an order as fulfilled: 

```
{

  "comment": "Order fulfilled.",

  "deliveryTargetId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",

  "costCenter": "MARKETING-2025",

  "customAttributes": [
    {

      "value": "PROJ-2024-CUSTOMER-INSIGHTS",

      "id": "com.infa.odin.models.custom.ca_2491832487117218775"
    }
  ]
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following table describes the parameters of each order that is marked as fulfilled: 

|`{`<br>`"id": "d93c4539`<br>`"externalId": "`<br>`"costCenter": "`<br>`"status": "AVAI`<br>`"accessGrantedO`<br>`"consumer":`<br>`{`<br>`"stakehold`<br>`"userType"`<br>`"roleId":`<br>`"isInherit`<br>`},`<br>`"assetGroups":`<br>`{`<br>`"id": "c1a`<br>`"name": "A`<br>`"isInherit`<br>`}`<br>`]`<br>`}`|`-a93e-440d-a4b4-95d69b8933e6",`<br>`CAS-123",`<br>`IT-DEPT-001",`<br>`LABLE",`<br>`n": "2025-08-01",`<br>`erId": "jaXM6NrqsKXffdwg2NkTMR",`<br>`: "USER",`<br>`"Stakeholder_Role_Marketplace_Editor",`<br>`ed": false`<br>`[`<br>`86a0e-7690-4577-99ab-be4b193e7d90",`<br>`SIA",`<br>`ed": true`|
|---|---|
|**Parameter**|**Description**|
|`id`|System-generated unique identifier of the consumer access that is created upon order<br>fulfillment.|
|`externalId`|Reference identifier of the consumer access.|
|`costCenter`|Cost center of the Data User that was granted access to the data collection.|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to withdraw the Data User's access to the<br>data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the data collection.|
|`consumer`|Details about the Data User that was granted access to the data collection.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data<br>collection that is associated with consumer access.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset<br>group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


#### Cancel orders
Use REST APIs to cancel orders in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v2/orders/<orderId>/cancel** `<orderId>` : Required. Enter the system-generated identifier of the order that you want to cancel. For more information about how you can use an API to get the system-generated unique identifier of an order, see “Retrieve all orders” on page 94. To get the system-generated unique identifier of a order from the Data Marketplace user interface, open the order. The order page's URL contains the unique identifier. For example, in the URL `https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848cb6821fc33f74?dtn=Order~579b ` , the unique identifier is ` 3d48daf6-5e75-4e1a-848cb6821fc33f74` . Method PATCH 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


#### Example request
The following example shows how you can use an API to cancel an order: 

```
https://{{CDMP_URL}}/api/v2/orders/2593524d-82f7-4dfd-bc6b-17088c046f9d/cancel
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the order is successfully cancelled: 

```
204 OK code
```

#### Reject orders
Use REST APIs to reject orders in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v2/orders/<orderId>/reject** `<orderId>` : Required. Enter the system-generated identifier of the order that you want to reject. For more information about how you can use an API to get the system-generated unique identifier of an order, see “Retrieve all orders” on page 94. To get the system-generated unique identifier of a order from the Data Marketplace user interface, open the order. The order page's URL contains the unique identifier. For example, in the URL `https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848cb6821fc33f74?dtn=Order~579b ` , the unique identifier is ` 3d48daf6-5e75-4e1a-848cb6821fc33f74` . Method PATCH 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


#### Example request
The following example shows how you can use an API to reject an order: 

```
https://{{CDMP_URL}}/api/v2/orders/2593524d-82f7-4dfd-bc6b-17088c046f9d/reject
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the order is successfully rejected: 

```
204 OK code
```


#### Delete orders
If you placed an order accidentally or if an order is obsolete, you can use a REST API to delete the order. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v2/orders/<orderId>** `<orderId>` : Required. Enter the system-generated identifier of the order that you want to delete. For more information about how you can use an API to get the system-generated unique identifier of an order, see “Retrieve all orders” on page 94. To get the system-generated unique identifier of a order from the Data Marketplace user interface, open the order. The order page's URL contains the unique identifier. For example, in the URL `https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848cb6821fc33f74?dtn=Order~579b ` , the unique identifier is ` 3d48daf6-5e75-4e1a-848cb6821fc33f74` . Method DELETE 

**Note:** To delete a fulfilled order, ensure that you have already deleted its associated consumer access. For more information about how you can delete a consumer access, see “Delete consumer accesses” on page 120. 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to delete an order: 

```
https://{{CDMP_URL}}/api/v2/orders/2593524d-82f7-4dfd-bc6b-17088c046f9d
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the order is successfully deleted: 

```
204 OK code
```

### Consumer accesses
A consumer access is a record that indicates a Data User's access to a data collection, along with other details such as the date of delivery, details of the stakeholder that delivered the data, and the delivery option that was used for the delivery.

#### Create consumer accesses
Use a REST API to create a consumer access in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/consumer-accesses**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`externalId`|Optional. Enter a reference identifier for the<br>consumer access that you want to create.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique<br>value to the item. The reference<br>identifier that Data Marketplace<br>automatically generates contains<br>a prefix. The administrator can<br>specify the prefix of the<br>automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a<br>unique value. Ensure that you<br>don't use the prefix value that is<br>configured in Metadata<br>Command Center.|
|`status `|Optional. Specify a status for the consumer<br>access.<br>Enter one of the following values:<br>- For a consumer access where the Data User<br>has access to the data collection, enter<br>` AVAILABLE`.<br>- For a consumer access that is awaiting<br>withdrawal, enter` PENDING_WITHDRAW`.<br>- For a consumer access that is withdrawn,<br>enter` WITHDRAWN`.<br>Default is` AVAILABLE`.|-|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`dataCollectionId `|Required. Enter the system-generated unique<br>identifier of the data collection to which the<br>Data User was granted access.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a data collection,<br>see<br>“Retrieve all data collections” on page 51.<br>To get the system-generated<br>unique identifier of a data<br>collection from the Data<br>Marketplace user interface, open<br>the data collection. The data<br>collection page's URL contains<br>the unique identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?`<br>`dtn=&tab=summary `, the unique<br>identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`deliveryTargetId`|Required. Enter the system-generated unique<br>identifier of the delivery target that was used<br>to deliver the data.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a delivery target, see<br>“Retrieve all delivery targets” on page 77.|
|`accessGrantedOn`|Optional. Enter the date on which the Data User<br>was granted access to the data collection.|To specify a date, use the` YYYY-MM-DD` format. The value that<br>you specify is automatically<br>converted and stored in the<br>Coordinated Universal Time<br>(UTC) time standard.|
|`consumerUserId`|Required. Enter the system-generated unique<br>identifier of the user account that was granted<br>access to the data collection.|To get the system-generated<br>unique identifier of a user<br>account, click**My Services >**<br>**Administrator > Users**. On the<br>**Users**page, click a user account.<br>The user account page's URL<br>contains the unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/usersAsset/`<br>`0LH3xBJC9A6haZ26htGZyT `, the<br>unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`usageContextId`|Optional. Enter the system-generated unique<br>identifier of the usage type used to specify the<br>context in which the data is to be used as<br>provided by the Data User that was granted<br>access to the collection.|For more information about how<br>you can use an API to get the<br>system-generated unique<br>identifier of a usage type, see<br>“Retrieve usage type” on page 174.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`costCenter`|Optional. Enter the cost center of the Data User<br>that was granted access to the data collection.|-|
|`customAttributes`|Specify the custom attribute values for the<br>consumer access.|Whether you must enter a value<br>or not enter a value in a custom<br>attribute is determined by how<br>the custom attribute was defined<br>by your administrator in<br>Metadata Command Center.<br>Custom attributes are additional<br>properties for Data Marketplace<br>items that are defined by your<br>administrator in Metadata<br>Command Center. For more<br>information about custom<br>attributes, see the_Create custom_<br>_attributes for items_topic in the<br>_Set Up Data Marketplace_help.|
|`customAttributes >`<br>`value`|Enter a value in the custom attribute.|-|
|`customFields > id`|Enter the system-generated unique identifier of<br>the custom attribute.|For more information about how<br>you can retrieve the system-generated unique identifier of a<br>custom attribute, see<br>“Retrieve custom attributes” on page 353.|


#### Example request
The following example shows how you can use an API to create a consumer access: 

```
{
  "externalId": "CAS-123",
  "status": "AVAILABLE",
  "dataCollectionId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "deliveryTargetId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "accessGrantedOn": "2025-08-01",
  "consumerUserId": "jqPFaKmJRGhdfJ35eWUzoW",
  "usageContextId": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "costCenter": "string",
  "customAttributes": [
    {
      "value": "PROJ-2024-CUSTOMER-INSIGHTS",
      "id": "com.infa.odin.models.custom.ca_2491832487117218775"
    }
  ]
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a consumer access: 

```
{
  "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "externalId": "CAS-123",

  "costCenter": "IT-DEPT-001",

  "status": "AVAILABLE",
```


```
  "accessGrantedOn": "2025-08-01",
  "consumer":
    {
      "stakeholderId": "jaXM6NrqsKXffdwg2NkTMR",
      "userType": "USER",
      "roleId": "Stakeholder_Role_Marketplace_Editor",
      "isInherited": false
    },
  "assetGroups": [
    {
      "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
      "name": "ASIA",
      "isInherited": true
    }
  ]
}
```

The following table describes the parameters of each consumer access that is created: 

|**Parameter**|**Description**|
|---|---|
|`id`|System-generated unique identifier of the consumer access.|
|`externalId`|Reference identifier of the consumer access.|
|`costCenter`|Cost center of the Data User that was granted access to the data collection.|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to withdraw the Data User's access to the<br>data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the data collection.|
|`consumer`|Details about the Data User that was granted access to the data collection.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data<br>collection that is associated with the consumer access.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset<br>group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|

#### Retrieve a consumer access
Use a REST API to retrieve the details of an individual consumer access in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/consumer-accesses/<consumerAccessId>**<br>`<consumerAccessId>`: Required. Enter the system-generated identifier of the consumer access for which<br>you want to retrieve the details.|
||For more information about how you can use an API to get the system-generated unique identifier of a<br>consumer access, see<br>“Retrieve all consumer accesses” on page 111.|
||To get the system-generated unique identifier of a consumer access from the Data Marketplace user<br>interface, open the consumer access. The consumer access page's URL contains the unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/access/e254491f-5795-49bd-be0b-385eb11d9d5a?dtn=Access~2e85`, the unique identifier is` e254491f-5795-49bd-be0b-385eb11d9d5a`.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details about the<br>consumer access.<br>-<br>` customAttributes `. Returns the details<br>about the custom attributes that are<br>configured for the consumer access.<br>-<br>` dataCollection `. Returns the details about<br>the data collection that is associated with<br>the consumer access.<br>-<br>` deliveryTarget `. Returns the details about<br>the delivery target that was used to deliver<br>the data.<br>-<br>` order `. Returns the details about the order<br>for which the consumer access is created.<br>-<br>` systemAttributes `. Returns the details<br>about the consumer access's creation and<br>modification, such as the date when the<br>consumer access was granted, the latest<br>date when it was modified, and so on.<br>-<br>` terms `. Returns the details about the terms<br>of use snapshot that represents the state of<br>the terms of use of the data collection when<br>the order was placed.<br>-<br>` usageContext`. Returns the details about<br>the usage type that the Data User selected at<br>the time of order.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the consumer<br>access such as status, cost<br>center and so on.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of a consumer access: 

```
https://{{CDMP_URL}}/api/v2/consumer-accesses/2593524d-82f7-4dfd-bc6b-17088c046f9d
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a consumer access: 

```
{
  "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
  "externalId": "CAS-123",
  "costCenter": "IT-DEPT-001",
  "status": "AVAILABLE",
  "accessGrantedOn": "2025-08-01",
  "consumer":
    {
      "stakeholderId": "jaXM6NrqsKXffdwg2NkTMR",
      "userType": "USER",
      "roleId": "Stakeholder_Role_Marketplace_Editor",
      "isInherited": false
    },
  "assetGroups": [

    {
      "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
      "name": "ASIA",
      "isInherited": true
    }
  ]
}
```

The following table describes the parameters of the consumer access that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`id`|System-generated unique identifier of the consumer access.|
|`externalId`|Reference identifier of the consumer access.|
|`costCenter`|Cost center of the Data User that was granted access to the data collection.|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to withdraw the Data User's access to the<br>data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the data collection.|
|`consumer`|Details about the Data User that was granted access to the data collection.|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data<br>collection that is associated with consumer access.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset<br>group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


#### Retrieve all consumer accesses
Use a REST API to retrieve the details of all consumer accesses in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/consumer-accesses**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`offset`|Optional. Specify the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Specify the maximum number of<br>results that are displayed on a page.|Default is` 20`.<br>Maximum is` 200`.|
|`segments `|Optional. Specify the type of details that you<br>want the API request to return.<br>Enter one or more of the following values:<br>-<br>` all `. Returns all the details about each<br>consumer access that is retrieved.<br>-<br>` customAttributes `. Returns the details<br>about the custom attributes that are<br>configured for a consumer access.<br>-<br>` dataCollection `. Returns the details about<br>the data collection that is associated with a<br>consumer access.<br>-<br>` deliveryTarget `. Returns the details about<br>the delivery target that was used to deliver<br>the data.<br>-<br>` order `. Returns the details about the order<br>for which the consumer access is created.<br>-<br>` usageContext `. Returns the details about<br>the usage type that the Data User selected at<br>the time of order.<br>-<br>` terms `. Returns the details about the terms<br>of use snapshot that represents the state of<br>the terms of use of the data collection when<br>the order was placed.<br>-<br>` systemAttributes`. Returns the details<br>about a consumer access's creation and<br>modification, such as the date when the<br>consumer access was granted, the latest<br>date when it was modified, and so on.|By default, the parameter is<br>assigned no value and the API<br>response includes only the basic<br>information about the consumer<br>access such as status, cost<br>center and so on.|
|`sortBy `|Optional. Specify the parameters to sort the<br>search results.<br>Enter one of the following values:<br>-<br>` accessGrantedOn `<br>-<br>` createdOn `<br>-<br>` modifiedOn`|Default is` modifiedOn`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortOrder`|Optional. Set the sorting order of the search<br>results.<br>Enter one of the following values:<br>- To sort the search results by ascending<br>order, enter` asc`.<br>- To sort the search results by descending<br>order, enter` desc`.|Default is` desc`.|
|`status `|Optional. Specify the status of the consumer<br>access.<br>Enter one of the following values:<br>- To find consumer accesses where the Data<br>User has access to the data collection, enter<br>` AVAILABLE`.<br>- To find consumer accesses that are pending<br>withdrawal, enter` PENDING_WITHDRAW `.<br>- To find consumer accesses that are<br>withdrawn,` WITHDRAWN`.|-|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of all consumer accesses: 

```
https://{{CDMP_URL}}/api/v2/consumer-accesses
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of all consumer accesses: 

```
{
  "pageInfo": {
    "offset": 40,
    "limit": 20,
    "totalCount": 120
  },
  "links": {
    "self": {
      "href": "/consumer-accesses?offset=40&limit=20"
    },
    "first": {
      "href": "/consumer-accesses?offset=0&limit=20"
    },
    "next": {
      "href": "/consumer-accesses?offset=60&limit=20"
    },
    "previous": {
      "href": "/consumer-accesses?offset=20&limit=20"
    },
    "last": {
      "href": "/consumer-accesses?offset=100&limit=20"
    }
  },
  "items": [
    {
      "id": "b93c4539-a93e-440d-a4b4-95d69b8911e6",
      "externalId": "CAS-123",
      "costCenter": "IT-DEPT-001",
```


```
      "status": "AVAILABLE",
      "accessGrantedOn": "2025-08-01",
      "consumer":
         {
           "stakeholderId": "jaXM6NrqsKXffdwg2NkTMR",
           "userType": "USER",
           "roleId": "Stakeholder_Role_Marketplace_Editor",
           "isInherited": false
         },
      "assetGroups": [
         {
           "id": "c1a86a0e-7690-4577-99ab-be4b193e7d90",
           "name": "ASIA",
           "isInherited": true
        }
      ]
    }
  ]
}
```

The following table describes the parameters of each consumer access that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`pageInfo > offset`|Starting index of the paginated results.|
|`pageInfo > limit`|The maximum number of results that are displayed on a page.|
|`pageInfo > totalCount`|Number of consumer accesses retrieved.|
|`links > self > href`|The API that was invoked to generate this response.|
|`links > first > href`|The API endpoint that you can use to retrieve the search results that are displayed on<br>the first page.|
|`links > next > href`|The API endpoint that you can use to retrieve the search results from the page<br>following the one you currently have open.|
|`links > previous >`<br>`href`|The API endpoint that you can use to retrieve the search results from the page prior<br>to the one you currently have open.|
|`links > last > href`|The API endpoint that you can use to retrieve the search results that are displayed on<br>the last page.|
|`id`|System-generated unique identifier of the consumer access.|
|`externalId`|Reference identifier of the consumer access.|
|`costCenter`|Cost center of the Data User that was granted access to the data collection.|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to withdraw the Data User's access to<br>the data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the data collection.|
|`consumer`|Details about the Data User that was granted access to the data collection.|

|**Parameter**|**Description**|
|---|---|
|`assetGroups`|Details of the asset group that is assigned to the category that contains the data<br>collection that is associated with consumer access.|
|`assetGroups > id`|System-generated identifier of the asset group.|
|`assetGroups > name`|Name of the asset group.|
|`assetGroups >`<br>`isInherited `|Indicates whether the asset group is directly assigned to the category or if the asset<br>group is inherited from the category hierarchy.<br>This parameter can have one of the following values:<br>-<br>` true `. The asset group is inherited from the category hierarchy.<br>-<br>` false`. The asset group is directly assigned to the category.|


#### Modify custom attributes of consumer accesses
Use a REST API to modify the custom attributes of a consumer access in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/consumer-accesses/<consumerAccessId>**<br>`<consumerAccessId>`: Required. Enter the system-generated identifier of the consumer access for which<br>you want to modify custom attributes.|
||For more information about how you can use an API to get the system-generated unique identifier of a<br>consumer access, see<br>“Retrieve all consumer accesses” on page 111.|
||To get the system-generated unique identifier of a consumer access from the Data Marketplace user<br>interface, open the consumer access. The consumer access page's URL contains the unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/access/e254491f-5795-49bd-be0b-385eb11d9d5a?dtn=Access~2e85`, the unique identifier is` e254491f-5795-49bd-be0b-385eb11d9d5a`.|
|Method|PATCH|


For more information about how you can call an API, see “Authenticate using JWT” on page 12. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`customAttributes`|Modify the custom attributes of the consumer<br>access.|Whether you must enter a value<br>in a custom attribute or not is<br>determined by how the custom<br>attribute was defined by your<br>administrator in Metadata<br>Command Center.<br>Custom attributes are additional<br>properties for Data Marketplace<br>items that are defined by your<br>administrator in Metadata<br>Command Center. For more<br>information about custom<br>attributes, see the_Create custom_<br>_attributes for items_topic in the<br>_Set Up Data Marketplace_help.|
|`customAttributes >`<br>`value`|Enter the updated value for the custom<br>attribute.|-|
|`customFields > id`|Enter the system-generated unique identifier of<br>the custom attribute that you want to modify.|For more information about how<br>you can retrieve the system-generated unique identifier of a<br>custom attribute, see<br>“Retrieve custom attributes” on page 353.|


#### Example request
The following example shows how you can use an API to modify the custom attributes of a consumer access: 

- `{ "customAttributes": [` 

- `"value": "PROJ-2024-CUSTOMER-INSIGHTS",` 

- `"id": "com.infa.odin.models.custom.ca_2491832487117218775"` 

```
    }
  ]
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the custom attributes of the consumer access are successfully modified successfully: 

- `204 OK code`

#### Submit consumer access withdrawals
Use a REST API to submit a request to withdraw a Data User's access to a data collection. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v2/consumer-accesses/<consumerAccessId>/request-withdrawal** `<consumerAccessId>` : Required. Enter the system-generated identifier of the consumer access for which you want to request a withdrawal. For more information about how you can use an API to get the system-generated unique identifier of a consumer access, see “Retrieve all consumer accesses” on page 111. To get the system-generated unique identifier of a consumer access from the Data Marketplace user interface, open the consumer access. The consumer access page's URL contains the unique identifier. For example, in the URL `https://{{CDMP_URL}}/access/e254491f-5795-49bdbe0b-385eb11d9d5a?dtn=Access~2e85 ` , the unique identifier is ` e254491f-5795-49bdbe0b-385eb11d9d5a ` . Ensure that the consumer access that you specify is in the ` AVAILABLE` state. Method PATCH 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


#### Example request
The following example shows how you can use an API to submit a withdrawal request for a consumer access: 

#### **Request query**
```
https://{{CDMP_URL}}/api/v2/consumer-accesses/794a215f-5479-4b55-8e1b-a4866ec9fe82/
request-withdrawal
```

#### **Request body**
```
{
  "comment": "Requesting withdrawal as user has completed project and no longer
requires access"
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that a request to withdraw the consumer access is successfully submitted: 

```
204 OK code
```


#### Withdraw consumer accesses
Use a REST API to withdraw a Data User's access to a data collection. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v2/consumer-accesses/<consumerAccessId>/withdraw** `<consumerAccessId>` : Required. Enter the system-generated identifier of the consumer access that you want to withdraw. For more information about how you can use an API to get the system-generated unique identifier of a consumer access, see “Retrieve all consumer accesses” on page 111. To get the system-generated unique identifier of a consumer access from the Data Marketplace user interface, open the consumer access. The consumer access page's URL contains the unique identifier. For example, in the URL `https://{{CDMP_URL}}/access/e254491f-5795-49bdbe0b-385eb11d9d5a?dtn=Access~2e85 ` , the unique identifier is ` e254491f-5795-49bdbe0b-385eb11d9d5a ` . Ensure that the consumer access that you specify is in the ` AVAILABLE ` or ` PENDING_WITHDRAW` state. Method PATCH 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


#### Example request
The following example shows how you can use an API to withdraw a consumer access: 

#### **Request query**
```
https://{{CDMP_URL}}/api/v2/consumer-accesses/794a215f-5479-4b55-8e1b-a4866ec9fe82/
withdraw
```

#### **Request body**
```
{
  "comment": "Revoking the access as user has completed project and no longer
requires access"
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the consumer access is successfully withdrawn: 

```
204 OK code
```

#### Cancel consumer access withdrawals
Use a REST API to cancel a withdrawal request that is submitted for a consumer access. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v2/consumer-accesses/<consumerAccessId>/cancel-withdrawal** `<consumerAccessId>` : Required. Enter the system-generated identifier of the consumer access for which you want to cancel the withdrawal request. For more information about how you can use an API to get the system-generated unique identifier of a consumer access, see “Retrieve all consumer accesses” on page 111. To get the system-generated unique identifier of a consumer access from the Data Marketplace user interface, open the consumer access. The consumer access page's URL contains the unique identifier. For example, in the URL `https://{{CDMP_URL}}/access/e254491f-5795-49bdbe0b-385eb11d9d5a?dtn=Access~2e85 ` , the unique identifier is ` e254491f-5795-49bdbe0b-385eb11d9d5a ` . Ensure that the consumer access that you specify is in the ` PENDING_WITHDRAW` state. Method PATCH 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


#### Example request
The following example shows how you can use an API to cancel a withdrawal request that is submitted for a consumer access: 

#### **Request query**
```
https://{{CDMP_URL}}/api/v2/consumer-accesses/794a215f-5479-4b55-8e1b-a4866ec9fe82/
cancel-withdrawal
```

#### **Request body**
```
{
  "comment": "Withdrawal cancelled - access still needed for ongoing project
deliverables"
}
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the consumer access withdrawal request is successfully cancelled: 

```
204 OK code
```


#### Delete consumer accesses
Use a REST API to delete a withdrawn consumer access in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v2/consumer-accesses/<consumerAccessId>**|
||`<consumerAccessId>`: Required. Enter the system-generated identifier of the consumer access that you<br>want to delete.|
||For more information about how you can use an API to get the system-generated unique identifier of a<br>consumer access, see<br>“Retrieve all consumer accesses” on page 111.|
||To get the system-generated unique identifier of a consumer access from the Data Marketplace user<br>interface, open the consumer access. The consumer access page's URL contains the unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/access/e254491f-5795-49bd-be0b-385eb11d9d5a?dtn=Access~2e85`, the unique identifier is` e254491f-5795-49bd-be0b-385eb11d9d5a`.|
|Method|DELETE|


To invoke this API, ensure that your user profile is assigned the Data Marketplace Administrator role. 

Alternatively, ensure that your user profile is assigned a role for which the following privileges and permissions are enabled: 

- **Delete** and **Read** permissions for consumer accesses are enabled in Metadata Command Center. 

- **Access Data Marketplace** privilege is enabled in Administrator. 

For more information about how you can call an API, see “Authenticate using JWT” on page 12. 

#### Request
**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to delete a consumer access: 

```
https://{{CDMP_URL}}/api/v2/consumer-accesses/67417f72-e5ab-44f0-add9-a1e412c1ce13
```

#### Response
When you invoke the API using a REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the consumer access is successfully deleted: 

```
204 OK code
```

# Part III: Authenticate using Application Integration
This part contains the following chapters: 

- Define settings for data collections, 122 

- Manage Data Marketplace content, 189 

- Manage data collections, 212 

- Manage requests, orders and consumer accesses, 258 

- Custom attributes, 353 

- Collaboration on objects, 359 

- Data Marketplace customizations, 363 

- Technical content, 368

## Chapter 6: Define settings for data collections
This chapter includes the following topics: 

- Categories, 122 

- Delivery formats, 134 

- Delivery methods, 141 

- Delivery templates, 148 

- Terms of use, 162 

- Usage types, 172 

- Cost centers, 181 

### Categories
A category is a grouping of related data collections. It is a predefined classification where stakeholders can publish the data collections for which they are responsible. 

#### Create categories
Use a REST API to create categories in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/categories**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`name `|Required. Enter a name for the category.|Ensure that you enter a unique<br>value.<br>Data Marketplace doesn't<br>consider the letter case when it<br>verifies the uniqueness of the<br>` name` parameter's value. For<br>example, if you try to name a<br>category as "Retail Department"<br>while a category called "Retail<br>department" already exists, the<br>API call fails.|
|`description`|Required. Enter a description for the category.|-|
|`dataOwners`|Optional. Enter the system generated unique<br>identifier of the user account or user group<br>that is assigned as a stakeholder on the<br>category with the Category Owner stakeholder<br>role.|- To get the system generated<br>unique identifier of a user<br>account, navigate to**My**<br>**Services > Administrator >**<br>**Users**. On the**Users**page,<br>click a user account. The user<br>account page's URL contains<br>the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/usersAsset/`<br>`0LH3xBJC9A6haZ26htGZyT `,<br>the system generated unique<br>identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system generated<br>unique identifier of a user<br>group, navigate to**My Services**<br>**> Administrator > User**<br>**Groups**. On the**User Groups**<br>page, click a user group. The<br>user group page's URL<br>contains the system generated<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userGroupsAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `,<br>the system generated unique<br>identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|


#### Example request
The following example shows how you can use an API to create a category: 

```
{
  "items": [
    {
      "name": "Retail Department",
      "description": "This category represents the retail department.",
      "dataOwners": [
        "e9paNlaKR8MdFJnETEs2k4"
      ]
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a category: 

```
{
  "processingTime": 10105,
  "objects": [
    {
      "index": 1,
      "id": "2fa40528-410d-4cf6-8ee3-9f3ef328285c",
      "refId": "CAT-414",
      "name": "Retail Department"
    }
  ]
}
```

The following table describes the parameters of each category that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the category in the` objects` JSON array. This value does not impact how the category is<br>used by a Data Marketplace user.|
|`id`|System generated unique identifier of the category.|
|`refId`|Reference identifier of the category.|
|`name`|Name of the category.|


#### Retrieve categories
Use a REST API to retrieve the details of categories in Data Marketplace.

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/categories**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`search`|Optional. Enter the search term that you want<br>to use to find a category.|Ensure that the search term that<br>you enter don't contain an<br>asterisk (*).|
|`fields `|Optional. Enter the fields on which the search<br>term applies. The terms that you entered in the<br>` search ` field parameter are used to search the<br>fields that you specify here.|Enter the following values:<br>-<br>` NAME `<br>-<br>` DESCRIPTION`|
|`ids `|Optional. Enter the system generated unique<br>identifier of a category.|To get the system generated<br>unique identifier of a category,<br>open the category. The category<br>page's URL contains the system<br>generated unique identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-`<br>`a1e412c1ce13&dtn=_AfterEB `<br>` F%20may20 `, the system<br>generated unique identifier is<br>` 67417f72-e5ab-44f0-add9-a1e412c1ce13 `.<br>To enter more than one value,<br>use the following format:<br>` ids=<value1>&ids=<value2>`|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`dataOwners`|Optional. Enter the system generated unique<br>identifier of the user account or user group<br>that is assigned as a stakeholder on the<br>category with the Category Owner stakeholder<br>role.|- To get the system generated<br>unique identifier of a user<br>account, navigate to**My**<br>**Services > Administrator >**<br>**Users**. On the**Users**page,<br>click a user account. The user<br>account page's URL contains<br>the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/usersAsset/`<br>`0LH3xBJC9A6haZ26htGZyT `,<br>the system generated unique<br>identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system generated<br>unique identifier of a user<br>group, navigate to**My Services**<br>**> Administrator > User**<br>**Groups**. On the**User Groups**<br>page, click a user group. The<br>user group page's URL<br>contains the system generated<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userGroupsAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `,<br>the system generated unique<br>identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|
|`status`|Optional. Specify the status of the category.<br>The status determines whether the category is<br>available in Data Marketplace.|Enter one of the following values:<br>- To find categories that are<br>available, enter` ACTIVE`.<br>- To find categories that are<br>unavailable, enter` INACTIVE`.|
|`createdDateFrom`|Optional. To find categories created between a<br>date range, enter the starting date.|To specify a date, use the` YYYY-MM-DD` format. The value that<br>you specify is automatically<br>converted and stored in the<br>Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for<br>the` createdDateFrom `<br>parameter, ensure that you also<br>enter a value for the<br>` createdDateTo` parameter.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`createdDateTo`|Optional. To find categories created between a<br>date range, enter the ending date.|To specify a date, use the` YYYY-MM-DD` format. The value that<br>you specify is automatically<br>converted and stored in the<br>Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for<br>the` createdDateTo` parameter,<br>ensure that you also enter a<br>value for the` createdDateFrom`<br>parameter.|
|`modifiedDateFrom`|Optional. To find categories modified between<br>a date range, enter the starting date.|To specify a date, use the` YYYY-MM-DD` format. The value that<br>you specify is automatically<br>converted and stored in the<br>Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for<br>the` modifiedDateFrom `<br>parameter, ensure that you also<br>enter a value for the<br>` modifiedDateTo` parameter.|
|`modifiedDateTo`|Optional. To find categories modified between<br>a date range, enter the ending date.|To specify a date, use the` YYYY-MM-DD` format. The value that<br>you specify is automatically<br>converted and stored in the<br>Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for<br>the` modifiedDateTo `<br>parameter, ensure that you also<br>enter a value for the<br>` modifiedDateFrom` parameter.|
|`sortByField `|Optional. Specify the parameters to sort the<br>search results.|To sort the search results, enter<br>one of the following values:<br>-<br>` ID `<br>-<br>` NAME `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the search<br>results.|Enter one of the following values:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting index for the<br>paginated results.|Default is` 0`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`limit`|Optional. Enter the maximum number of<br>results.|Default is` 50`.<br>Maximum is` 100`.|
|`rootOnly`|Optional. Specify whether to retrieve only the<br>root category of a hierarchy.|Enter one of the following values:<br>- If you want to retrieve only the<br>root category of a hierarchy<br>and don't want the search<br>results to contain<br>subcategories, enter` true`.<br>- If you don't want to retrieve<br>only the root category of a<br>hierarchy and want the search<br>results to contain<br>subcategories, enter` false`.<br>Default is` false`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of a category: 

```
https://{{CDMP_URL}}/api/v1/integration/categories?status=ACTIVE
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a category: 

```
{
  "processingTime": 369,
  "offset": 0,
  "limit": 50,
  "totalCount": 186,
  "objects": [
    {
      "id": "2fa40528-410d-4cf6-8ee3-9f3ef328285c",
      "refId": "CAT-414",
      "parentId": "g92b4h1e-3rc4-4444-318v-383135d296y6",
      "name": "Retail Department",
      "description": "This category represents the retail department.",
      "status": "ACTIVE",
      "effectiveStatus": "ACTIVE",
      "hasChildCategory": false,
      "dataOwners": [
        {
          "displayName": "cdmp_cat_owner_qa1607",
          "id": "e9paNlaKR8MdFJnETEs2k4",
          "name": "John Doe",
          "email": "johndoe@informatica.com",
          "phone": "8758939746",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": true
        }
      ],
      "createdBy": "f400dUqjPuaiJ9KBrzOhiB",
      "createdOn": "2022-07-20T10:49:53.505Z",

      "modifiedBy": "f400dUqjPuaiJ9KBrzOhiB",
      "modifiedOn": "2022-07-20T10:49:53.505Z"
    }
  ]
}
```

The following table describes the parameters of each category that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of categories retrieved.|
|`id`|System generated unique identifier of the category.|
|`refId`|Reference identifier of the category.|
|`parentId`|System generated unique identifier of the parent category.|
|`name`|Name of the category.|
|`description`|Description of the category.|
|`status `|Status of the category. A category can have one of the following statuses:<br>-<br>` ACTIVE `<br>-<br>` INACTIVE`|
|`effectiveStatus `|The effective status indicates whether the category is available in Data Marketplace. A<br>category can have one of the following statuses:<br>-<br>` ACTIVE `. The category is available.<br>-<br>` INACTIVE`. The category is unavailable.|
|`hasChildCategory `|Parameter that indicates whether a category has subcategories. This parameter can<br>have one of the following values:<br>-<br>` true `. The category contains subcategories.<br>-<br>` false`. The category doesn't contain subcategories.|
|`dataOwners`|Details of the stakeholder that is assigned the Category Owner stakeholder role on the<br>category.|
|`dataOwners >`<br>`displayName`|Name of the stakeholder that is assigned the Category Owner stakeholder role on the<br>category.|
|`dataOwners > id`|System generated unique identifier of the user account or user group that is assigned<br>as a stakeholder on the category with the Category Owner stakeholder role.|
|`dataOwners > name`|Username of the stakeholder that is assigned the Category Owner stakeholder role on<br>the category.|
|`dataOwners > email`|Email address of the stakeholder that is assigned the Category Owner stakeholder role<br>on the category.|
|`dataOwners > phone`|Contact number of the stakeholder that is assigned the Category Owner stakeholder<br>role on the category.|


|**Parameter**|**Description**|
|---|---|
|`dataOwners > status `|The status indicates whether or not the Category Owner stakeholder user account or<br>user group is active. A user account or user group can have one of the following<br>statuses:<br>-<br>` ACTIVE `. The Category Owner user account or user group is active.<br>-<br>` INACTIVE`. The Category Owner user account or user group is not active.|
|`dataOwners > userInfo`|Details retrieved from the**My Data**page of the stakeholder that is assigned the<br>Category Owner stakeholder role on the category.|
|`dataOwners > isGroup `|Parameter that indicates whether the user account is part of a group. This parameter<br>can have one of the following values:<br>-<br>` true `. The user account is part of a group<br>-<br>` false`. The user account isn't part of a group|
|`createdBy`|System generated identifier of the user account that created the category.|
|`createdOn`|Date when the category was created.|
|`modifiedBy`|System generated identifier of the latest user account that modified the category.|
|`modifiedOn`|Latest date when the category was modified.|


#### Modify categories
Use a REST API to modify categories in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/categories**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id `|Required. The system generated unique<br>identifier of the category that you want to<br>modify.|For more information about how<br>you can use an API to get the<br>system generated unique<br>identifier of a category, see<br>“Retrieve categories” on page 124.<br>To get the system generated<br>unique identifier of a category<br>from the Data Marketplace<br>interface, open the category. The<br>category page's URL contains the<br>system generated unique<br>identifier.<br>For example, in the URL<br>` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-`<br>`a1e412c1ce13&dtn=_AfterEB `<br>` F%20may20 `, the system<br>generated unique identifier is<br>` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|`name `|Required. Enter a name for the category.|Ensure that you enter a unique<br>value.<br>Data Marketplace doesn't<br>consider the letter case when it<br>verifies the uniqueness of the<br>` name` parameter's value. For<br>example, if you try to name a<br>category as "Retail Department"<br>while a category called "Retail<br>department" already exists, the<br>API call fails.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`description`|Required. Enter a description for the category.|-|
|`dataOwners`|Optional. Enter the system generated unique<br>identifier of the user account or user group<br>that is assigned as a stakeholder on the<br>category with the Category Owner stakeholder<br>role.|- To get the system generated<br>unique identifier of a user<br>account, navigate to**My**<br>**Services > Administrator >**<br>**Users**. On the**Users**page,<br>click a user account. The user<br>account page's URL contains<br>the system generated unique<br>identifier.|
|||For example, in the URL`/cloudUI/products/`<br>`administer/main/usersAsset/`<br>`0LH3xBJC9A6haZ26htGZyT `,<br>the system generated unique<br>identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|||- To get the system generated<br>unique identifier of a user<br>group, navigate to**My Services**<br>**> Administrator > User**<br>**Groups**. On the**User Groups**<br>page, click a user group. The<br>user group page's URL<br>contains the system generated<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/userGroupsAsset/`<br>`90uizkSWg0ycuu7hSNXSW4 `,<br>the system generated unique<br>identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|


#### Example request
The following example shows how you can use an API to modify a category: 

```
{
  "items": [
    {
      "id": "2fa40528-410d-4cf6-8ee3-9f3ef328285c",
      "name": "Retail Department",
      "description": "This category represents the retail department.",
      "dataOwners": [
        "e9paNlaKR8MdFJnETEs2k4"
      ]
    }
  ]
}

      "description": "This category represents the retail department.",
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered.

The following example shows the response of an API call to modify a category: 

```
{
  "processingTime": 10105,
  "objects": [
    {
      "index": 1,
      "id": "2fa40528-410d-4cf6-8ee3-9f3ef328285c",
      "refId": "CAT-414",
      "name": "Retail Department"
    }
  ]
}
```

The following table describes the parameters of each category that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the category in the` objects` JSON array. This value does not impact how the category is<br>used by a Data Marketplace user.|
|`id`|System generated unique identifier of the category.|
|`refId`|Reference identifier of the category.|
|`name`|Name of the category.|


#### Delete categories
Use a REST API to delete categories in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/categories/<id>**|
||`<id>`: Required. Enter the system generated unique identifier of the category that you want to delete.|
||To get the system generated unique identifier of a category, open the category. The category page's URL<br>contains the system generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/category/view?ac=67417f72-e5ab-44f0-add9-a1e412c1ce13&dtn=_AfterEBF%20may20`, the system generated unique identifier is` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>category, see<br>“Retrieve categories” on page 124.|
|Method|DELETE|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 


#### Example request
The following example shows how you can use an API to delete a category: 

```
https://{{CDMP_URL}}/api/v1/integration/categories/67417f72-e5ab-44f0-add9-a1e412c1ce13
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the category is deleted successfully: 

```
204 OK code
```

### Delivery formats
A delivery format object represents the format in which the data is delivered. 

#### Create delivery formats
Use a REST API to create delivery formats in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryFormats**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`refId`|Optional. Enter a reference identifier<br>for the delivery format.|If you don't specify a reference identifier,<br>Data Marketplace automatically assigns a<br>unique value to the object. The reference<br>identifier that Data Marketplace<br>automatically generates contains a<br>prefix. The Administrator can specify the<br>prefix of the automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a unique<br>value. Ensure that you don't use the prefix<br>value that is configured in Metadata<br>Command Center.|
|`name`|Required. Enter a name for the<br>delivery format.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the<br>letter case when it verifies the<br>uniqueness of the` name` parameter's<br>value. For example, if you try to name a<br>delivery format as "Excel" while a delivery<br>format called "excel" already exists, the<br>API call fails.|
|`status`|Required. Specify a status for the<br>delivery format. The status<br>determines whether a delivery format<br>is available to be added to delivery<br>templates.|Enter one of the following values:<br>- To make the delivery format available,<br>enter` ACTIVE`.<br>- To make the delivery format<br>unavailable, enter` INACTIVE`.|


#### Example request
The following example shows how you can use an API to create a delivery format: 

```
{
  "items": [
    {
      "refId": "FMT9",
      "name": "XLSX_Workbook",
      "status": "ACTIVE"
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a delivery format: 

```
{
  "processingTime": 17626,
  "objects": [
    {
      "index": 1,
```


```
      "id": "980dfbc0-c7c0-4e24-ba47-4477bb9bf19c",
      "refId": "FMT9",
      "name": "XLSX_Workbook"
    }
  ]
}
```

The following table describes the parameters of each delivery format that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the delivery format in the` objects` JSON array. This value does not impact how the<br>delivery format is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the delivery format.|
|`refId`|Reference identifier of the delivery format.|
|`name`|Name of the delivery format.|


#### Retrieve delivery formats
Use a REST API to retrieve the details of delivery formats in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryFormats**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`ids `|Optional. Enter the system<br>generated unique identifier of a<br>delivery format.|To enter more than one value, use the following<br>format:<br>` ids=<value1>&ids=<value2>`|
|`search`|Optional. Enter the search term<br>that you want to use to find a<br>delivery format.|Ensure that the search term that you enter don't<br>contain an asterisk (*).|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`fields`|Optional. Enter the fields on<br>which the search term applies.<br>The terms that you entered in<br>the` search ` field parameter are<br>used to search the fields that<br>you specify here.|Enter the following values:<br>-<br>` NAME `<br>-<br>` DESCRIPTION`|
|`createdDateFrom`|Optional. To find delivery<br>format created between a date<br>range, enter the starting date.|To specify a date, use the` YYYY-MM-DD ` format.<br>The value that you specify is automatically<br>converted and stored in the Coordinated<br>Universal Time (UTC) time standard.<br>If you have specified a value for the<br>` createdDateFrom` parameter, ensure that you<br>also enter a value for the` createdDateTo`<br>parameter.|
|`createdDateTo`|Optional. To find delivery<br>format created between a date<br>range, enter the ending date.|To specify a date, use the` YYYY-MM-DD ` format.<br>The value that you specify is automatically<br>converted and stored in the Coordinated<br>Universal Time (UTC) time standard.<br>If you have specified a value for the<br>` createdDateTo` parameter, ensure that you<br>also enter a value for the` createdDateFrom`<br>parameter.|
|`modifiedDateFrom`|Optional. To find delivery<br>format modified between a<br>date range, enter the ending<br>date.|To specify a date, use the` YYYY-MM-DD ` format.<br>The value that you specify is automatically<br>converted and stored in the Coordinated<br>Universal Time (UTC) time standard.<br>If you have specified a value for the<br>` modifiedDateFrom` parameter, ensure that you<br>also enter a value for the` modifiedDateTo`<br>parameter.|
|`modifiedDateTo`|Optional. To find delivery<br>format modified between a<br>date range, enter the starting<br>date.|To specify a date, use the` YYYY-MM-DD ` format.<br>The value that you specify is automatically<br>converted and stored in the Coordinated<br>Universal Time (UTC) time standard.<br>If you have specified a value for the<br>` modifiedDateTo` parameter, ensure that you<br>also enter a value for the` modifiedDateFrom`<br>parameter.|
|`status`|Optional. Specify the status of<br>the delivery format. The status<br>indicates whether a delivery<br>format is available to be added<br>to delivery templates.|Enter one of the following values:<br>- To find the delivery formats that are available,<br>enter` ACTIVE`.<br>- To find the delivery formats that aren't<br>available, enter` INACTIVE`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortByField `|Optional. Specify the<br>parameters to sort the search<br>results.|To sort the search results, enter one of the<br>following values:<br>-<br>` NAME `<br>-<br>` STATUS `<br>-<br>` ID `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order<br>of the search results.|Enter one of the following values:<br>- To sort the search results by ascending order,<br>enter` ASC`.<br>- To sort the search results by descending<br>order, enter` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting<br>index for the paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum<br>number of results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of delivery format: 

```
https://{{CDMP_URL}}/api/v1/integration/provisioning/deliveryFormats
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a delivery format: 

```
{
  "processingTime": 0,
  "offset": 0,
  "limit": 0,
  "totalCount": 0,
  "objects": [
    {
      "id": "980dfbc0-c7c0-4e24-ba47-4477bb9bf19c",
      "refId": "FMT9",
      "name": "XLSX_Workbook",
      "status": "ACTIVE"
    }
  ]
}
```

The following table describes the parameters of each delivery format that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`Offset`|Starting index for paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of delivery formats retrieved.|
|`id`|System generated unique identifier of the delivery format.|
|`refId`|Reference identifier of the delivery format.|
|`name`|Name of the delivery format.|
|`status `|The status indicates whether a delivery format is available to be added to delivery templates. A<br>delivery format can have one of the following statuses:<br>-<br>` ACTIVE `. The delivery format is available.<br>-<br>` INACTIVE`. The delivery format is not available.|


#### Modify delivery formats
Use a REST API to modify delivery formats in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryFormats**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system<br>generated unique identifier of<br>the delivery format that you<br>want to modify.|For more information about how you can use an<br>API to get the unique identifier of a delivery<br>format, see<br>“Retrieve delivery formats” on page 136.|
|`refId`|Optional. Enter a reference<br>identifier for the delivery<br>format.|If you don't specify a reference identifier, Data<br>Marketplace automatically assigns a unique<br>value to the object. The reference identifier that<br>Data Marketplace automatically generates<br>contains a prefix. The Administrator can specify<br>the prefix of the automatically generated<br>reference identifier in Metadata Command<br>Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure that<br>you don't use the prefix value that is configured<br>in Metadata Command Center.|
|`name`|Required. Enter a name for the<br>delivery format.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the letter<br>case when it verifies the uniqueness of the` name`<br>parameter's value. For example, if you try to<br>name a delivery format as "Excel" while a<br>delivery format called "excel" already exists, the<br>API call fails.|
|`status `|Required. Specify a status for<br>the delivery format. The status<br>determines whether a delivery<br>format is available to be added<br>to delivery templates.|Enter one of the following values:<br>- To make the delivery format available, enter<br>` ACTIVE `.<br>- To make the delivery format unavailable, enter<br>` INACTIVE`.|


#### Example request
The following example shows how you can use an API to modify a delivery format: 

```
{
  "items": [
    {
      "id": "980dfbc0-c7c0-4e24-ba47-4477bb9bf19c",
      "refId": "FMT9",
      "name": "XLSX Workbook",
      "status": "ACTIVE"
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered.

The following example shows the response of an API call to modify a delivery format: 

```
{
  "processingTime": 16702,
  "objects": [
    {
      "index": 1,
      "id": "980dfbc0-c7c0-4e24-ba47-4477bb9bf19c",
      "refId": "FMT9",
      "name": "XLSX_Workbook"
    }
  ]
}
```

The following table describes the parameters of each delivery format that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the delivery format in the` objects` JSON array. This value does not impact how the<br>delivery format is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the delivery format.|
|`refId`|Reference identifier of the delivery format.|
|`name`|Name of the delivery format.|


### Delivery methods
A delivery method represents the medium used to deliver the data to the Data User that ordered the collection. 

#### Create delivery methods
Use a REST API to create delivery methods in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryMethods**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**||
|---|---|---|
|`refId`|Optional. Enter a reference<br>identifier for the delivery<br>method.|If you don't specify a reference identifier, Data<br>Marketplace automatically assigns a unique<br>value to the object. The reference identifier that<br>Data Marketplace automatically generates<br>contains a prefix. The Administrator can specify<br>the prefix of the automatically generated<br>reference identifier in Metadata Command<br>Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure that<br>you don't use the prefix value that is configured<br>in Metadata Command Center.|
|`name`|Required. Enter a name for the<br>delivery method.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the letter<br>case when it verifies the uniqueness of the` name`<br>parameter's value. For example, if you try to<br>name a delivery method as "SFTP" while a<br>delivery method called "sftp" already exists, the<br>API call fails.|
|`status `|Required. Specify a status for<br>the delivery method. The status<br>indicates whether a delivery<br>method is available to be<br>added to delivery templates.|Enter one of the following values:<br>- To make the delivery method available, enter<br>` ACTIVE`.<br>- To make the delivery method unavailable,<br>enter` INACTIVE`.|


#### Example request
The following example shows how you can use an API to create a delivery method: 

```
{
  "items": [
    {
      "refId": "DMTD71",
      "name": "Hypercore Protocol",
      "status": "INACTIVE"
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a delivery method: 

```
{
  "processingTime": 17626,
  "objects": [
    {
      "index": 1,
      "id": "770dfbc0-v5t6-4e24-ba47-4477bb9bf33e",
      "refId": "DMTD71",

      "name": "Hypercore Protocol"
    }
  ]
}
```

The following table describes the parameters of each delivery method that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the delivery method in the` objects` JSON array. This value does not impact how the<br>delivery method is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the delivery method.|
|`refId`|Reference identifier of the delivery method.|
|`name`|Name of the delivery method.|


#### Retrieve delivery methods
Use a REST API to retrieve the details of delivery methods in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryMethods**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`search`|Optional. Enter the search term that you want<br>to use to find a delivery method.|Ensure that the search term that<br>you enter don't contain an<br>asterisk (*).|
|`ids `|Optional. Enter the system generated unique<br>identifier of the delivery method.|To enter more than one value,<br>use the following format:<br>` ids=<value1>&ids=<value2>`|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`status `|Optional. Specify the status of the delivery<br>method. The status indicates whether a<br>delivery method is available to be added to<br>delivery templates.|Enter one of the following<br>values:<br>- To find the delivery methods<br>that are available, enter<br>` ACTIVE `.<br>- To find the delivery methods<br>that aren't available, enter<br>` INACTIVE`.|
|`createdDateFrom`|Optional. To find the delivery method created<br>between a date range, enter the starting date.|To specify a date, use the` YYYY-MM-DD` format. The value that<br>you specify is converted<br>automatically to the Coordinated<br>Universal Time (UTC) time<br>standard.<br>If you have specified a value for<br>the` createdDateFrom`<br>parameter, ensure that you enter<br>a value for the` createdDateTo`<br>parameter.|
|`createdDateTo`|Optional. To find the delivery method created<br>between a date range, enter the ending date.|To specify a date, use the` YYYY-MM-DD` format. The value that<br>you specify is converted<br>automatically to the Coordinated<br>Universal Time (UTC) time<br>standard.<br>If you have specified a value for<br>the` createdDateTo` parameter,<br>ensure that you enter a value for<br>the` createdDateFrom`<br>parameter.|
|`modifiedDateFrom`|Optional. To find the delivery method modified<br>between a date range, enter the starting date.|To specify a date, use the` YYYY-MM-DD` format. The value that<br>you specify is converted<br>automatically to the Coordinated<br>Universal Time (UTC) time<br>standard.<br>If you have specified a value for<br>the` modifiedDateFrom `<br>parameter, ensure that you enter<br>a value for the<br>` modifiedDateTo` parameter.|
|`modifiedDateTo`|Optional. To find the delivery method modified<br>between a date range, enter the ending date.|To specify a date, use the` YYYY-MM-DD` format. The value that<br>you specify is converted<br>automatically to the Coordinated<br>Universal Time (UTC) time<br>standard.<br>If you have specified a value for<br>the` modifiedDateTo `<br>parameter, ensure that you enter<br>a value for the<br>` modifiedDateFrom` parameter.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortByField `|Optional. Specify the parameters to sort the<br>search results.|To sort the search results, enter<br>one of the following values:<br>-<br>` ID `<br>-<br>` NAME `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the search<br>results.|Enter one of the following<br>values:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum number of<br>results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of delivery method: 

```
https://{{CDMP_URL}}/api/v1/integration/provisioning/deliveryMethods?
search=SK&ids=909668e3-f91d-4cde-a7d5-
e3dca316ce97&status=ACTIVE&createdDateFrom=2022-01-12&createdDateTo=2022-12-12&modifiedDa
teFrom=2022-01-12&modifiedDateTo=2022-12-12&sortByField=NAME&sort=DESC&offset=0&limit=2
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of delivery method: 

```
{
  "processingTime": 2988,
  "offset": 0,
  "limit": 2,
  "totalCount": 1,
  "objects": [
    {
      "id": "770dfbc0-v5t6-4e24-ba47-4477bb9bf33e",
      "refId": "DMTD71",
      "name": "Hypercore Protocol"
      "status": "ACTIVE",
      "createdBy": "9Wqbp1IRIqfltN12yjs3qt",
      "createdOn": "2022-06-03T06:27:07.853Z",
      "modifiedBy": "9Wqbp1IRIqfltN12yjs3qt",
      "modifiedOn": "2022-06-03T06:30:34.676Z"
    }
```


```
}
```

#### `]`
The following table describes the parameters of each delivery method that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`Offset`|Starting index for paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of delivery methods retrieved.|
|`id`|System generated unique identifier of the delivery method.|
|`refId`|Reference identifier of the delivery method.|
|`name`|Name of the delivery method.|
|`status `|The status indicates whether the delivery method is available to be added to delivery templates. A<br>delivery method can have one of the following statuses:<br>-<br>` ACTIVE `. The delivery method is available.<br>-<br>` INACTIVE`. The delivery method is not available.|
|`CREATED_BY`|System generated unique identifier of the user account that created the delivery method.|
|`CREATED_ON`|Date when the delivery method was created.|
|`MODIFIED_BY`|System generated unique identifier of the user account that last modified the delivery method.|
|`MODIFIED_ON`|Latest date when the delivery method was modified.|


#### Modify delivery methods
Use a REST API to modify delivery methods in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliverymethods**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system<br>generated unique identifier of<br>the delivery method that you<br>want to modify.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a delivery method, see<br>“Retrieve delivery methods” on page 143.|
|`refId`|Optional. Enter a reference<br>identifier for the delivery<br>method.|If you don't specify a reference identifier, Data<br>Marketplace automatically assigns a unique<br>value to the object. The reference identifier that<br>Data Marketplace automatically generates<br>contains a prefix. The Administrator can specify<br>the prefix of the automatically generated<br>reference identifier in Metadata Command<br>Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure that<br>you don't use the prefix value that is configured<br>in Metadata Command Center.|
|`name`|Required. Enter a name for the<br>delivery method.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the letter<br>case when it verifies the uniqueness of the` name`<br>parameter's value. For example, if you try to<br>name a delivery method as "SFTP" while a<br>delivery method called "sftp" already exists, the<br>API call fails.|
|`status `|Required. Specify a status for<br>the delivery method. The status<br>indicates whether a delivery<br>method is available to be<br>added to delivery templates.|Enter one of the following values:<br>- To make the delivery method available, enter<br>` ACTIVE`.<br>- To make the delivery method unavailable,<br>enter` INACTIVE`.|


#### Example request
The following example shows how you can use an API to modify a delivery method: 

```
{
  "items": [
    {
      "id": "770dfbc0-v5t6-4e24-ba47-4477bb9bf33e",
      "refId": "DMTD71",
      "name": "Hypercore Protocol",
      "status": "INACTIVE"
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 


The following example shows the response of an API call to modify a delivery method: 

```
{
  "processingTime": 16702,
  "objects": [
    {
      "index": 1,
      "id": "770dfbc0-v5t6-4e24-ba47-4477bb9bf33e",
      "refId": "DMTD71",
      "name": "Hypercore Protocol"
    }
  ]
}
```

The following table describes the parameters of each delivery method that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the delivery method in the` objects` JSON array. This value does not impact how the<br>delivery method is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the delivery method.|
|`refId`|Reference identifier of the delivery method.|
|`name`|Name of the delivery method.|


### Delivery templates
A delivery template contains the delivery format, the delivery method, and the location where the data is delivered to the Data User. 

#### Create delivery templates
Use a REST API to create delivery templates in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryTemplates**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`refId`|Optional. Enter a reference identifier for the<br>delivery template.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique<br>value to the object. The<br>reference identifier that Data<br>Marketplace automatically<br>generates contains a prefix. The<br>Administrator can specify the<br>prefix of the automatically<br>generated reference identifier in<br>Metadata Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a<br>unique value. Ensure that you<br>don't use the prefix value that is<br>configured in Metadata<br>Command Center.|
|`name `|Required. Enter a name for the delivery<br>template.|Ensure that you enter a unique<br>value.<br>Data Marketplace doesn't<br>consider the letter case when it<br>verifies the uniqueness of the<br>` name` parameter's value. For<br>example, if you try to name a<br>delivery template as "Excel over<br>ipfs" while a delivery template<br>called "Excel over IPFS" already<br>exists, the API call fails.|
|`description`|Required. Enter a description for the delivery<br>template.|-|
|`color`|Optional. Specify a color indicator for the<br>delivery template. Assigning a unique color<br>indicator allows Data Marketplace users to<br>identify a delivery template easily.|Enter the hexadecimal value that<br>represents the color that you<br>want to use.|
|`status`|Required. Specify a status for the delivery<br>template. The status determines whether the<br>delivery template is available to be used to<br>create a delivery target of a data collection.|Enter one of the following values:<br>- To make the delivery template<br>available, enter` ACTIVE`.<br>- To make the delivery template<br>unavailable, enter` INACTIVE`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`managedAccess`|Optional. This parameter determines whether<br>the data is customized and is made available<br>in a data location that is unique to each order<br>fulfilled using a target that uses this template.|Enter one of the following values:<br>- If you set the parameter value<br>to` ENABLED`, a unique data<br>location is created for each<br>order that is fulfilled using a<br>target that is based on this<br>template. Furthermore, an<br>access management<br>application such as Data<br>Access Management might<br>customize the data that it<br>delivers based on the<br>characteristics of the data, the<br>Data User and the usage<br>context that they specified in<br>the order.<br>- If you set the parameter value<br>to` DISABLED`, the same data is<br>made available in a data<br>location that is common to all<br>Data Users.<br>For more information, see the<br>_Integrate Data Marketplace with_<br>_other Informatica services_topic<br>in the_Set Up Data Marketplace_<br>help.|
|`deliveryType `|Optional. Specify the type of delivery.|Enter one of the following values:<br>- If the delivery type is set to<br>` AUTOMATIC `, the order<br>approval and fulfillment is<br>automated.<br>- If the delivery type is set to<br>` MANUAL`, a stakeholder of the<br>data collection must approve<br>and fulfill the order manually.|
|`templateOwners`|Optional. Enter the system generated unique<br>identifier of the user account to which you<br>want to assign the Delivery Owner role. The<br>Delivery Owner is responsible for the delivery<br>template that you create.|To get the system generated<br>unique identifier of a user<br>account, navigate to**My Services**<br>**> Administrator > Users**. On the<br>**Users**page, click a user account.<br>The user account page's URL<br>contains the system generated<br>unique identifier.<br>For example, in the URL`/cloudUI/products/`<br>`administer/main/usersAsset/`<br>`0LH3xBJC9A6haZ26htGZyT `, the<br>system generated unique<br>identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`targetSystemReference`|Required. Specify the default system where the<br>data is delivered.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`defaultPhysicalLocation`|Required. Specify the default location where<br>the data is delivered.|If you set the` managedAccess`<br>parameter value to` ENABLED `,<br>ensure that you use the following<br>structure to specify the data<br>location:<br>` hostname `:` port `<br>In the preceding location<br>structure, you can configure the<br>following properties:<br>-<br>` hostname `. Enter the<br>hostname of the data plane in<br>Data Access Management.<br>-<br>` port`. Enter the port number of<br>the data plane in Data Access<br>Management.|
|`deliveryMethodIds`|Required. Enter the system generated unique<br>identifier of the delivery method.|For more information about how<br>you can use an API to get the<br>system generated unique<br>identifier of a delivery method,<br>see<br>“Retrieve delivery methods” on page 143.|
|`deliveryFormatIds`|Required. Enter the system generated unique<br>identifier of the delivery format.|For more information about how<br>you can use an API to get the<br>system generated unique<br>identifier of a delivery format,<br>see<br>“Retrieve delivery formats” on page 136.|


#### Example request
The following example shows how you can use an API to create a delivery template: 

```
{
  "items":[
    {
      "refId": "Template719",
      "name":"XLSX via Hypercore",
      "description":"Template to deliver an XLSX file via Hypercore.",
      "color": "#10Be21",
      "status":"ACTIVE",
      "managedAccess": "DISABLED",
      "deliveryType": "AUTOMATIC",
      "templateOwners":[
        "26sEOH2bGStfMSZtlK2UC0"
      ],
      "targetSystemReference":"hyper://{public_key}{version}",
      "defaultPhysicalLocation":"Hypercore",
      "deliveryMethodIds":[
        "fd24d20f-b97d-435f-b703-dc03c3e0280a"
      ],
      "deliveryFormatIds":[
        "b86a8588-3ec2-4eab-9504-8ff98a41187f"
      ]
    }
  ]
}
```


#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a delivery template: 

```
{
  "processingTime": 16702,
  "objects": [
    {
      "index": 1,
      "id": "72ab9726-359d-4c07-987e-febdcfb86906",
      "refId": "Template719",
      "name": "XLSX via Hypercore"
    }
  ]
}
```

The following table describes the parameters of each delivery template that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the delivery template in the` objects` JSON array. This value does not impact how the<br>delivery template is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the delivery template.|
|`refId`|Reference identifier of the delivery template.|
|`name`|Name of the delivery template.|


#### Retrieve delivery templates
Use a REST API to retrieve the details of delivery templates in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryTemplates**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`search`|Optional. Enter the search term that<br>you want to use to find a delivery<br>template.|Ensure that the search term that you enter<br>don't contain an asterisk (*).|
|`fields`|Optional. Enter the fields on which<br>the search term applies. The terms<br>that you entered in the` search ` field<br>parameter are used to search the<br>fields that you specify here.|Enter the following values:<br>-<br>` NAME `<br>-<br>` DESCRIPTION`|
|`ids `|Optional. Enter the system<br>generated unique identifier of a<br>delivery template.|To enter more than one value, use the<br>following format:<br>` ids=<value1>&ids=<value2>`|
|`status`|Optional. Specify the status of the<br>delivery template. The status<br>indicates whether the delivery<br>template is available to be used to<br>create a delivery target of a data<br>collection.|Enter one of the following values:<br>- To find the delivery templates that are<br>available, enter` ACTIVE`.<br>- To find the delivery templates that aren't<br>available, enter` INACTIVE`.|
|`deliveryType`|Optional. Specify the type of<br>delivery.|Enter one of the following values:<br>- To find a delivery template of automatic<br>type, enter` AUTOMATIC`. For orders where<br>the delivery option is of automatic type,<br>the approval and fulfillment is automated.<br>- To find a delivery template of manual<br>type, enter` MANUAL`. For orders where the<br>delivery option is of manual type, a<br>stakeholder of the data collection must<br>approve and fulfill the order manually.|
|`isDefault`|Optional. Specify whether to retrieve<br>the default delivery template for<br>Data Marketplace.|Enter one of the following values:<br>- To find a delivery template that is<br>configured as the default delivery option<br>for Data Marketplace, enter` true`.<br>- To find a delivery template that isn't<br>configured as the default delivery option<br>for Data Marketplace, enter` false`.|
|`deliveryMethodIds`|Optional. Enter the system<br>generated unique identifier of the<br>delivery method that is part of the<br>delivery template.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a delivery method, see<br>“Retrieve delivery methods” on page 143.|
|`deliveryFormatIds`|Optional. Enter the system<br>generated unique identifier of the<br>delivery format that is part of the<br>delivery template.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a delivery format, see<br>“Retrieve delivery formats” on page 136.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`templateOwnerUserIds`|Optional. Enter the system<br>generated unique identifier of the<br>Delivery Owner that manages the<br>delivery template.|To get the system generated unique<br>identifier of a user account, navigate to**My**<br>**Services > Administrator > Users**. On the<br>**Users**page, click a user account. The user<br>account page's URL contains the system<br>generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `,<br>the system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`createdDateFrom`|Optional. To find delivery templates<br>created between a date range, enter<br>the starting date.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in the<br>Coordinated Universal Time (UTC) time<br>standard.<br>If you have specified a value for the<br>` createdDateFrom ` parameter, ensure that<br>you also enter a value for the<br>` createdDateTo` parameter.|
|`createdDateTo`|Optional. To find delivery templates<br>created between a date range, enter<br>the ending date.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in the<br>Coordinated Universal Time (UTC) time<br>standard.<br>If you have specified a value for the<br>` createdDateTo ` parameter, ensure that<br>you also enter a value for the<br>` createdDateFrom` parameter.|
|`modifiedDateFrom`|Optional. To find delivery templates<br>modified between a date range,<br>enter the starting date.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in the<br>Coordinated Universal Time (UTC) time<br>standard.<br>If you have specified a value for the<br>` modifiedDateFrom ` parameter, ensure that<br>you also enter a value for the<br>` modifiedDateTo` parameter.|
|`modifiedDateTo`|Optional. To find delivery templates<br>modified between a date range,<br>enter the ending date.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in the<br>Coordinated Universal Time (UTC) time<br>standard.<br>If you have specified a value for the<br>` modifiedDateTo ` parameter, ensure that<br>you also enter a value for the<br>` modifiedDateFrom` parameter.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortByField `|Optional. Specify the parameters to<br>sort the search results.|To sort the search results, enter one of the<br>following values:<br>-<br>` ID `<br>-<br>` NAME `<br>-<br>` TARGET_SYSTEM_REFERENCE `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of<br>the search results.|Enter one of the following values:<br>- To sort the search results by ascending<br>order, enter` ASC`.<br>- To sort the search results by descending<br>order, enter` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting index for<br>the paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum<br>number of results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of delivery template: 

```
https://{{CDMP_URL}}/api/v1/integration/provisioning/deliveryTemplates?status=ACTIVE
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of delivery template: 

```
{

     "processingTime":959,
     "offset":0,
     "limit":50,
     "totalCount":153,
     "objects"[
        {
           "id":"72ab9726-359d-4c07-987e-febdcfb86906",
           "refId": "Template719",
           "name":"XLSX via Hypercore",
           "description":"Template to deliver an XLSX file via Hypercore.",
           "color": "#10Be21",
           "isDefault":false,
           "status":"ACTIVE",
           "managedAccess": "DISABLED",
           "deliveryType": "MANUAL",
           "templateOwners":[],
           "targetSystemReference":"S3",
```


```
           "defaultPhysicalLocation":"file",
           "deliveryMethods":[
             {
                "id":"3c308736-5d9d-4a35-92a6-86dc213f4c5a",
                "refId": "DMTD72",
                "name":"Hypercore Protocol",
                "status":"ACTIVE",
                "createdBy":"f400dUqjPuaiJ9KBrzOhiB",
                "createdOn":"2022-07-21T18:20:27.529Z",
                "modifiedBy":"f400dUqjPuaiJ9KBrzOhiB",
                "modifiedOn":"2022-07-21T18:20:27.529Z"
            }
             ],
         "deliveryFormats":[
            {
              "id":"8738df74-1413-4123-b370-b637249b5bfc",
              "refId": "FMT8",
              "name":"XLSX Workbook",
              "status":"ACTIVE",
              "createdBy":"f400dUqjPuaiJ9KBrzOhiB",
              "createdOn":"2022-07-21T18:20:24.395Z",
              "modifiedBy":"f400dUqjPuaiJ9KBrzOhiB",
              "modifiedOn":"2022-07-21T18:20:24.395Z"
            }
            ],
             "createdBy":"f400dUqjPuaiJ9KBrzOhiB",
             "createdOn":"2022-07-21T18:20:30.491Z",
             "modifiedBy":"f400dUqjPuaiJ9KBrzOhiB",
             "modifiedOn":"2022-07-21T18:20:30.491Z"
}
```

The following table describes the parameters of each delivery template that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of delivery templates retrieved.|
|`id`|System generated unique identifier of the delivery template.|
|`refId`|Reference identifier of the delivery template.|
|`name`|Name of the delivery template.|
|`description`|Description of the delivery template.|
|`color`|A hexadecimal value that represents the color indicator of the delivery<br>template.|
|`isDefault `|Parameter that indicates whether or not the delivery template is set as the<br>default delivery option for your Data Marketplace instance. The parameter can<br>have one of the following values:<br>-<br>` true `. The delivery template is the default delivery option.<br>-<br>` false`. The delivery target isn't the default delivery option.|
|`status `|The status indicates whether the delivery template is available to be used to<br>create a delivery target of a data collection. A delivery template can have one<br>of the following statuses:<br>-<br>` ACTIVE `. The delivery template is available.<br>-<br>` INACTIVE`. The delivery template is unavailable.|

|**Parameter**|**Description**|
|---|---|
|`managedAccess `|Parameter that indicates whether a unique data location is created for each<br>order that is fulfilled using a target that is based on this template. This<br>parameter can have one of the following values:<br>-<br>` ENABLED `. A unique data location is created for each order that is fulfilled<br>using a target that is based on this template. Furthermore, an access<br>management application such as Data Access Management might<br>customize the delivered data based on the characteristics of the data, the<br>Data User and the usage context that they specified in the order.<br>-<br>` DISABLED`. The same data is made available in a data location that is<br>common to all Data Users.<br>For more information, see the_Manage access to data with Data Access_<br>_Management_topic in the_Set Up Data Marketplace_help.|
|`deliveryType `|The type of delivery. A delivery template can have one of the following types:<br>-<br>` AUTOMATIC `. The order approval and fulfillment is automated.<br>-<br>` MANUAL`, a stakeholder of the data collection must approve and fulfill the<br>order manually.|
|`templateowners`|Details of the Delivery Owner that is responsible for the delivery template.|
|`targetSystemReference`|Default system where the data is delivered.|
|`defaultPhysicalLocation`|Default location where the data is delivered.|
|`deliveryMethods > id`|System generated unique identifier of the delivery method of the delivery<br>template.|
|`deliveryMethods > refId`|Reference identifier of the delivery method.|
|`deliveryMethods > name`|Name of the delivery method.|
|`deliveryMethods > status `|The status indicates whether the delivery method is available to be added to<br>delivery templates. A delivery method can have one of the following statuses:<br>-<br>` ACTIVE `. The delivery method is available.<br>-<br>` INACTIVE`. The delivery method is not available.|
|`deliveryMethods > createdBy`|System generated unique identifier of the user account that created the<br>delivery method.|
|`deliveryMethods > createdOn`|Date when the delivery method was created.|
|`deliveryMethods > modifiedBy`|System generated unique identifier of the latest user account that modified<br>the delivery method.|
|`deliveryMethods > modifiedOn`|Latest date when the delivery method was modified.|
|`deliveryFormats > id`|System generated unique identifier of the delivery format of the delivery<br>template.|
|`deliveryFormats > refId `<br>` deliveryFormats > name`|Reference identifier of the delivery format.<br>Name of the delivery format.|


|**Parameter**|**Description**|
|---|---|
|`deliveryFormats > status `|The status indicates whether the delivery format is available to be added to<br>delivery templates. A delivery format can have one of the following statuses:<br>-<br>` ACTIVE `. The delivery format is available.<br>-<br>` INACTIVE`. The delivery format is not available.|
|`deliveryFormats > createdBy`|System generated unique identifier of the user account that created the<br>delivery format.|
|`deliveryFormats > createdOn`|Date when the delivery format was created.|
|`deliveryFormats > modifiedBy`|System generated unique identifier of the latest user account that modified<br>the delivery format.|
|`deliveryFormats > modifiedOn`|Latest date when the delivery format was modified.|
|`createdBy`|System generated unique identifier of the user account that created the<br>delivery template.|
|`createdOn`|Date when the delivery template was created.|
|`modifiedBy`|System generated unique identifier of the latest user account that modified<br>the delivery template.|
|`modifiedOn`|Latest date when the delivery template was modified.|


#### Modify delivery templates
Use a REST API to modify delivery templates in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryTemplates**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system generated<br>unique identifier of the delivery<br>template that you want to modify.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a delivery template,<br>see<br>“Retrieve delivery templates” on page 152.|
|`refId`|Optional. Enter a reference identifier for<br>the delivery template.|If you don't specify a reference<br>identifier, Data Marketplace<br>automatically assigns a unique value to<br>the object. The reference identifier that<br>Data Marketplace automatically<br>generates contains a prefix. The<br>Administrator can specify the prefix of<br>the automatically generated reference<br>identifier in Metadata Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a unique<br>value. Ensure that you don't use the<br>prefix value that is configured in<br>Metadata Command Center.|
|`name`|Required. Enter a name for the delivery<br>template.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the<br>letter case when it verifies the<br>uniqueness of the` name` parameter's<br>value. For example, if you try to name a<br>delivery template as "Excel over ipfs"<br>while a delivery template called "Excel<br>over IPFS" already exists, the API call<br>fails.|
|`description`|Required. Enter a description for the<br>delivery template.|-|
|`color`|Optional. Specify a color indicator for<br>the delivery template. Assigning a<br>unique color indicator allows Data<br>Marketplace users to identify a delivery<br>template easily.|Enter the hexadecimal value that<br>represents the color that you want to<br>use.|
|`status`|Required. Specify a status for the<br>delivery template. The status<br>determines whether the delivery<br>template is available to be used to<br>create a delivery target of a data<br>collection.|Enter one of the following values:<br>- To make the delivery template<br>available, enter` ACTIVE`.<br>- To make the delivery template<br>unavailable, enter` INACTIVE`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`managedAccess `|Optional. This parameter determines<br>whether the data is customized and is<br>made available in a data location that is<br>unique to each order fulfilled using a<br>target that uses this template.|Enter one of the following values:<br>- If you set the parameter value to<br>` ENABLED `, a unique data location is<br>created for each order that is fulfilled<br>using a target that is based on this<br>template. Furthermore, an access<br>management application such as<br>Data Access Management might<br>customize the delivered data based<br>on the characteristics of the data, the<br>Data User and the usage context that<br>they specified in the order.<br>- If you set the parameter value to<br>` DISABLED`, the same data is made<br>available in a data location that is<br>common to all Data Users.<br>For more information, see the_Integrate_<br>_Data Marketplace with other Informatica_<br>_services_topic in the_Set Up Data_<br>_Marketplace_help.|
|`deliveryType `|Optional. Specify the type of delivery.|Enter one of the following values:<br>- If the delivery type is set to<br>` AUTOMATIC`, the order approval and<br>fulfillment is automated.<br>- If the delivery type is set to` MANUAL`, a<br>stakeholder of the data collection<br>must approve and fulfill the order<br>manually.|
|`templateOwners`|Optional. Enter the system generated<br>unique identifier of the user account to<br>which you want to assign the Delivery<br>Owner role. The Delivery Owner is<br>responsible for the delivery template<br>that you create.|To get the system generated unique<br>identifier of a user account, navigate to<br>**My Services > Administrator > Users**.<br>On the**Users**page, click a user account.<br>The user account page's URL contains<br>the system generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `, the<br>system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`targetSystemReference`|Required. Specify the default system<br>where the data is delivered.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`defaultPhysicalLocation`|Required. Specify the default location<br>where the data is delivered.|If you set the` managedAccess`<br>parameter value to` ENABLED `, ensure<br>that you use the following structure to<br>specify the data location:<br>` hostname `:` port `<br>In the location structure, you can<br>configure the following properties:<br>-<br>` hostname `. Enter the hostname for<br>the Data Access Management proxy.<br>-<br>` port`. Enter the port number of the<br>Data Access Management proxy.<br>For more information about how you can<br>retrieve the hostname and port values,<br>see the_Data Access Management Proxy_<br>_service_topic in the_Secure Agent_<br>_Services help_in the Administrator.|
|`deliveryMethodIds`|Required. Enter the system generated<br>unique identifier of the delivery method.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a delivery method,<br>see<br>“Retrieve delivery methods” on page 143.|
|`deliveryFormatIds`|Required. Enter the system generated<br>unique identifier of the delivery format.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a delivery format,<br>see<br>“Retrieve delivery formats” on page 136.|


#### Example request
The following example shows how you can use an API to modify a delivery template: 

```
{
  "items":[
    {
         "id": "72ab9726-359d-4c07-987e-febdcfb86906",
      "refId": "Template719",
      "name":"XLSX via Hypercore",
      "description":"Template to deliver an XLSX file via Hypercore.",
      "color": "#10Be21",
      "status":"INACTIVE",
      "managedAccess": "DISABLED",
      "deliveryType": "MANUAL",
      "templateOwners":[
        "26sEOH2bGStfMSZtlK2UC0"
      ],
      "targetSystemReference":"hyper://{public_key}{version}",
      "defaultPhysicalLocation":"Hypercore",
      "deliveryMethodIds":[
        "fd24d20f-b97d-435f-b703-dc03c3e0280a"
      ],
      "deliveryFormatIds":[
        "b86a8588-3ec2-4eab-9504-8ff98a41187f"
      ]
    }
  ]
}
```


#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to modify a delivery template: 

```
{
  "processingTime": 16702,
  "objects": [
    {
      "index": 1,
      "id": "72ab9726-359d-4c07-987e-febdcfb86906",
      "refId": "Template719",
      "name": "XLSX via Hypercore"
    }
  ]
}
```

The following table describes the parameters of each delivery template that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the delivery template in the` objects` JSON array. This value does not impact how the<br>delivery template is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the delivery template.|
|`refId`|Reference identifier of the delivery template.|
|`name`|Name of the delivery template.|


### Terms of use
The terms of use provide usage requirements and guidelines that a Data User must accept to use the data. 

#### Create terms of use
Use REST APIs to create terms of use in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/termsOfUse**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`refId`|Optional. Enter a reference<br>identifier for the terms of use.|If you don't specify a reference identifier,<br>Data Marketplace automatically assigns a<br>unique value to the object. The reference<br>identifier that Data Marketplace<br>automatically generates contains a prefix.<br>The Administrator can specify the prefix of<br>the automatically generated reference<br>identifier in Metadata Command Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure<br>that you don't use the prefix value that is<br>configured in Metadata Command Center.|
|`name `|Required. Enter a name for the<br>terms of use.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the letter<br>case when it verifies the uniqueness of the<br>` name` parameter's value. For example, if you<br>try to name the terms of use as "Internal<br>Use Only" while a terms of use called<br>"Internal use only" already exists, the API<br>call fails.|
|`description`|Required. Enter a description for<br>the terms of use.|-|
|`type`|Required. Specify a type for the<br>terms of use.|Your administrator defines the terms of use<br>types in Metadata Command Center. For<br>more information about terms of use, see<br>the_Terms of use_topic in the_Set Up Data_<br>_Marketplace_help.|
|`status `|Required. Specify a status for the<br>terms of use. The status<br>determines whether the terms of<br>use are available to be added to<br>data collections.|Enter one of the following values:<br>- To make the terms of use available, enter<br>` ENABLED`.<br>- To make the terms of use unavailable,<br>enter` DISABLED`.|
|`acknowledgement`|Required. Configure whether you<br>want the Data User to acknowledge<br>the terms of use when they place an<br>order.|Enter one of the following values:<br>- If you want the Data User to acknowledge<br>the terms of use when they place an<br>order, enter` true`.<br>- If you don't want the Data User to<br>acknowledge the terms of use when they<br>place an order, enter` false`.|
|`referenceLink`|Optional. Enter the uniform<br>resource identifier of the resource<br>or location where the data is stored<br>or maintained.|-|


#### Example request
The following example shows how you can use an API to create terms of use: 

```
{
  "items": [
    {
      "refId": "Term23",
      "name": "Company use only",
      "description": "This data should not be accessed by non-company personnel.",
      "status": "ENABLED",
      "type": "NEUTRAL",
      "acknowledgement": true,
      "referenceLink": "sample link"
    },
    {
      "refId": "Term22",
      "name": "Client confidential",
      "description": "Use this for data to be shared with authorized third parties.",
      "status": "DISABLED",
      "type": "NEUTRAL",
      "acknowledgement": false,
      "referenceLink": "sample link"
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a terms of use: 

```
{
  "processingTime": 13051,
  "objects": [
    {
      "index": 1,
      "id": "5f674ca5-f2d1-4465-9576-dae6ff4cf7c0",
      "refId": "Term23",
      "name": "Company use only"
    },
    {
      "index": 2,
      "id": "3893741f-e9cf-4a7e-85de-0d200fe7ddf4",
      "refId": "Term22",
      "name": "Client confidential"
    }
  ]
}
```

The following table describes the parameters of each terms of use that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the terms of use in the` objects` JSON array. This value does not impact how the terms<br>of use is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the terms of use.|
|`refId`|Reference identifier of the terms of use.|
|`name`|Name of the terms of use.|

#### Retrieve terms of use
Use REST APIs to retrieve the details of terms of use in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/termsOfUse**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`ids `|Optional. Enter the system generated<br>unique identifier of a terms of use.|To enter more than one value, use the<br>following format:<br>` ids=<value1>&ids=<value2>`|
|`search`|Optional. Enter the search term that you<br>want to use to find a terms of use.|Ensure that the search term that you<br>enter don't contain an asterisk (*).|
|`fields`|Optional. Enter the fields on which the<br>search term applies. The terms that you<br>entered in the` search ` field parameter<br>are used to search the fields that you<br>specify here.|Enter the following values:<br>-<br>` NAME `<br>-<br>` DESCRIPTION`|
|`createdDateFrom`|Optional. To find terms of use that were<br>created between a date range, enter the<br>initial date when the terms of use were<br>created.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` createdDateFrom ` parameter, ensure<br>that you also enter a value for the<br>` createdDateTo` parameter.|
|`createdDateTo`|Optional. To find terms of use that were<br>created between a date range, enter the<br>latest date when the terms of use were<br>created.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` createdDateTo ` parameter, ensure<br>that you also enter a value for the<br>` createdDateFrom` parameter.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`modifiedDateFrom`|Optional. To find terms of use that were<br>modified between a date range, enter the<br>initial date when the terms of use were<br>modified.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` modifiedDateFrom` parameter,<br>ensure that you also enter a value for<br>the` modifiedDateTo` parameter.|
|`modifiedDateTo`|Optional. To find terms of use that were<br>modified between a date range, enter the<br>latest date when the terms of use were<br>modified.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` modifiedDateTo ` parameter, ensure<br>that you also enter a value for the<br>` modifiedDateFrom` parameter.|
|`status`|Optional. Specify the status of the terms<br>of use. The status indicates whether the<br>terms of use are available to be added to<br>data collections.|Enter one of the following values:<br>- To find the terms of use are<br>available to be added to data<br>collections, enter` ENABLED`.<br>- To find the terms of use aren't<br>available to be added to data<br>collections, enter` DISABLED`.|
|`type`|Optional. Specify the type of the terms of<br>use.|Your administrator defines the terms<br>of use types in Metadata Command<br>Center. For more information about<br>terms of use, see the_Terms of use_<br>topic in the_Set Up Data Marketplace_<br>help.|
|`sortByField `|Optional. Specify the parameters to sort<br>the search results.|To sort the search results, enter one of<br>the following values:<br>-<br>` NAME `<br>-<br>` STATUS `<br>-<br>` TYPE `<br>-<br>` ID `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the<br>search results.|You can sort the search results in the<br>following order:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default is` DESC`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`offset`|Optional. Enter the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum number of<br>results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve terms of use: 

```
https://{{CDMP_URL}}/api/v1/integration/termsOfUse?
search=SK&fields=NAME&fields=DESCRIPTION&ids=7bb0bcc1-23a9-4ffc-9f39-50c7b4df91b0&status=
ENABLED&type=PERMISSIVE&createdDateFrom=2022-01-01&createdDateTo=2022-12-01&modifiedDateF
rom=2022-01-01&modifiedDateTo=2022-12-01&sortByField=MODIFIED_ON&sort=DESC&offset=0&limit
=100
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve terms of use: 

```
{
  "processingTime": 4779,
  "offset": 0,
  "limit": 100,
  "totalCount": 2,
  "objects": [
    {
      "id": "5f674ca5-f2d1-4465-9576-dae6ff4cf7c0",
      "refId": "Term23",
      "name": "Company use only"
      "description": "This data should not be accessed by non-company personnel.",
      "type": "PERMISSIVE",
      "status": "ENABLED",
      "acknowledgement": true,
      "referenceLink": null,
      "createdBy": "5tSQ0vG66z9jS0KBQkY84r",
      "createdOn": "2022-05-17T10:05:21.453Z",
      "modifiedBy": "5tSQ0vG66z9jS0KBQkY84r",
      "modifiedOn": "2022-05-17T10:06:57.433Z"
    }
  ]
}
```

The following table describes the parameters of each terms of use that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of terms of use retrieved.|


|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the terms of use.|
|`refId`|Reference identifier of the terms of use.|
|`name`|Name of the terms of use.|
|`description`|Description of the terms of use.|
|`type`|Type of the terms of use.<br>Your administrator defines the terms of use types in Metadata Command Center. For more<br>information about terms of use, see the_Terms of use_topic in the_Set Up Data Marketplace_help.|
|`status `|The status indicates whether the terms of use are available to be added to data collections. A<br>terms of use can have one of the following statuses:<br>-<br>` ENABLED `. The terms of use are available to be added to data collections.<br>-<br>` DISABLED`. The terms of use isn't available to be added to data collections.|
|`acknowledgement `|Parameter that indicates whether the Data User is required to acknowledge the terms of use<br>when they place an order. The parameter can be one of the following values:<br>-<br>` true `. The Data User must acknowledge the terms of use when they place an order.<br>-<br>` false`. The Data User doesn't need to acknowledge the terms of use when they place an<br>order.|
|`referenceLink`|Uniform resource identifier of the terms of use.|


#### Modify terms of use
Use REST APIs to modify terms of use in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/termsOfUse**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system generated<br>unique identifier of the terms of use<br>that you want to modify.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a terms of use, see<br>“Retrieve terms of use” on page 165.|
|`refId`|Optional. Enter a reference identifier<br>for the terms of use.|If you don't specify a reference identifier,<br>Data Marketplace automatically assigns a<br>unique value to the object. The reference<br>identifier that Data Marketplace<br>automatically generates contains a prefix.<br>The Administrator can specify the prefix of<br>the automatically generated reference<br>identifier in Metadata Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a unique<br>value. Ensure that you don't use the prefix<br>value that is configured in Metadata<br>Command Center.|
|`name`|Required. Enter a name for the terms<br>of use.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the<br>letter case when it verifies the uniqueness<br>of the` name` parameter's value. For<br>example, if you try to name the terms of<br>use as "Internal Use Only" while a terms of<br>use called "Internal use only" already<br>exists, the API call fails.|
|`description`|Required. Enter a description for the<br>terms of use.|-|
|`status`|Required. Specify a status for the<br>terms of use. The status determines<br>whether the terms of use are<br>available to be added to data<br>collections.|Enter one of the following values:<br>- To make the terms of use available,<br>enter` ENABLED`.<br>- To make the terms of use unavailable,<br>enter` DISABLED`.|
|`type`|Required. Specify a type for the<br>terms of use.|Your administrator defines the terms of<br>use types in Metadata Command Center.<br>For more information about terms of use,<br>see the_Terms of use_topic in the_Set Up_<br>_Data Marketplace_help.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`acknowledgement`|Required. Configure whether you<br>want the Data User to acknowledge<br>the terms of use when they place an<br>order.|Enter one of the following values:<br>- If you want the Data User to<br>acknowledge the terms of use when they<br>place an order, enter` true`.<br>- If you don't want the Data User to<br>acknowledge the terms of use when they<br>place an order, enter` false`.|
|`referenceLink`|Optional. Enter the uniform resource<br>identifier of the resource or location<br>where the data is stored or<br>maintained.|-|


#### Example request
The following example shows how you can use an API to modify a terms of use: 

```
{
  "items": [
    {
      "id": "5f674ca5-f2d1-4465-9576-dae6ff4cf7c0",
      "refId": "Term23",
      "name": "Company use only",
      "description": "This data should not be accessed by non-company personnel.",
      "status": "ENABLED",
      "type": "NEUTRAL",
      "acknowledgement": true,
      "referenceLink": "sample link"
    },
    {
      "id": "3893741f-e9cf-4a7e-85de-0d200fe7ddf4",
      "refId": "Term22",
      "name": "Client confidential",
      "description": "Use this for data to be shared with authorized third parties.",
      "status": "DISABLED",
      "type": "NEUTRAL",
      "acknowledgement": false,
      "referenceLink": "sample link"
    }
 ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to modify a terms of use: 

```
{
  "processingTime": 12351,
  "objects": [
    {
      "index": 1,
      "id": "5f674ca5-f2d1-4465-9576-dae6ff4cf7c0",
      "refId": "Term23",
      "name": "Company use only"
    },
    {
      "index": 2,
      "id": "3893741f-e9cf-4a7e-85de-0d200fe7ddf4",
      "refId": "Term22",

      "name": "Client confidential"
    }
  ]
}
```

The following table describes the parameters of each terms of use that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the terms of use in the` objects` JSON array. This value does not impact how the terms<br>of use is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the terms of use.|
|`refId`|Reference identifier of the terms of use.|
|`name`|Name of the terms of use.|


#### Update the status of a terms of use
Use REST APIs to enable or disable terms of use in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/termsOfUse/status**|
|Method|PATCH|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system<br>generated unique identifier of<br>the terms of use.|For more information about how you can use an<br>API to get the unique identifier of a terms of use,<br>see<br>“Retrieve terms of use” on page 165.|
|`status `|Required. Specify a status for<br>the terms of use. The status<br>determines whether the terms<br>of use are available to be<br>added to data collections.|Enter one of the following values:<br>- To make the terms of use available, enter<br>` ENABLED `.<br>- To make the terms of use unavailable, enter<br>` DISABLED`.|


#### Example request
The following example shows how you can use an API to update the status of a terms of use: 

```
{
  "items": [
    {
      "id": "7bb0bcc1-23a9-4ffc-9f39-50c7b4df91b0",
      "status": "ENABLED"
    },
    {
      "id": "f8504f82-3b26-41b8-ad1c-eea5d1a2a343",
      "status": "ENABLED"
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the status of the terms of use is updated successfully: 

```
204 OK
```

### Usage types
A usage type defines the contexts within which an individual can use data. Stakeholders can utilize usage types to specify the intended use of their data collections. Data Users can utilize usage types to cite their intention when they order a collection. 

#### Create usage type
Use REST APIs to create usage types in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/usageContext**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`refId`|Optional. Enter a reference<br>identifier for the usage type.|If you don't specify a reference identifier, Data<br>Marketplace automatically assigns a unique<br>value to the object. The reference identifier that<br>Data Marketplace automatically generates<br>contains a prefix. The Administrator can specify<br>the prefix of the automatically generated<br>reference identifier in Metadata Command<br>Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure that<br>you don't use the prefix value that is configured<br>in Metadata Command Center.|
|`name`|Required. Enter a name for the<br>usage type.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the letter<br>case when it verifies the uniqueness of the` name`<br>parameter's value. For example, if you try to<br>name a usage type as "Sales Analytics" while a<br>usage type called "Sales analytics" already<br>exists, the API call fails.|
|`description`|Required. Enter a description<br>for the usage type.|-|
|`color`|Required. Specify a color<br>indicator for the usage type.<br>Assigning a unique color<br>indicator allows Data<br>Marketplace users to identify a<br>usage type easily.|Enter the hexadecimal value that represents the<br>color that you want to use.|
|`status `|Required. Specify a status for<br>the usage type. The status<br>determines whether a usage<br>type is available to be added to<br>a data collection or to be<br>selectable on an order.|Enter one of the following values:<br>- To make the usage type available, enter<br>` ACTIVE `.<br>- To make the usage type unavailable, enter<br>` INACTIVE`.|


#### Example request
The following example shows how you can use an API to create an usage type: 

```
{
  "items": [
    {
      "refId": "UType314",
      "name": "Retail-Use",
      "description": "This is for standard retail operations.",
      "color": "#aAbe2A",
      "status": "INACTIVE"
    }
  ]
}
```


#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create an usage type: 

```
{
         "result":
             {
              "processingTime":2023,
              "objects":
                  [
                    {
                     "index": 1,
                     "id":"964dfd5f-0add-4749-9ed8-79016a8e9f69",
                     "refId":"UType314",
                     "name":"Retail-Use"
                    }
                  ]
              }
          }
```

The following table describes the parameters of each usage type that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the usage type in the` objects` JSON array. This value does not impact how the usage<br>type is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the usage type.|
|`refId`|Reference identifier of the usage type.|
|`name`|Name of the usage type.|


#### Retrieve usage type
Use REST APIs to retrieve the details of usage types in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/usageContext**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`ids `|Optional. Enter the system generated<br>unique identifier of a usage type.|To enter more than one value, use the<br>following format:<br>` ids=<value1>&ids=<value2>`|
|`search`|Optional. Enter the search term that you<br>want to use to find a usage type.|Ensure that the search term that you<br>enter don't contain an asterisk (*).|
|`fields`|Optional. Enter the fields on which the<br>search term applies. The terms that you<br>entered in the` search ` field parameter<br>are used to search the fields that you<br>specify here.|Enter the following values:<br>-<br>` NAME `<br>-<br>` DESCRIPTION`|
|`createdDateFrom`|Optional. To find usage types that were<br>created between a date range, enter the<br>initial date when the usage types were<br>created.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` createdDateFrom ` parameter, ensure<br>that you also enter a value for the<br>` createdDateTo` parameter.|
|`createdDateTo`|Optional. To find usage types that were<br>created between a date range, enter the<br>latest date when the usage types were<br>created.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` createdDateTo ` parameter, ensure<br>that you also enter a value for the<br>` createdDateFrom` parameter.|
|`modifiedDateFrom`|Optional. To find usage types that were<br>modified between a date range, enter<br>the initial date when the usage types<br>were modified.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` modifiedDateFrom ` parameter, ensure<br>that you also enter a value for the<br>` modifiedDateTo` parameter.|
|`modifiedDateTo`|Optional. To find usage types that were<br>modified between a date range, enter<br>the latest date when the usage types<br>were modified.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` modifiedDateTo ` parameter, ensure<br>that you also enter a value for the<br>` modifiedDateFrom` parameter.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortByField `|Optional. Specify the parameters to sort<br>the search results.|To sort the search results, enter one of<br>the following values:<br>-<br>` NAME `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the<br>search results.|You can sort the search results in the<br>following order:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default is` DESC`.|
|`status`|Optional. Specify the status of the usage<br>type. The status indicates whether a<br>usage type is available to be added to a<br>data collection or to be selectable on an<br>order.|Enter one of the following values:<br>- To find the usage types that are<br>available, enter` ACTIVE`.<br>- To find the usage types that aren't<br>available, enter` INACTIVE`.|
|`offset`|Optional. Enter the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum number of<br>results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve an usage type: 

```
https://{{CDMP_URL}}/api/v1/integration/usageContext?

ids=c20c8fb4-1cb4-477f-8ff9-52908a3c01d3&search=abcd&fields=NAME&createdDateFrom=2022%2F0
1%2F01&createdDateTo=2022%2F07%2F22&modifiedDateTo=2022%2F02%2F01&modifiedDateFrom=2022%2
F07%2F22&status=ACTIVE&sortByField=NAME&sort=ASC&offset=0&limit=20
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve an usage type: 

```
{
         "result":
            {
              "offset":0,
              "limit":50,
              "totalCount":1,
              "objects":
                  [
                    {

                     "id":"964dfd5f-0add-4749-9ed8-79016a8e9f69",
                     "refId":"UType314",
                     "name": "Retail-Use",
                     "description": "This is for standard retail operations.",
                     "color": "#aAbe2A",
                     "status":"INACTIVE",
                     "createdBy":"f400dUqjPuaiJ9KBrzOhiB",
                     "createdOn":"2022-07-26T11:19:17.550Z",
                     "modifiedBy":"f400dUqjPuaiJ9KBrzOhiB",
                     "modifiedOn":"2022-07-26T11:19:17.550Z"
                    }
                  ]
            }
        }
```

The following table describes the parameters of each usage type that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of usage types retrieved.|
|`id`|System generated unique identifier of the usage type.|
|`refId`|Reference identifier of the usage type.|
|`name`|Name of the usage type.|
|`description`|Description of the usage type.|
|`color`|A hexadecimal value that represents the color indicator of the usage type.|
|`status `|The status indicates whether a usage type is available to be added to a data collection or to be<br>selectable on an order. A usage type can have one of the following values:<br>-<br>` ACTIVE `. The usage type is available.<br>-<br>` INACTIVE`. The usage type is available.|
|`createdBy`|System generated unique identifier of the user account that created the usage type.|
|`createdOn`|Date when the usage type was created.|
|`modifiedBy`|System generated unique identifier of the latest user account that modified the usage type.|
|`modifiedOn`|Latest date when the usage type was modified.|


#### Modify usage type
Use REST APIs to modify usage types in Data Marketplace. 


#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/usageContext**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system<br>generated unique identifier of the<br>usage type that you want to modify.|For more information about how you can<br>use an API to get the unique identifier of a<br>usage type, see<br>“Retrieve usage type” on page 174.|
|`refId`|Optional. Enter a reference identifier<br>for the usage type.|If you don't specify a reference identifier,<br>Data Marketplace automatically assigns a<br>unique value to the object. The reference<br>identifier that Data Marketplace<br>automatically generates contains a prefix.<br>The Administrator can specify the prefix of<br>the automatically generated reference<br>identifier in Metadata Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a unique<br>value. Ensure that you don't use the prefix<br>value that is configured in Metadata<br>Command Center.|
|`name`|Required. Enter a name for the usage<br>type.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the<br>letter case when it verifies the uniqueness<br>of the` name` parameter's value. For<br>example, if you try to name a usage type as<br>"Sales Analytics" while a usage type called<br>"Sales analytics" already exists, the API<br>call fails.|
|`description`|Required. Enter a description for the<br>usage type.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`color`|Required. Specify a color indicator<br>for the usage type. Assigning a<br>unique color indicator allows Data<br>Marketplace users to identify a<br>usage type easily.|Enter the hexadecimal value that<br>represents the color that you want to use.|
|`status `|Required. Specify a status for the<br>usage type. The status determines<br>whether a usage type is available to<br>be added to a data collection or to<br>be selectable on an order.|Enter one of the following values:<br>- To make the usage type available, enter<br>` ACTIVE`.<br>- To make the usage type unavailable,<br>enter` INACTIVE`.|


#### Example request
The following example shows how you can use an API to modify an usage type: 

```
{
  "items": [
    {
      "id": "964dfd5f-0add-4749-9ed8-79016a8e9f69",
      "refId": "UType314",
      "name": "Retail-Use",
      "description": "This is for standard retail operations.",
      "status": "ACTIVE"
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to modify an usage type: 

```
       {
        "result":
           {
            "processingTime":1286,
            "objects":
                [
                  {
                   "index": 1,
                   "id":"964dfd5f-0add-4749-9ed8-79016a8e9f69",
                   "refId":"UType314",
                   "name":"Retail-Use"
                  }
                ]
            }
        }
```


The following table describes the parameters of each usage type that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the usage type in the` objects` JSON array. This value does not impact how the usage<br>type is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the usage type.|
|`refId`|Reference identifier of the usage type.|
|`name`|Name of the usage type.|


#### Update the status of an usage type
Use REST APIs to enable or disable usage types in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/usageContext/status**|
|Method|PATCH|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system<br>generated unique identifier of<br>the usage type.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a usage type, see<br>“Retrieve usage type” on page 174.|
|`status `|Required. Specify a status for<br>the usage type. The status<br>determines whether a usage<br>type is available to be added to<br>a data collection or to be<br>selectable on an order.|Enter one of the following values:<br>- To make the usage type available, enter<br>` ACTIVE `.<br>- To make the usage type unavailable, enter<br>` INACTIVE`.|


#### Example request
The following example shows how you can use an API to update the status of an usage type: 

```
{

  "items": [

      {
      "id": "bd1bcf0a-c05c-4a12-b5df-67215475cf25",
      "status": "INACTIVE"
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the status of the usage type is updated successfully: `204 OK` 

### Cost centers
A cost center is an area or department within an organization that is generally represented by a unique identifier to which business expenses are allocated. If there is a cost required to deliver the data to a Data User or if the there is a cost required to create the data collection that was requested by a Data User, the cost can be charged to the cost center of the Data User. 

#### Create cost centers
Use a REST API to create cost centers in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/costCenters**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`name`|Required. Enter a name for the<br>cost center.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the letter<br>case when it verifies the uniqueness of the` name`<br>parameter's value. For example, if you try to<br>name a cost center as "MKT010" while a cost<br>center called "mkt0101" already exists, the API<br>call fails.|
|`description`|Optional. Enter a description<br>for the cost center.|-|


**Note:** You can create only 30 cost centers per API call. 

#### Example request
The following example shows how you can use an API to create a cost center: 

```
{
  "items": [
    {
      "name": "RTL-367APJ",
      "description": "The cost center for the APJ retail department."
    },
    {
      "name": "RTL-367EMEA",
      "description": "The cost center for the EMEA retail department."
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a cost center: 

```
{
  "processingTime": 23297,
  "objects": [
    {
      "index": 1,
      "id": "980dfbc0-c7c0-4e24-ba47-4477bb9bf19c",
      "refId": "320dnbc0-e4r0-4r15-by42-3457bg7hf11x",
      "name": "RTL-367APJ"
    },
    {
      "index": 2,
      "id": "8039d329-46fa-4048-b8e0-9e52a6d73583",
      "refId": "783rebc4-c7v5-6e23-xz17-4937bb3nm13t",
      "name": "RTL-367EMEA"
    }
  ]
}
```

The following table describes the parameters of each cost center that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the cost center in the` objects` JSON array. This value does not impact how the cost<br>center is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the cost center.|
|`refId`|Reference identifier of the cost center.|
|`name`|Name of the cost center.|


#### Retrieve cost centers
Use a REST API to retrieve the details of cost centers in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/costCenters**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`ids `|Optional. Enter the system generated unique<br>identifier of a cost center.|To enter more than one value,<br>use the following format:<br>` ids=<value1>&ids=<value2`<br>`>`|
|`search`|Optional. Enter the search term that you want to<br>use to find a cost center.|Ensure that the search term<br>that you enter don't contain an<br>asterisk (*).|
|`fields `|Optional. Enter the fields on which the search<br>term applies. The terms that you entered in the<br>` search ` field parameter are used to search the<br>fields that you specify here.|Enter the following values:<br>-<br>` NAME `<br>-<br>` DESCRIPTION`|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`createdDateFrom `|Optional. To find cost centers that were created<br>between a date range, enter the initial date when<br>the cost center were created.|To specify a date, use the<br>` YYYY-MM-DD` format. The value<br>that you specify is<br>automatically converted and<br>stored in the Coordinated<br>Universal Time (UTC) time<br>standard.<br>If you have specified a value<br>for the` createdDateFrom `<br>parameter, ensure that you<br>also enter a value for the<br>` createdDateTo` parameter.|
|`createdDateTo `|Optional. To find cost centers that were created<br>between a date range, enter the latest date when<br>the cost center were created.|To specify a date, use the<br>` YYYY-MM-DD` format. The value<br>that you specify is<br>automatically converted and<br>stored in the Coordinated<br>Universal Time (UTC) time<br>standard.<br>If you have specified a value<br>for the` createdDateTo `<br>parameter, ensure that you<br>also enter a value for the<br>` createdDateFrom` parameter.|
|`modifiedDateFrom `|Optional. To find cost centers that were<br>modified between a date range, enter the initial<br>date when the cost center were modified.|To specify a date, use the<br>` YYYY-MM-DD` format. The value<br>that you specify is<br>automatically converted and<br>stored in the Coordinated<br>Universal Time (UTC) time<br>standard.<br>If you have specified a value<br>for the` modifiedDateFrom `<br>parameter, ensure that you<br>also enter a value for the<br>` modifiedDateTo` parameter.|
|`modifiedDateTo `|Optional. To find cost centers that were<br>modified between a date range, enter the latest<br>date when the cost center were modified.|To specify a date, use the<br>` YYYY-MM-DD` format. The value<br>that you specify is<br>automatically converted and<br>stored in the Coordinated<br>Universal Time (UTC) time<br>standard.<br>If you have specified a value<br>for the` modifiedDateTo `<br>parameter, ensure that you<br>also enter a value for the<br>` modifiedDateFrom`<br>parameter.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortByField `|Optional. Specify the parameters to sort the<br>search results.|To sort the search results,<br>enter one of the following<br>values:<br>-<br>` NAME `<br>-<br>` ID `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the search<br>results.|Enter one of the following<br>values:<br>- To sort the search results by<br>ascending order, enter` ASC `.<br>- To sort the search results by<br>descending order, enter<br>` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum number of results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve cost centers: 

```
https://{{CDMP_URL}}/api/v1/integration/costCenters?
search=SK&fields=NAME&ids=8039d329-46fa-4048-
b8e0-9e52a6d73583&createdDateFrom=2012-09-12&createdDateTo=2023-09-12&modifiedDateFrom=20
12-09-12&modifiedDateTo=2023-09-12&sortByField=MODIFIED_ON&sort=DESC&offset=0&limit=1
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve a cost center: 

```
{
  "processingTime": 7986,
  "offset": 0,
  "limit": 1,
  "totalCount": 1,
  "objects": [
    {
      "id": "980dfbc0-c7c0-4e24-ba47-4477bb9bf19c",
      "name": "RTL-367APJ",
      "description": "The cost center for the APJ retail department."
      "createdBy": "3q961bWdAHqjfSS7orgR8N",
      "createdOn": "2022-07-26T11:18:20.029Z",
      "modifiedBy": "3q961bWdAHqjfSS7orgR8N",
      "modifiedOn": "2022-07-26T11:23:32.881Z"
```


```
    }
  ]
}
```

The following table describes the parameters of each cost center that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of cost centers retrieved.|
|`id`|System generated unique identifier of the cost center.|
|`name`|Name of the cost center.|
|`description`|Description of the cost center.|
|`createdBy`|System generated unique identifier of the user account that created the cost center.|
|`createdOn`|Date when the cost center was created.|
|`modifiedBy`|System generated unique identifier of the latest user account that modified the cost center.|
|`modifiedOn`|Latest date when the cost center was modified.|


#### Modify cost centers
Use a REST API to modify cost centers in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/costCenters**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system<br>generated unique identifier of<br>the cost center that you want<br>to modify.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a cost center, see<br>“Retrieve cost centers” on page 183.|
|`name`|Required. Enter a name for the<br>cost center.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the letter<br>case when it verifies the uniqueness of the` name`<br>parameter's value. For example, if you try to<br>name a cost center as "MKT010" while a cost<br>center called "mkt010" already exists, the API<br>call fails.|
|`description`|Optional. Enter a description<br>for the cost center.|-|


#### Example request
The following example shows how you can use an API to modify a cost center: 

```
{
  "items": [
    {
      "id": "980dfbc0-c7c0-4e24-ba47-4477bb9bf19c",
      "name": "RTL-367APJ",
      "description": "The cost center for the APJ retail department."
    },
    {
      "id": "8039d329-46fa-4048-b8e0-9e52a6d73583",
      "name": "RTL-367EMEA",
      "description": "The cost center for the EMEA retail department."
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to modify a cost center: 

```
{
  "processingTime": 23913,
  "objects": [
    {
      "index": 1,
      "id": "980dfbc0-c7c0-4e24-ba47-4477bb9bf19c",
      "refId": "320dnbc0-e4r0-4r15-by42-3457bg7hf11x",
      "name": "RTL-367APJ"
    },
    {
      "index": 2,
      "id": "8039d329-46fa-4048-b8e0-9e52a6d73583",
      "refId": "783rebc4-c7v5-6e23-xz17-4937bb3nm13t",
      "name": "RTL-367EMEA"
    }
```


```
  ]

}
```

The following table describes the parameters of each cost center that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the cost center in the` objects` JSON array. This value does not impact how the cost<br>center is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the cost center.|
|`refId`|Reference identifier of the cost center.|
|`name`|Name of the cost center.|

## Chapter 7: Manage Data Marketplace content
This chapter includes the following topics: 

- Data elements, 189 

- Data assets, 200 

### Data elements
A data element is the smallest unit of data that is present in Data Marketplace. A grouping of one or more related data elements forms a data asset. 

#### Create data elements
Use a REST API to create data elements in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataAssets/<id>/dataElements**|
||`<id>`: Required. The system generated unique identifier of the enabled data asset to which you want to<br>add the new data element.|
||To get the system generated unique identifier of a data asset, open the data asset. The data asset page's<br>URL contains the system generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/dataAsset/8c4c2089-6c7e-4696-a8f6-44f87639d65c?dtn=Table_Profiling_AK_1676302415587&tab=dataElements`, the system<br>generated unique identifier is` 8c4c2089-6c7e-4696-a8f6-44f87639d65c`.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>data asset, see<br>“Retrieve data assets” on page 203.|
|Method|POST|


**Note:** You can use the API to add a data element only to a data asset that is native to Data Marketplace.

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`name`|Required. Enter a name for the data<br>element.|-|
|`refId`|Optional. Enter a reference identifier<br>for the data element.|If you don't specify a reference identifier,<br>Data Marketplace automatically assigns a<br>unique value to the object. The reference<br>identifier that Data Marketplace<br>automatically generates contains a<br>prefix. The Administrator can specify the<br>prefix of the automatically generated<br>reference identifier in Metadata<br>Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a unique<br>value. Ensure that you don't use the prefix<br>value that is configured in Metadata<br>Command Center.|
|`description`|Required. Enter a description for the<br>data element.|-|
|`referenceLink`|Optional. Enter the uniform resource<br>identifier of the data element in the<br>data source.|-|
|`technicalType`|Optional. Enter the type of the data<br>element as defined in the data<br>source.|-|
|`technicalName`|Optional. Enter the name of the data<br>element as it appears in the data<br>source.|-|
|`type`|Optional. Enter a type for the data<br>element.|-|
|`status`|Required. Specify a status for the<br>data element.|Enter one of the following values:<br>- To make the data elements available,<br>enter` ENABLED`.<br>- To make the data elements<br>unavailable, enter` DISABLED`.|


#### Example request
The following example shows how you can use an API to create a data element: 

```
{

    "items": [
        {
            "name": "Address",

            "refId": "DAET3401",
            "description": "Address of the customer",
            "referenceLink": "URL://DATA_ASSET",
            "technicalType": "string",
            "technicalName": "string",
            "type": "TEXT",
            "status": "ENABLED"
        }
    ]
}
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a data element: 

```
{
    "processingTime": 2137,
    "objects": [
        {
            "index": 1,
            "id": "d917d035-d9a0-3894-a8cc-16b506c4decd",
            "refId": "DAET3401",
            "name": "Address"
        }
    ]
}
```

The following table describes the parameters of each data element that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the data element in the` objects` JSON array. This value does not impact how the data<br>element is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the data element.|
|`refId`|Reference identifier of the data element.|
|`name`|Name of the data element.|


#### Retrieve data elements
Use a REST API to retrieve the details of data elements in Data Marketplace. 


#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataAssets/<id>/dataElements**|
||`<id>`: Required. The system generated unique identifier of the data asset that contains the data element.|
||To get the system generated unique identifier of a data asset, open the data asset. The data asset page's<br>URL contains the system generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/dataAsset/8c4c2089-6c7e-4696-a8f6-44f87639d65c?dtn=Table_Profiling_AK_1676302415587&tab=dataElements`, the system<br>generated unique identifier is` 8c4c2089-6c7e-4696-a8f6-44f87639d65c`.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>data asset, see<br>“Retrieve data assets” on page 203.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortByField `|Optional. Specify the parameters to<br>sort the search results.|To sort the search results, enter one of<br>the following values:<br>-<br>` ID `<br>-<br>` NAME `<br>-<br>` STATUS `<br>-<br>` TECHNICAL_TYPE `<br>-<br>` TECHNICAL_NAME `<br>-<br>` TYPE`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the<br>search results.|You can sort the search results in the<br>following order:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting index for<br>the paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum number<br>of results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload.

#### Example request
The following example shows how you can use an API to retrieve the data elements of a data asset: 

```
https://{{CDMP_URL}}/api/v1/integration/dataAssets/r517d035-g1a0-6813-v4cd-23e506c4devr/
dataElements?limit=100&sortByField=NAME&sort=DESC
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the data elements of a data asset: 

```
{
  "processingTime": 214,
  "offset": 0,
  "limit": 100,
  "totalCount": 8,
  "objects": [
    {
      "id": "c317d035-d9a0-3144-a8cc-18v506c4dece",
      "refId": "DLM-371",
      "name": "Retail outlet list",
      "description": "A list of all retail outlets.",
      "referenceLink": "URL://AXON",
      "technicalType": "string",
      "technicalName": "string",
      "type": "column",
      "status": "ENABLED",
      "businessName": "Global Retail Outlets",
      "businessDesc": "A list of all retail outlets around the global.",
      "createdBy": "7w2uGIARApUgxniNoHHWsJ",
      "createdOn": "2022-03-08T17:05:28.208Z",
      "modifiedBy": "7w2uGIARApUgxniNoHHWsJ",
      "modifiedOn": "2022-03-08T17:09:34.623Z"
    }
  ]
}
```

The following table describes the parameters of each data element that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of data elements retrieved.|
|`id`|System generated unique identifier of the data element.|
|`refId`|Reference identifier of the data element.|
|`name`|Name of the data element.|
|`description`|Description of the data element.|
|`referenceLink`|Uniform resource identifier of the location in the data source where the data element is stored.|
|`technicalType`|Type of the data element as defined in the data source.|
|`technicalName`|Name of the data element as it appears in the data source.|


|**Parameter**|**Description**|
|---|---|
|`type`|Type of the data element.|
|`status `|Status of the data element.<br>-<br>` ENABLED `. The data element is available.<br>-<br>` DISABLED`. The data element is unavailable.|
|`businessName`|Business name of the data element as defined in Data Governance and Catalog.<br>In Data Governance and Catalog, technical assets might have user-friendly business names<br>assigned to them. Users can assign the business names manually, or the system can<br>automatically assign the names. For more information, see the_Enrich technical assets with_<br>_governance context_topic in the_Working With Assets_help for Data Governance and Catalog.|
|`businessDesc`|Business description of the data element as defined in Data Governance and Catalog.<br>In Data Governance and Catalog. For more information, see the_Enrich technical assets with_<br>_governance context_topic in the_Working With Assets_help for Data Governance and Catalog.|
|`createdBy`|System generated unique identifier of the user account that created the data element.|
|`createdOn`|Date when the data element was created.|
|`modifiedBy`|System generated unique identifier of the latest user account that modified the data element.|
|`modifiedOn`|Latest date when the data element was modified.|


#### Modify data elements
Use a REST API to modify data elements in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataAssets/<id>/dataElements**|
||`<id>`: Required. The system generated unique identifier of the data asset for which you want to modify its<br>data element.|
||To get the system generated unique identifier of a data asset, open the data asset. The data asset page's<br>URL contains the system generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/dataAsset/8c4c2089-6c7e-4696-a8f6-44f87639d65c?dtn=Table_Profiling_AK_1676302415587&tab=dataElements`, the system<br>generated unique identifier is` 8c4c2089-6c7e-4696-a8f6-44f87639d65c`.<br>For more information about how you can use an API to get the system generated unique identifier of a<br>data asset, see<br>“Retrieve data assets” on page 203.|
|Method|PUT|


**Note:** You can use the API to modify only a data element that is native to Data Marketplace. 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system<br>generated unique identifier of<br>the data element that you want<br>to modify.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a data element, see<br>“Retrieve data elements” on page 191.|
|`name`|Required. Enter a name for the<br>data element.|-|
|`refId`|Optional. Enter a reference<br>identifier for the data element.|If you don't specify a reference identifier, Data<br>Marketplace automatically assigns a unique<br>value to the object. The reference identifier that<br>Data Marketplace automatically generates<br>contains a prefix. The Administrator can specify<br>the prefix of the automatically generated<br>reference identifier in Metadata Command<br>Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure that<br>you don't use the prefix value that is configured<br>in Metadata Command Center.|
|`description`|Required. Enter a description<br>for the data element.|-|
|`referenceLink`|Optional. Enter the uniform<br>resource identifier of the data<br>element in the data source.|-|
|`technicalType`|Optional. Enter the type of the<br>data element as defined in the<br>data source.|-|
|`technicalName`|Optional. Enter the name of the<br>data element as it appears in<br>the data source.|-|
|`type`|Optional. Enter a type for the<br>data element.|-|
|`status `|Required. Specify a status for<br>the data element.|Enter one of the following values:<br>- To make the data elements available, enter<br>` ENABLED `.<br>- To make the data elements unavailable, enter<br>` DISABLED`.|


#### Example request
The following example shows how you can use an API to modify a data element: 

```
{
    "items": [
        {
            "id": "d917d035-d9a0-3894-a8cc-16b506c4decd",
            "name": "Updated Address",
            "refId": "DAET3401",
```


```
            "description": "Updated Address of the customer",
            "referenceLink": "URL://DATA_ASSET",
            "technicalType": "string",
            "technicalName": "string",
            "type": "TEXT",
            "status": "ENABLED"
        }
    ]
}
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to modify a data element: 

```
{
    "processingTime": 1986,
    "objects": [
        {
            "index": 1,
            "id": "d917d035-d9a0-3894-a8cc-16b506c4decd",
            "refId": "DAET3401",
            "name": "Updated Address"
        }
    ]
}
```

The following table describes the parameters of each data element that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the data element in the` objects` JSON array. This value does not impact how the data<br>element is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the data element.|
|`refId`|Reference identifier of the data element.|
|`name`|Name of the data element.|


#### Delete data elements
Use a REST API to delete data elements in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataElements/<id>**|
||`<id>`: Required. Enter the system generated unique identifier of the data element that you want to delete.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>data element, see<br>“Retrieve data elements” on page 191.|
|Method|DELETE|

**Note:** You can use the API to delete only a data element that was created in Data Marketplace. If you use the API to delete an element that originates from an external system such as Data Governance and Catalog, the API call fails. 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 

#### Example request
The following example shows how you can use an API to delete a data element: 

```
https://{{CDMP_URL}}/api/v1/integration/dataElements/d2313aed-330e-44a0-bcfa-380ee4a95a62
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data element is deleted successfully: 

```
204 OK code
```

#### Manage the data quality information of a data element
Use a REST API to specify a data quality score for a data element in Data Marketplace or to modify the data quality score of a data element. 

For more information about data quality rules, see the _Data Quality Per Asset section_ topic in the _Introduction and Getting Started_ help. 

#### Endpoint and method
The following table lists the connection parameters for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataElements/dq/records**|
|Method|PUT|


**Note:** Before you call this API, consider the following: 

- The number of data quality rules that you can assign to a data element per API call depends on the cloud provider the IDMC POD is hosted. The following table lists the number of data quality rules that you can assign to a data element per API call on each cloud provider: 

|**Cloud provider**|**Number of data quality rules**|
|---|---|
|Amazon Web Services|30|
|Microsoft Azure|10|


- You can call a maximum of 100 APIs per minute. 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`dataElementId`|Required. Enter the system generated<br>unique identifier of the data element<br>for which you want to specify a data<br>quality score.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a data element, see<br>“Retrieve data elements” on page 191.|
|`ruleRefId`|Required. Enter the reference identifier<br>of the data quality rule that is run on<br>the data element as defined in the<br>external data quality system.|-|
|`ruleName`|Required. Enter the name of the data<br>quality rule that is run on the data<br>element.|-|
|`ruleDescription`|Optional. Enter the description of the<br>data quality rule that is run on the data<br>element.|-|
|`type `|Required. Specify the dimension for<br>which the data quality rule is run.|Enter one of the following values:<br>-<br>` Accuracy `<br>-<br>` Validity `<br>-<br>` Completeness `<br>-<br>` Consistency `<br>-<br>` Uniqueness `<br>-<br>` Timeliness`|
|`score`|Required. Enter the data quality score.|Enter a value from 0 through 100.|
|`rowCount`|Required. Enter the number of rows on<br>which the data quality rule is executed.|-|
|`exceptionCount`|Required. Enter the acceptable number<br>of unresolved data quality issues in<br>your data.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`executionDate`|Optional. Enter the date when the data<br>quality rule is executed.|-|
|`thresholds`|Required. Enter a threshold.|Threshold is the minimum acceptable<br>data quality score of a data element. The<br>thresholds that you define help users to<br>distinguish helpful data from the<br>unhelpful data.|
|`thresholds > name`|Required. Enter the name of the color<br>used by Data Marketplace to represent<br>a threshold.|Enter one of the following values:<br>- The` GREEN` value indicates that the<br>data quality score is above the target<br>value, represents a "Good" score.<br>- The` AMBER` value indicates that the<br>data quality score is between the<br>threshold and target value, represents<br>an "Acceptable" score.|
|`thresholds > value `|Required. Enter the minimum<br>acceptable data quality score.|You must specify a value for both the<br>` GREEN` and` AMBER` threshold.|


#### Example request
The following example shows how you can use an API to specify a data quality score for a data element: 

```
{
  "items": [
    {
      "dataElementId": "d917d035-d9a0-3894-a8cc-16b506c4decd",
      "ruleRefId": "CDMP-RO-01",
      "ruleName": "CDMP Rule Occurrence 1",
      "ruleDescription": "Rule Description",
      "type": "Accuracy",
      "score": 85,
      "rowCount": 100,
      "exceptionCount": 15,
      "executionDate": "2022-12-07T00:00:00.000Z",
      "thresholds": [
        {
          "name": "GREEN",
          "value": 88
        },
        {
          "name": "AMBER",
          "value": 70
        }
      ]
    }
  ]
}
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to specify a data quality score for a data element: 

```
{
```


```
  "processingTime": 689,
  "objects": [
    {
      "index": 1,
      "id": "g377d066-f9e4-5674-f7cv-13g666c4dtyd",
      "refId": "CDMP-RO-01",
      "name": "CDMP Rule Occurrence 1"
    }
  ]
}
```

The following table contains the parameters in the response body: 

|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the data quality score information of the data element.|
|`refId`|Reference identifier of the data quality rule that is assigned to the data element.|
|`name`|Name of the data quality rule that is assigned to the data element.|


### Data assets
A representation of data in an organization. Data assets might contain relevant data elements and are contained in one or more data collections. 

#### Create data assets
Use a REST API to create data assets in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataAssets**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`name`|Required. Enter a name for the<br>data asset.|-|
|`refId`|Optional. Enter a reference<br>identifier for the data asset.|If you don't specify a reference identifier, Data<br>Marketplace automatically assigns a unique<br>value to the object. The reference identifier that<br>Data Marketplace automatically generates<br>contains a prefix. The Administrator can specify<br>the prefix of the automatically generated<br>reference identifier in Metadata Command<br>Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure that<br>you don't use the prefix value that is configured<br>in Metadata Command Center.|
|`description`|Required. Enter a description<br>for the data asset.|-|
|`source`|Required. Specify the source<br>system from which the data is<br>supplied to Data Marketplace.|-|
|`descriptiveSource`|Optional. Specify the source<br>application from which the<br>description of the data asset is<br>taken.|-|
|`type`|Required. Enter a type for the<br>data asset.|-|
|`refLink`|Optional. Enter the uniform<br>resource identifier of the<br>location in the data source<br>where the data asset is stored.|-|
|`assetLocation`|Optional. Enter the location of<br>the data asset in the data<br>source.|-|
|`assetLocationDescription`|Optional. Enter a description<br>for the location in the data<br>source where the data asset is<br>stored.|-|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`technicalAssetName`|Optional. Enter the name of the<br>data asset as it appears in the<br>data source.|-|
|`status `|Optional. Specify a status for<br>the data asset. The status<br>indicates whether the data<br>asset is available to be added<br>to data collections.|Enter one of the following values:<br>- To make the data asset available, enter<br>` ENABLED `.<br>- To make the data asset unavailable, enter<br>` DISABLED`.<br>Default value is` ENABLED`.|


#### Example request
The following example shows how you can use an API to create a data asset: 

```
{
    "items": [
        {
            "name": "Accounts_Customers",
            "refId": "CMDS001",
            "description": "Customer Information",
            "source": "CDLG",
            "descriptiveSource": "AXON",
            "type": "Table",
            "refLink": "URL://AXON",
            "assetLocation": "string",
            "assetLocationDescription": "string",
            "technicalAssetName": "string",
            "status": "ENABLED"
            }
         }
     ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create a data asset: 

```
{
    "processingTime": 2130,
    "objects": [
        {
            "index": 1,
            "id": "49d4856a-90b3-3584-abd6-47db0fe9acb6",
            "refId": "CMDS001",
            "name": "Accounts_Customers"
        }
    ]
}
```

The following table describes the parameters of each data asset that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the data asset in the` objects` JSON array. This value does not impact how the data<br>asset is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the data asset.|
|`refId`|Reference identifier of the data asset.|
|`name`|Name of the data asset.|


#### Retrieve data assets
Use REST APIs to retrieve the details of data assets in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataAssets**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`search`|Optional. Enter a search term to<br>find the data assets.|Ensure that the search term that you enter<br>don't contain an asterisk (*).|
|`fields `|Optional. Enter one or more fields<br>that apply to the search. The<br>terms you enter in the search<br>` fields ` parameter are searched<br>in the fields that you enter here.|Enter the following values:<br>-<br>` NAME `<br>-<br>` DESCRIPTION `<br>-<br>` SOURCE `<br>-<br>` TYPE `<br>-<br>` DESCRIPTIVE_SOURCE`|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`createdDateFrom`|Optional. To find data assets<br>created between a date range,<br>enter the starting date.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in the<br>Coordinated Universal Time (UTC) time<br>standard.<br>If you have specified a value for the<br>` createdDateFrom ` parameter, ensure that<br>you also enter a value for the<br>` createdDateTo` parameter.|
|`createdDateTo`|Optional. To find data assets<br>created between a date range,<br>enter the ending date.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in the<br>Coordinated Universal Time (UTC) time<br>standard.<br>If you have specified a value for the<br>` createdDateTo` parameter, ensure that you<br>also enter a value for the` createdDateFrom`<br>parameter.|
|`modifiedDateFrom`|Optional. To find data assets<br>modified between a date range,<br>enter the starting date.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in the<br>Coordinated Universal Time (UTC) time<br>standard.<br>If you have specified a value for the<br>` modifiedDateFrom ` parameter, ensure that<br>you also enter a value for the<br>` modifiedDateTo` parameter.|
|`modifiedDateTo`|Optional. To find data assets<br>modified between a date range,<br>enter the ending date.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in the<br>Coordinated Universal Time (UTC) time<br>standard.<br>If you have specified a value for the<br>` modifiedDateTo ` parameter, ensure that you<br>also enter a value for the<br>` modifiedDateFrom` parameter.|
|`status`|Optional. Specify the status of the<br>data asset. The status indicates<br>whether the data asset is<br>available to be added to data<br>collections.|Enter one of the following values:<br>- To find the data assets that are available,<br>enter` ENABLED`.<br>- To find the data assets that aren't<br>available, enter` DISABLED`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortByField `|Optional. Specify the parameters<br>to sort the search results.|To sort the search results, enter one of the<br>following values:<br>-<br>` ID `<br>-<br>` NAME `<br>-<br>` SOURCE `<br>-<br>` DESCRIPTIVE_SOURCE `<br>-<br>` TYPE `<br>-<br>` ASSET_LOCATION `<br>-<br>` ASSET_LOCATION_DESCRIPTION `<br>-<br>` TECHNICAL_ASSET_NAME `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of<br>the search results.|Enter one of the following values:<br>- To sort the search results by ascending<br>order, enter` ASC`.<br>- To sort the search results by descending<br>order, enter` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting index<br>for the paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum<br>number of results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of a data asset: 

```
https://{{CDMP_URL}}/api/v1/integration/dataAssets?
search=Accounts_Customers2&status=ENABLED
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a data asset: 

```
{
    "processingTime": 212,
    "offset": 0,
    "limit": 50,
    "totalCount": 1,
    "objects": [
        {
            "name": "Accounts_Customers2",
            "id": "35d4856a-90b3-3584-abd6-47db0fe9acb7",
            "refId": "DAS-891",
            "description": "Customer Information",
```


```
            "source": "CDLG",
            "descriptiveSource": "AXON",
            "type": "Table",
            "refLink": "URL://AXON",
            "assetLocation": "string",
            "assetLocationDescription": "string",
            "technicalAssetName": "string",
            "status": "DISABLED",
            "resourceReference": {
              "typeReference": "string",
              "sourceAssetId": "string"
            },
            "createdBy": "7w2uGIARApUgxniNoHHWsJ",
            "createdOn": "2022-03-08T17:05:28.208Z",
            "modifiedBy": "7w2uGIARApUgxniNoHHWsJ",
            "modifiedOn": "2022-03-08T17:09:34.623Z"
        }
    ]
}
```

The following table describes the parameters of each data asset that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of data assets retrieved.|
|`name`|Name of the data asset.|
|`id`|System generated unique identifier of the data asset.|
|`refId`|Reference identifier of the data asset.|
|`description`|Description of the data asset.|
|`source`|Source system from which the data is supplied to Data Marketplace.|
|`descriptiveSource`|Source application from which the description of the data asset is taken.|
|`type`|Type of the data asset.|
|`refLink`|Uniform resource identifier of the location in the data source where the data<br>asset is stored.|
|`assetLocation`|Location of the data asset in the data source.|
|`assetLocationDescription`|Description of the location in the data source where the data asset is stored.|
|`technicalAssetName`|Name of the data asset as it appears in the data source.|
|`status `|The status indicates whether the data asset is available to be added to data<br>collections. A data asset can have one of the following statuses:<br>-<br>` ENABLED `. The data asset is available.<br>-<br>` DISABLED`. The data asset isn't available.|
|`resourceReference`|Details of the data asset that was imported from Data Governance and<br>Catalog|

|**Parameter**|**Description**|
|---|---|
|`resourceReference >`<br>`typeReference`|Type of the asset as defined in Data Governance and Catalog.|
|`resourceReference >`<br>`sourceAssetId`|System generated unique identifier of the associated Data Governance and<br>Catalog asset.|
|`createdBy`|System generated unique identifier of the user that created the data asset.|
|`createdOn`|Date when the data asset was created.|
|`modifiedBy`|System generated unique identifier of the latest user account that modified<br>the data asset.|
|`modifiedOn`|Latest date when the data asset was modified.|


#### Modify data assets
Use a REST API to modify data assets in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataAssets**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system<br>generated unique identifier of<br>the data asset that you want to<br>modify.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a data asset, see<br>“Retrieve data assets” on page 203.<br>To get the system generated unique identifier of<br>a data asset from the Data Marketplace<br>interface, open the data asset. The data asset<br>page's URL contains the system generated<br>unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/dataAsset/`<br>`8c4c2089-6c7e-4696-a8f6-44f87639d65c?`<br>`dtn=Table_Profiling_AK_1676302415587&`<br>`tab=dataElements`, the system generated<br>unique identifier is` 8c4c2089-6c7e-4696-a8f6-44f87639d65c`.<br>**Note:**You can use this API to modify only the<br>data assets that are native to Data Marketplace.<br>To modify a data asset that was imported from<br>Data Governance and Catalog, see the Data<br>Governance and Catalog help.|
|`name`|Required. Enter a name for the<br>data asset.|-|
|`description`|Required. Enter a description<br>for the data asset.|-|
|`source`|Required. Specify the source<br>system from which the data is<br>supplied to Data Marketplace.|-|
|`descriptiveSource`|Optional. Specify the source<br>application from which the<br>description of the data asset is<br>taken.|-|
|`type`|Required. Enter a type for the<br>data asset.|-|
|`refLink`|Optional. Enter the uniform<br>resource identifier of the<br>location in the data source<br>where the data asset is stored.|-|
|`assetLocation`|Optional. Enter the location of<br>the data asset in the data<br>source.|-|
|`assetLocationDescription`|Optional. Enter a description<br>for the location in the data<br>source where the data asset is<br>stored.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`technicalAssetName`|Optional. Enter the name of the<br>data asset as it appears in the<br>data source.|-|
|`status `|Optional. Specify a status for<br>the data asset. The status<br>indicates whether the data<br>asset is available to be added<br>to data collections.|Enter one of the following values:<br>- To make the data asset available, enter<br>` ENABLED `.<br>- To make the data asset unavailable, enter<br>` DISABLED`.<br>Default value is` ENABLED`.|


#### Example request
The following example shows how you can use an API to modify a data asset: 

```
{
    "items": [
        {
            "id": "35d4856a-90b3-3584-abd6-47db0fe9acb7",
            "name": "Accounts_Customers2",
            "description": "Updated Customer Information",
            "source": "CDLG",
            "descriptiveSource": "AXON",
            "type": "Table",
            "refLink": "URL://AXON",
            "assetLocation": "string",
            "assetLocationDescription": "string",
            "technicalAssetName": "string",
            "status": "DISABLED"
        }
    ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to modify a data asset: 

```
{
    "processingTime": 2130,
    "objects": [
        {
            "index": 1,
            "id": "35d4856a-90b3-3584-abd6-47db0fe9acb7",
            "refId": "DAS-891",
            "name": "Accounts_Customers2"
        }
    ]
}
```


The following table describes the parameters of each data asset that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the data asset in the` objects` JSON array. This value does not impact how the data<br>asset is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the data asset.|
|`refId`|Reference identifier of the data asset.|
|`name`|Name of the data asset.|


#### Delete data assets
Use a REST API to delete data assets in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataAssets/<id>**|
||`<id>`: Required. Enter the system generated unique identifier of the data asset that you want to delete.<br>For more information about how you can use an API to get the system generated unique identifier of a<br>data asset, see<br>“Retrieve data assets” on page 203.|
||To get the system generated unique identifier of a data asset from the Data Marketplace interface, open<br>the data asset. The data asset page's URL contains the system generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/dataAsset/8c4c2089-6c7e-4696-a8f6-44f87639d65c?dtn=Table_Profiling_AK_1676302415587&tab=dataElements`, the system<br>generated unique identifier is` 8c4c2089-6c7e-4696-a8f6-44f87639d65c`.|
|Method|DELETE|


**Note:** You can use this API to delete only the data assets that are native to Data Marketplace. To delete a data asset that was imported from Data Governance and Catalog, see the Data Governance and Catalog help. 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 

#### Example request
The following example shows how you can use an API to delete a data asset: 

```
https://{{CDMP_URL}}/api/v1/integration/dataAssets/d2313aed-330e-44a0-bcfa-380ee4a95a62
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data asset is deleted successfully: 

```
204 OK code
```


## Chapter 8: Manage data collections
A data collection is a grouping of one or more data assets. If a Data User requires access to a data collection, the user can place an order for the data collection. 

### Create data collections
Use a REST API to create data collections in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataCollections**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`name`|Required. Enter a name for the<br>data collection.|-|
|`description`|Required. Enter a description<br>for the data collection.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`refId`|Optional. Enter a reference<br>identifier for the data<br>collection.|If you don't specify a reference identifier, Data<br>Marketplace automatically assigns a unique<br>value to the object. The reference identifier that<br>Data Marketplace automatically generates<br>contains a prefix. The Administrator can specify<br>the prefix of the automatically generated<br>reference identifier in Metadata Command<br>Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure that<br>you don't use the prefix value that is configured<br>in Metadata Command Center.|
|`categoryId`|Required. Enter the system<br>generated unique identifier of<br>the category in which you want<br>to create the data collection.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a category, see<br>“Retrieve categories” on page 124.<br>To get the system generated unique identifier of<br>a category from the Data Marketplace interface,<br>open the category. The category page's URL<br>contains the system generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-a1e412c1ce13&dtn=_AfterEBF%20may20 `, the<br>system generated unique identifier is<br>` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|`usageContextIds`|Optional. Enter the system<br>generated unique identifier of<br>the usage type that you want to<br>use to specify the certified use<br>of the data collection.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a usage type, see<br>“Retrieve usage type” on page 174.|
|`dataCollectionStatus`|Required. Specify a status for<br>the data collection. The status<br>determines whether or not the<br>data collection is available to<br>Data Users.|Enter one of the following values:<br>- To make the data collection discoverable to<br>Data Users, enter` PUBLISHED`.<br>- To make the data collection undiscoverable to<br>Data Users, enter` UNPUBLISHED`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`dataOwners`|Optional. Enter the system<br>generated unique identifier of<br>the user account or user group<br>that is assigned as a<br>stakeholder on the data<br>collection with the Data Owner<br>stakeholder role.|- To get the system generated unique identifier<br>of a user account, navigate to**My Services >**<br>**Administrator > Users**. On the**Users**page,<br>click a user account. The user account page's<br>URL contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `,<br>the system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system generated unique identifier<br>of a user group, navigate to**My Services >**<br>**Administrator > User Groups**. On the**User**<br>**Groups**page, click a user group. The user<br>group page's URL contains the system<br>generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`userGroupsAsset/90uizkSWg0ycuu7hSNXSW4 `, the system<br>generated unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|
|`technicalOwners`|Optional. Enter the system<br>generated unique identifier of<br>the user account or user group<br>that is assigned as a<br>stakeholder on the data<br>collection with the Technical<br>Owner stakeholder role.|- To get the system generated unique identifier<br>of a user account, navigate to**My Services >**<br>**Administrator > Users**. On the**Users**page,<br>click a user account. The user account page's<br>URL contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `,<br>the system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system generated unique identifier<br>of a user group, navigate to**My Services >**<br>**Administrator > User Groups**. On the**User**<br>**Groups**page, click a user group. The user<br>group page's URL contains the system<br>generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`userGroupsAsset/90uizkSWg0ycuu7hSNXSW4 `, the system<br>generated unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`customFields`|The header where you can<br>modify the custom attributes<br>of the data collection.|Whether you must enter a value or not enter a<br>value in a custom attribute is determined by how<br>the custom attribute was defined by your<br>administrator in Metadata Command Center.<br>Custom attributes are additional properties for<br>Data Marketplace items that are defined by your<br>administrator in Metadata Command Center. For<br>more information about custom attributes, see<br>the_Create custom attributes for items_topic in<br>the_Set Up Data Marketplace_help.|
|`customFields > name`|Enter the system generated<br>unique identifier of the custom<br>attribute that you want to<br>modify.|For more information about how you can retrieve<br>the system generated unique identifier of a<br>custom attribute, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|Enter a value in the custom<br>attribute.|This parameter can have only a single value.|
|`customFields > values`|Enter a value in the custom<br>attribute.|This parameter can have multiple values.|


#### Example request
The following examples show how you can use APIs to create data collections: 

#### **Example request 1**
```
{
  "items": [
    {
      "name": "Retail Manager List",
      "description": "Details about the managers of all retail outlets globally.",
      "refId": "DC9912",
      "categoryId": "49d4856a-90b3-3584-abd6-47db0fe9acb6",
      "usageContextIds": [
        "54ab6f78-264a-4892-bff6-b999b2284200"
      ],
      "dataCollectionStatus": "PUBLISHED",
      "dataOwners": [
        "56y4856a-34t3-5678-abd6-47db0fe9er3c"
      ],
      "technicalOwners": [
        "42y4853e-16y1-9087-ert2-32ef0fe4gh7c"
      ],
      "customFields": [
         {
         "name":"com.infa.odin.models.custom.ca_3412802606475555040",
         "value":"false"
         }
       ]
     }
  ]
}
```

#### **Example request 2**
```
{
  "items": [
    {
```


```
      "name": "08/09 J-SOX Reports",
      "description": "The collection of J-SOX reports from 2008-09.",
      "refId": "78901",
      "categoryId": "5e056b9b-d2d1-4b33-a38b-3d8255cde7c0",
      "dataCollectionStatus": "PUBLISHED",
      "customFields": [
                {
                    "name": "com.infa.odin.models.custom.ca_7821688086685433570",
                    "value": true
                },
                {
                    "name": "com.infa.odin.models.custom.ca_3184028726397751640",
                    "value": "2023-09-05"
                },
                {
                    "name": "com.infa.odin.models.custom.ca_6928840635020975219",
                    "value": 45.6
                },
                {
                    "name": "com.infa.odin.models.custom.ca_7907323524500803261",
                    "value": "Secure Data Transfer"
                },
                {
                    "name": "com.infa.odin.models.custom.ca_1535512762047882544",
                    "value": 10
                },
                {
                    "name": "com.infa.odin.models.custom.ca_8610145587620226269",
                    "value": "Reticulum"
                },
                {
                    "name": "com.infa.odin.models.custom.ca_5588843841075376340",
                    "value": "LXMF"
                },
                {
                    "name": "com.infa.odin.models.custom.ca_1007408542741800522",
                    "value": true
                },
                {
                    "name": "com.infa.odin.models.custom.ca_3068304325528979652",
                    "values": [
                        "Apache CloudStack",
                        "Microsoft Azure",
                        "Amazong Web Services"
                    ]
                }
            ]
        }
    ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following examples show the response of API calls to create data collections: 

**Example response 1** 

```
{
  "processingTime": 10105,
  "objects": [
      {
         "index": 1,
         "id": "1a9e9fa2-f864-4821-b160-18006453b525",
         "refId": "DC9912",

         "name": "Retail Manager List"
      }
   ],
  "errors": null
}
```

**Example response 2** 

```
{
  "processingTime": 12243,
  "objects": [
      {
        "index": 0,
        "id": "0537c392-a359-4cdc-8989-1d88eee1dddb",
        "refId": "78901",
        "name": "08/09 J-SOX Reports"
      }
   ],
  "errors": null
}
```

The following table describes the parameters of each data collection that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the data collection in the` objects` JSON array. This value does not impact how the data<br>collection is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the data collection.|
|`refId`|Reference identifier of the data collection.|
|`name`|Name of the data collection.|


### Retrieve data collections
Use REST APIs to retrieve the details of data collections in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataCollections**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`search`|Optional. Enter the search term that<br>you want to use to find a data<br>collection.|Ensure that the search term that you<br>enter don't contain an asterisk (*).|
|`fields`|Optional. Enter the fields on which the<br>search term applies. The terms that<br>you entered in the` search ` field<br>parameter are used to search the fields<br>that you specify here.|Enter the following values:<br>-<br>` NAME `<br>-<br>` DESCRIPTION`|
|`ids`|Optional. Enter the system generated<br>unique identifier of a data collection.|To get the system generated unique<br>identifier of a data collection from the<br>Data Marketplace interface, open the<br>data collection. The data collection<br>page's URL contains the system<br>generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary `,<br>the system generated unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1 `.<br>To enter more than one value, use the<br>following format:<br>` ids=<value1>&ids=<value2>`|
|`dataOwners`|Optional. Enter the system generated<br>unique identifier of the user account or<br>user group that is assigned as a<br>stakeholder on the data collection with<br>the Data Owner stakeholder role.|- To get the system generated unique<br>identifier of a user account, navigate<br>to**My Services > Administrator >**<br>**Users**. On the**Users**page, click a user<br>account. The user account page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `, the<br>system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system generated unique<br>identifier of a user group, navigate to<br>**My Services > Administrator > User**<br>**Groups**. On the**User Groups**page,<br>click a user group. The user group<br>page's URL contains the system<br>generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`userGroupsAsset/90uizkSWg0ycuu7hSNXSW4 `, the<br>system generated unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`technicalOwners`|Optional. Enter the system generated<br>unique identifier of the user account or<br>user group that is assigned as a<br>stakeholder on the data collection with<br>the Technical Owner stakeholder role.|- To get the system generated unique<br>identifier of a user account, navigate<br>to**My Services > Administrator >**<br>**Users**. On the**Users**page, click a user<br>account. The user account page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `, the<br>system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system generated unique<br>identifier of a user group, navigate to<br>**My Services > Administrator > User**<br>**Groups**. On the**User Groups**page,<br>click a user group. The user group<br>page's URL contains the system<br>generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`userGroupsAsset/90uizkSWg0ycuu7hSNXSW4 `, the<br>system generated unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|
|`categoryIds`|Optional. Enter the system generated<br>unique identifier of the category that<br>contains the data collection.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a category, see<br>“Retrieve categories” on page 124.<br>To get the system generated unique<br>identifier of a category from the Data<br>Marketplace interface, open the<br>category. The category page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-a1e412c1ce13&dtn=_AfterEBF`<br>`%20may20`, the system generated unique<br>identifier is` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|`usageContextIds`|Optional. Enter the system generated<br>unique identifier of the usage type that<br>is used to specify the certified use of<br>the data collection.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a usage type, see<br>“Retrieve usage type” on page 174.|
|`deliveryTemplateIds`|Optional. Enter the system generated<br>unique identifier of the delivery<br>template that was used to create the<br>delivery target of the data collection.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a delivery template,<br>see<br>“Retrieve delivery templates” on page 152.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`deliveryTargetLocations`|Optional. Specify the delivery target<br>location.|-|
|`createdDateFrom`|Optional. To find data collections that<br>were created between a date range,<br>enter the initial date when the data<br>collections were created.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` createdDateFrom ` parameter, ensure<br>that you also enter a value for the<br>` createdDateTo` parameter.|
|`createdDateTo`|Optional. To find data collections that<br>were created between a date range,<br>enter the latest date when the data<br>collections were created.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` createdDateTo ` parameter, ensure that<br>you also enter a value for the<br>` createdDateFrom` parameter.|
|`modifiedDateFrom`|Optional. To find data collections that<br>were modified between a date range,<br>enter the initial date when the data<br>collections were modified.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` modifiedDateFrom ` parameter, ensure<br>that you also enter a value for the<br>` modifiedDateTo` parameter.|
|`modifiedDateTo`|Optional. To find data collections that<br>were modified between a date range,<br>enter the latest date when the data<br>collections were modified.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` modifiedDateTo ` parameter, ensure<br>that you also enter a value for the<br>` modifiedDateFrom` parameter.|
|`status `|Optional. Specify the status of the data<br>collection. The status indicates<br>whether or not the data collection is<br>available to Data Users.|Enter one of the following values:<br>- To find the data collections that are<br>discoverable to Data Users, enter<br>` PUBLISHED `.<br>- To find the data collections that aren't<br>discoverable to Data Users, enter<br>` UNPUBLISHED`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortByField `|Optional. Specify the parameters to<br>sort the search results.|To sort the search results, enter one of<br>the following values:<br>-<br>` ID `<br>-<br>` NAME `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the<br>search results.|You can sort the search results in the<br>following order:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting index for<br>the paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum number<br>of results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following examples show how you can use APIs to retrieve the details of data collections: **Example request 1** 

```
https://{{CDMP_URL}}/api/v1/integration/dataCollections?ids=675c2d4d-
f0a8-4526-9aeb-304c86488296&usageContextIds=IdString2&sortByField=MODIFIED_ON&sort=DE
SC&limit=100
```

#### **Example request 2**
```
https://{{CDMP_URL}}/api/v1/integration/dataCollections?ids=0537c392-
a359-4cdc-8989-1d88eee1dddb
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following examples show the response of API calls to retrieve the details of data collections: 

#### **Example response 1**
```
{
  "processingTime": 1392,
  "offset": 0,
  "limit": 100,
  "totalCount": 856,
  "objects": [
```


`{ "id": "b90626e0-c9ae-44ed-b9ed-67178e92e886", "refId": "DCL-945", "name": "Retail Outlet List", "description": "List of all retail outlets.", "category": { "id": "8a17c0c2-dc9b-4904-8d28-535c27f23489", "name": "Retail", "description": "The category for the Retail department.", "refId": "CAT-744", "status": "ACTIVE", "effectiveStatus": "ACTIVE" }, "usageContexts": [ { "id": "675c2d4d-f0a8-4526-9aeb-304c86488296", "name": "Retail Analytics", "description": "This is for analytics purposes by the retail department.", "color": "string", "status": "ACTIVE" } ], "status": "PUBLISHED", "dataOwners": [ { "displayName": "Jane Doe", "id": " User1", "name": "username1", "email": "abc@xyz.com", "phone": "2020202020", "status": "ACTIVE", "userInfo": {}, "isGroup": true } ], "technicalOwners": [ { "displayName": "John Doe", "id": "User2", "name": "username2", "email": "xyz@abc.com", "phone": "2020202021", "status": "ACTIVE", "userInfo": {}, "isGroup": true } ], "customFields": [ { "name":"com.infa.odin.models.custom.ca_1007408542741800522", "value":"false", "values":null } ], "createdBy": "lWDKqZAREESgsDTtk9kVOy", "createdOn": "2022-06-21T10:43:03.374Z", "modifiedBy": "lWDKqZAREESgsDTtk9kVOy", "modifiedOn": "2022-07-07T13:26:23.180Z" } ] }` **Example response 2** `{ "processingTime": 7788, "offset": 0, "limit": 50, "totalCount": 1,`

```
  "objects": [
       {
          "id": "0537c392-a359-4cdc-8989-1d88eee1dddb",
          "refId": "78901",
          "name": "08/09 J-SOX Reports",
          "description": "The collection of J-SOX reports from 2008-09.",
          "category":
               {
                  "id": "5e056b9b-d2d1-4b33-a38b-3d8255cde7c0",
                  "name": "cat05",
                  "description": "desc",
                  "refId": "16",
                  "status": "ACTIVE",
                  "effectiveStatus": "ACTIVE"
               },
          "usageContexts": null,
          "status": "PUBLISHED",
          "dataOwners": [],
          "technicalOwners": [],
          "customFields": [
               {
                  "name": com.infa.odin.models.custom.ca_3184028726397751640",
                  "value": "2023-09-05",
                  "values": null
               },
               {
                  "name": "com.infa.odin.models.custom.ca_3068304325528979652",
                  "value": null,
                  "values": [
                       {
                          "Apache CloduStack",
                          "Microsoft Azure"                                ]
                       },
                       {
                          "name":
"com.infa.odin.models.custom.ca_8610145587620226269",
                          "value": "Reticulum",
                          "values": null
                       },
                       {
                          "name":
"com.infa.odin.models.custom.ca_6928840635020975219",
                          "value": "45.6",
                          "values": null
                       },
                       {
                          "name":
"com.infa.odin.models.custom.ca_5588843841075376340",
                          "value": "LXMF",
                          "values": null
                       },
                       {
                          "name":
"com.infa.odin.models.custom.ca_7907323524500803261",
                          "value": "Secure Data Transfer",
                          "values": null
                       },
                       {
                          "name":
"com.infa.odin.models.custom.ca_1535512762047882544",
                          "value": "10",
                          "values": null
                       },
                       {
                          "name":
"com.infa.odin.models.custom.ca_1007408542741800522",
                          "value": "false",
                          "values": null
                       },
                       {
```


```
                          "name":
"com.infa.odin.models.custom.ca_7821688086685433570",
                          "value": "false",
                          "values": null
                       }
                  ],
          "createdBy": "8lgIca73QzfdX7xzhZnOQa",
          "createdOn": "2023-09-11T05: 40: 34.460Z",
          "modifiedBy": "8lgIca73QzfdX7xzhZnOQa",
          "modifiedOn": "2023-09-11T05: 40: 34.460Z"
        }
    ]
}
```

The following table describes the parameters of each data collection that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`processingTime`|Time taken (in milliseconds) to complete the API call.|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of data collections retrieved.|
|`objects`|Details of the retrieved data collection.|
|`id`|System generated unique identifier of the data collection.|
|`refId`|Reference identifier of the data collection.|
|`name`|Name of the data collection.|
|`description`|Description of the data collection.|
|`category`|Details of the category that contains the data collection.|
|`category > id`|System generated unique identifier of the category that contains the category.|
|`category > name`|Name of the category that contains the category.|
|`category >`<br>`description`|Description of the category that contains the category.|
|`category > status `|Status of the category. A category can have one of the following statuses:<br>-<br>` ACTIVE `<br>-<br>` INACTIVE`|
|`category >`<br>`effectiveStatus `|The effective status indicates whether the category is available in Data Marketplace. A<br>category can have one of the following statuses:<br>-<br>` ACTIVE `. The category is available.<br>-<br>` INACTIVE`. The category is unavailable.|
|`usageContexts > id`|System generated unique identifier of the usage type that is used to specify the<br>certified use of the data collection.|
|`usageContexts > name`|Name of the usage type that is used to specify the certified use of the data collection.|

|**Parameter**|**Description**|
|---|---|
|`usageContexts >`<br>`description`|Description of the usage type that is used to specify the certified use of the data<br>collection.|
|`usageContexts > color`|A hexadecimal value that represents the color indicator of the usage type. Assigning a<br>unique color indicator allows Data Marketplace users to identify a usage type easily.|
|`usageContexts >`<br>`status `|The status indicates whether a usage type is available to be added to a data collection<br>or to be selectable on an order. A usage type can have one of the following values:<br>-<br>` ACTIVE `. The usage type is available.<br>-<br>` INACTIVE`. The usage type is available.|
|`status `|The status indicates whether the data collection is discoverable by Data Users when<br>they search for it. A data collection can have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to Data Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable to Data Users.|
|`dataOwners`|Details of the stakeholder that is assigned the Data Owner stakeholder role on the data<br>collection.|
|`dataOwners >`<br>`displayName`|Name of the stakeholder that is assigned the Data Owner stakeholder role on the data<br>collection.|
|`dataOwners > id`|System generated unique identifier of the user account or user group that is assigned<br>as a stakeholder on the data collection with the Data Owner stakeholder role.|
|`dataOwners > name`|Username of the stakeholder that is assigned the Data Owner stakeholder role on the<br>data collection.|
|`dataOwners > email`|Email address of the stakeholder that is assigned the Data Owner stakeholder role on<br>the data collection.|
|`dataOwners > phone`|Contact number of the stakeholder that is assigned the Data Owner stakeholder role on<br>the data collection.|
|`dataOwners > status `|The status indicates whether or not the Data Owner stakeholder user account or user<br>group is active. A user account or user group can have one of the following statuses:<br>-<br>` ACTIVE `. The Data Owner user account or user group is active.<br>-<br>` INACTIVE`. The Data Owner user account or user group is not active.|
|`dataOwners > userInfo`|Details retrieved from the**My Data**page of the stakeholder that is assigned the Data<br>Owner stakeholder role on the data collection.|
|`dataOwners > isGroup `|Parameter that indicates whether the user account is part of a group. This parameter<br>can have one of the following values:<br>-<br>` true `. The user account is part of a group<br>-<br>` false`. The user account isn't part of a group|
|`technicalOwners`|Details of the stakeholder that is assigned the Technical Owner stakeholder role on the<br>data collection.|
|`technicalOwners >`<br>`displayName`|Name of the stakeholder that is assigned the Technical Owner stakeholder role on the<br>data collection.|
|`technicalOwners > id`|System generated unique identifier of the user account or user group that is assigned<br>as a stakeholder on the data collection with the Technical Owner stakeholder role.|


|**Parameter**|**Description**|
|---|---|
|`technicalOwners >`<br>`name`|Username of the stakeholder that is assigned the Technical Owner stakeholder role on<br>the data collection.|
|`technicalOwners >`<br>`email`|Email address of the stakeholder that is assigned the Technical Owner stakeholder role<br>on the data collection.|
|`technicalOwners >`<br>`phone`|Contact number of the stakeholder that is assigned the Technical Owner stakeholder<br>role on the data collection.|
|`technicalOwners >`<br>`status `|The status indicates whether or not the Technical Owner stakeholder user account or<br>user group is active. A user account or user group can have one of the following<br>statuses:<br>-<br>` ACTIVE `. The Technical Owner user account or user group is active.<br>-<br>` INACTIVE`. The Technical Owner user account or user group is not active.|
|`technicalOwners >`<br>`userInfo`|Details retrieved from the**My Data**page of the stakeholder that is assigned the<br>Technical Owner stakeholder role on the data collection.|
|`technicalOwners >`<br>`isGroup `|Parameter that indicates whether the user account is part of a group. This parameter<br>can have one of the following values:<br>-<br>` true `. The user account is part of a group<br>-<br>` false`. The user account isn't part of a group|
|`customFields`|The custom fields of the data collection and their values.<br>Custom attributes are additional properties for Data Marketplace items that are defined<br>by your administrator in Metadata Command Center.<br>For more information about custom attributes, see the_Create custom attributes for_<br>_items_topic in the_Set Up Data Marketplace_help.|
|`customFields > name`|The system generated unique identifier of the custom attribute.<br>For more information about how you can retrieve the custom attribute name as it<br>appears on the Data Marketplace interface, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value. This parameter can have only a single value.|
|`customFields > values`|The custom attribute value. This parameter can have multiple values.|
|`createdBy`|System generated unique identifier of the user account that created the data collection.|
|`createdOn`|Date when the data collection was created.|
|`modifiedBy`|System generated unique identifier of the latest user account that modified the data<br>collection.|
|`modifiedOn`|Latest date when the data collection was modified.|


### Modify data collections
Use a REST API to modify data collections in Data Marketplace.

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataCollections**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system<br>generated unique identifier of<br>the data collection that you<br>want to modify.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a data collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique identifier of<br>a data collection from the Data Marketplace<br>interface, open the data collection. The data<br>collection page's URL contains the system<br>generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?`<br>`dtn=&tab=summary `, the system generated<br>unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`name`|Required. Enter a name for the<br>data collection.|-|
|`description`|Required. Enter a description<br>for the data collection.|-|
|`refId`|Optional. Enter a reference<br>identifier for the data<br>collection.|If you don't specify a reference identifier, Data<br>Marketplace automatically assigns a unique<br>value to the object. The reference identifier that<br>Data Marketplace automatically generates<br>contains a prefix. The Administrator can specify<br>the prefix of the automatically generated<br>reference identifier in Metadata Command<br>Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure that<br>you don't use the prefix value that is configured<br>in Metadata Command Center.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`categoryId`|Required. Enter the system<br>generated unique identifier of<br>the category to which you want<br>to add the data collection.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a category, see<br>“Retrieve categories” on page 124.<br>To get the system generated unique identifier of<br>a category from the Data Marketplace interface,<br>open the category. The category page's URL<br>contains the system generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-a1e412c1ce13&dtn=_AfterEBF%20may20 `, the<br>system generated unique identifier is<br>` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|`usageContextIds`|Optional. Enter the system<br>generated unique identifier of<br>the usage type that you want to<br>use to specify the certified use<br>of the data collection.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a usage type, see<br>“Retrieve usage type” on page 174.|
|`dataCollectionStatus`|Required. Specify a status for<br>the data collection. The status<br>determines whether or not the<br>data collection is available to<br>Data Users.|Enter one of the following values:<br>- To make the data collection discoverable to<br>Data Users, enter` PUBLISHED`.<br>- To make the data collection undiscoverable to<br>Data Users, enter` UNPUBLISHED`.|
|`dataOwners`|Optional. Enter the system<br>generated unique identifier of<br>the user account or user group<br>that is assigned as a<br>stakeholder on the data<br>collection with the Data Owner<br>stakeholder role.|- To get the system generated unique identifier<br>of a user account, navigate to**My Services >**<br>**Administrator > Users**. On the**Users**page,<br>click a user account. The user account page's<br>URL contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `,<br>the system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system generated unique identifier<br>of a user group, navigate to**My Services >**<br>**Administrator > User Groups**. On the**User**<br>**Groups**page, click a user group. The user<br>group page's URL contains the system<br>generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`userGroupsAsset/90uizkSWg0ycuu7hSNXSW4 `, the system<br>generated unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`technicalOwners`|Optional. Enter the system<br>generated unique identifier of<br>the user account or user group<br>that is assigned as a<br>stakeholder on the data<br>collection with the Technical<br>Owner stakeholder role.|- To get the system generated unique identifier<br>of a user account, navigate to**My Services >**<br>**Administrator > Users**. On the**Users**page,<br>click a user account. The user account page's<br>URL contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `,<br>the system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.<br>- To get the system generated unique identifier<br>of a user group, navigate to**My Services >**<br>**Administrator > User Groups**. On the**User**<br>**Groups**page, click a user group. The user<br>group page's URL contains the system<br>generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`userGroupsAsset/90uizkSWg0ycuu7hSNXSW4 `, the system<br>generated unique identifier is<br>` 90uizkSWg0ycuu7hSNXSW4`.|
|`customFields`|The header where you can<br>modify the custom attributes<br>of the data collection.|Whether you must enter a value or not enter a<br>value in a custom attribute is determined by how<br>the custom attribute was defined by your<br>administrator in Metadata Command Center.<br>Custom attributes are additional properties for<br>Data Marketplace items that are defined by your<br>Administrator in Metadata Command Center. For<br>more information about custom attributes, see<br>the_Create custom attributes for items_topic in<br>the_Set Up Data Marketplace_help.|
|`customFields > name`|Enter the system generated<br>unique identifier of the custom<br>attribute that you want to<br>modify.|For more information about how you can retrieve<br>the system generated unique identifier of a<br>custom attribute, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|Enter a value in the custom<br>attribute.|This parameter can have only a single value.|
|`customFields > values`|Enter a value in the custom<br>attribute.|This parameter can have multiple values.|


#### Example request
The following example shows how you can use an API to modify a data collection: 

```
{
  "items": [
    {
      "id": "15100e3a-0c89-42c7-afc1-9ed0f13766a4",
      "name":"07/08 J-SOX Reports",
```


```
      "description": "The collection of J-SOX reports from 2007-08.",
      "refId": "123467",
      "categoryId": "2d0a40a4-a511-4d64-915b-d700d6441efe",
      "usageContextIds": [
        "c607b766-1e04-4892-a105-3ef2826461c9"
      ],
      "dataCollectionStatus": "PUBLISHED",
      "dataOwners": [
        "48WAxPi970Jkn9p2PCoJCb"
      ],
      "technicalOwners": [
        "6WExNvGf1Mpj9BITKmPMeg"
      ],
      "customFields": [
                {
                    "name": "com.infa.odin.models.custom.ca_7821688086685433570",
                    "value": true
                },
                {
                    "name": "com.infa.odin.models.custom.ca_3184028726397751640",
                    "value": "2023-09-05"
                },
                {
                    "name": "com.infa.odin.models.custom.ca_6928840635020975219",
                    "value": 45.6
                },
                {
                    "name": "com.infa.odin.models.custom.ca_7907323524500803261",
                    "value": "Secure Data Transfer"
                },
                {
                    "name": "com.infa.odin.models.custom.ca_1535512762047882544",
                    "value": 10
                },
                {
                    "name": "com.infa.odin.models.custom.ca_8610145587620226269",
                    "value": "Reticulum"
                },
                {
                    "name": "com.infa.odin.models.custom.ca_5588843841075376340",
                    "value": "LXMF"
                },
                {
                    "name": "com.infa.odin.models.custom.ca_1007408542741800522",
                    "value": true
                },
                {
                    "name": "com.infa.odin.models.custom.ca_3068304325528979652",
                    "values": [
                        "Apache CloduStack",
                        "Microsoft Azure",
                        "Amazong Web Services"
                    ]
                }
            ]
        }
    ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to modify a data collection:

```
{
  "processingTime": 30149,
  "objects":
        [
            {
              "index": 0,
              "id": "15100e3a-0c89-42c7-afc1-9ed0f13766a4",
              "refId": "123467",
              "name": "07/08 J-SOX Reports"
            }
         ],
  "errors": null
}
```

The following table describes the parameters of each data collection that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the data collection in the` objects` JSON array. This value does not impact how the data<br>collection is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the data collection.|
|`refId`|Reference identifier of the data collection.|
|`name`|Name of the data collection.|


### Delete data collections
Use a REST API to delete data collections in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataCollections/<id>**|
||`<id>`: Required. Enter the system generated unique identifier of the data collection that you want to<br>delete.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>data collection, see<br>“Retrieve data collections” on page 217.|
||To get the system generated unique identifier of a data collection from the Data Marketplace interface,<br>open the data collection. The data collection page's URL contains the system generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary `, the system generated unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|Method|DELETE|


**Note:** If the data collection that you want to delete is linked to another collection, this link is removed when you delete the data collection. In this scenario, the other collection that was linked does not get deleted. 


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 

#### Example request
The following example shows how you can use an API to delete a data collection: 

```
https://{{CDMP_URL}}/api/v1/integration/dataCollections/d2313aed-330e-44a0-
bcfa-380ee4a95a62
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data collection is deleted successfully: 

```
204 OK code
```

### Add or remove data assets from data collections
Use a REST API to add a data asset to a data collection or to remove a data asset from a data collection. 

Before you make the API call, ensure that the data collection already exists in Data Marketplace, and the data asset already exist in either Data Marketplace or Data Governance and Catalog. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataCollections/dataAssets**|
|Method|PUT|

#### **Note:**
- The number of data assets that you can add per API call depends on the cloud provider the IDMC POD is hosted on. The following table shows the number of data assets that you can add per API call on each cloud provider: 

|**Cloud provider**|**Number of data assets**|
|---|---|
|Amazon Web Services|30|
|Microsoft Azure|10|


- You can call a maximum of 100 APIs per minute. 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`dataCollectionId`|Required. Enter the system<br>generated unique identifier of<br>the data collection to which<br>you want to add a data asset or<br>from which you want to remove<br>a data asset.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a data collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique identifier of<br>a data collection from the Data Marketplace<br>interface, open the data collection. The data<br>collection page's URL contains the system<br>generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?`<br>`dtn=&tab=summary `, the system generated<br>unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`assetId`|Required. Enter the system<br>generated unique identifier of<br>the data asset that you want to<br>add to a data collection or<br>remove from a data collection.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a Data Marketplace data asset, see<br>“Retrieve data assets” on page 203.<br>To get the system generated unique identifier of<br>a data asset from the Data Marketplace<br>interface, open the data asset. The data asset<br>page's URL contains the system generated<br>unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/dataAsset/`<br>`8c4c2089-6c7e-4696-a8f6-44f87639d65c?`<br>`dtn=Table_Profiling_AK_1676302415587&`<br>`tab=dataElements`, the system generated<br>unique identifier is` 8c4c2089-6c7e-4696-a8f6-44f87639d65c`.<br>For more information about how you can use an<br>API to get the system generated unique identifier<br>of a Data Governance and Catalog asset, see the<br>Data Governance and Catalog help.<br>When you add a Data Governance and Catalog<br>asset to a data collection, the**Overview**page of<br>the asset in Data Governance and Catalog<br>indicates that the asset is published to Data<br>Marketplace. Additionally, you can view in Data<br>Governance and Catalog the details of the data<br>collection to which the asset was added.|
|`operation `|Required. Specify the action<br>that you want to perform.|Enter one of the following values:<br>- To add a data asset to a data collection, enter<br>` ADD`.<br>- To remove a data asset from a data collection,<br>enter` REMOVE`.|

#### Example request
The following example shows how you can use an API to add a data asset to a data collection:. 

```
[
  {
    "dataCollectionId": "3c4c3349-6aac-4235-95e0-11b62a9dabcd",
    "dataAssets": [
      {
        "assetId": "7f4227e2-93ad-4881-adb4-2be5842def09",
        "operation": "ADD"
      }
    ]
  }
]
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the request query based on the parameters you had mapped with values. 

If the data asset is successfully added or removed, the following response is displayed: 

```
204 OK code
```

### Retrieve the data assets of a data collection
Use a REST API to retrieve the data assets of a data collection. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v1/integration/dataCollections/<dataCollectionId>/dataAssets** `<dataCollectionId>` : Required. Enter the system generated unique identifier of the data collection for which you want to retrieve the data assets. For more information about how you can use an API to get the system generated unique identifier of a data collection, see “Retrieve data collections” on page 217. To get the system generated unique identifier of a data collection from the Data Marketplace interface, open the data collection. The data collection page's URL contains the system generated unique identifier. For example, in the URL `https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3ecec1e171d0f1?dtn=&tab=summary ` , the system generated unique identifier is ` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1` . Method GET 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`offset`|Optional. Enter the starting<br>index for the paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum<br>number of results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API call to retrieve the data assets of a data collection: 

```
https://{{CDMP_URL}}/api/v1/integration/dataCollections/794a215f-5479-4b55-8e1b-
a4866ec9fe82/dataAssets?limit=100
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the request query based on the data collection that you specified. 

The following example shows the response of an API call to retrieve the data assets of a data collection: 

```
{
    "processingTime": 218,
    "offset": 0,
    "limit": 100,
    "totalCount": 231,
    "objects": [
        {
            "name": "Accounts_Customers",
            "id": "49d4856a-90b3-3584-abd6-47db0fe9acb6",
            "refId": "DAS-191",
            "description": "Customer Information",
            "source": "CDLG",
            "descriptiveSource": "AXON",
            "type": "Table",
            "refLink": "URL://AXON",
            "assetLocation": "string",
            "assetLocationDescription": "string",
            "technicalAssetName": "string",
            "status": "DISABLED",
            "resourceReference": {
               "typeReference": "string",
               "sourceAssetId": "9u2rTIHJHpUgvriNoUUYsE"
               },
            "createdBy": "7w2uGIARApUgxniNoHHWsJ",
            "createdOn": "2022-03-08T17:05:28.208Z",
            "modifiedBy": "7w2uGIARApUgxniNoHHWsJ",
            "modifiedOn": "2022-03-08T17:09:34.623Z"
        }
    ]
}
```

The following table describes the parameters of each data asset that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of data assets retrieved.|
|`name`|Name of the data asset.|
|`id`|System generated unique identifier of the data asset.|
|`refId`|Reference identifier of the data asset.|
|`description`|Description of the data asset.|
|`source`|Source system from which the data is supplied to Data Marketplace.|
|`descriptiveSource`|Source application from which the description of the data asset is taken.|
|`type`|Type of the data asset.|
|`refLink`|Uniform resource identifier of the location in the data source where the data<br>asset is stored.|
|`assetLocation`|Location of the data asset in the data source.|
|`assetLocationDescription`|Description of the location in the data source where the data asset is stored.|
|`technicalAssetName`|Name of the data asset as it appears in the data source.|
|`status `|The status indicates whether the data asset is available to be added to data<br>collections. A data asset can have one of the following statuses:<br>-<br>` ENABLED `. The data asset is available.<br>-<br>` DISABLED`. The data asset isn't available.|
|`resourceReference`|Details of the data asset that was imported from Data Governance and<br>Catalog|
|`resourceReference >`<br>`typeReference`|Type of the asset as defined in Data Governance and Catalog.|
|`resourceReference >`<br>`sourceAssetId`|System generated unique identifier of the associated Data Governance and<br>Catalog asset.|
|`createdBy`|System generated unique identifier of the user that created the data asset.|
|`createdOn`|Date when the data asset was created.|
|`modifiedBy`|System generated unique identifier of the latest user account that modified<br>the data asset.|
|`modifiedOn`|Latest date when the data asset was modified.|


### Manage the delivery targets of a data collection
A delivery target is a delivery option that you as a stakeholder of a data collection can use to deliver data to a Data User. Stakeholders use delivery templates to create new delivery targets for their data collections. 

#### Create delivery targets
Use a REST API to create delivery targets in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryTargets**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`refId`|Optional. Enter a reference<br>identifier for the delivery<br>target.|If you don't specify a reference identifier, Data<br>Marketplace automatically assigns a unique<br>value to the object. The reference identifier that<br>Data Marketplace automatically generates<br>contains a prefix. The Administrator can specify<br>the prefix of the automatically generated<br>reference identifier in Metadata Command<br>Center.<br>If you want to specify a reference identifier,<br>ensure that you enter a unique value. Ensure that<br>you don't use the prefix value that is configured<br>in Metadata Command Center.|
|`name`|Required. Enter a name for the<br>delivery target.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the letter<br>case when it verifies the uniqueness of the` name`<br>parameter's value. For example, if you try to<br>name a delivery target as "Excel over ipfs" while<br>a delivery target called "Excel over IPFS" already<br>exists, the API call fails.|
|`description`|Required. Enter a description<br>for the delivery target.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`status `|Required. Specify a status for<br>the delivery target. The status<br>determines whether the<br>delivery target is available for<br>use to the Data Users that<br>order the data collection.|Enter one of the following values:<br>- To make the delivery targets available, enter<br>` ACTIVE `.<br>- To make the delivery targets unavailable, enter<br>` INACTIVE`.|
|`targetSystemReference`|Optional. Enter the target<br>system or resource reference<br>from where the data is<br>obtained.|-|
|`physicalLocation `|Optional. Enter the URL of the<br>delivery location.|If the**Managed Access**option is enabled for the<br>deliver template that you specified in the<br>` deliveryTemplateId` parameter, the data is<br>not delivered to the location that you specify in<br>the` physicalLocation` parameter. Instead, the<br>data will be delivered to a location that is<br>generated at the time of order fulfillment. The<br>generated location is unique to each order that<br>is fulfilled using this target.<br>For more information, see the_Manage access to_<br>_data with Data Access Management_topic in the<br>_Set Up Data Marketplace_help.|
|`deliveryTemplateId`|Required. Enter the system<br>generated unique identifier of<br>the delivery template that you<br>want to use to create the<br>delivery target.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`deliveryMethodId`|Required. Enter the system<br>generated unique identifier of<br>the delivery method that<br>constitutes the delivery target.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a delivery method, see<br>“Retrieve delivery methods” on page 143.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`deliveryFormatId`|Required. Enter the system<br>generated unique identifier of<br>the delivery format that<br>constitutes the delivery target.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a delivery format, see<br>“Retrieve delivery formats” on page 136.|
|`dataCollectionId`|Required. Enter the system<br>generated unique identifier of<br>the data collection in which<br>you want to create the delivery<br>target.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a data collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique identifier of<br>a data collection, open the data collection. The<br>data collection page's URL contains the system<br>generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?`<br>`dtn=&tab=summary `, the system generated<br>unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1 `.<br>**Note:**If the**Managed Access**option is enabled<br>for the deliver template that you specified in the<br>` deliveryTemplateId ` parameter, ensure that<br>the data collection that you specify in the<br>` dataCollectionId` parameter is comprised<br>only of data assets that belong to the same data<br>source. For more information about how you can<br>add data assets to a data collection, see<br>“Add or remove data assets from data collections” on page 232.|


#### Example request
The following example shows how you can use an API to create a delivery target: 

```
{
    "items": [
        {
            "refId": "Target561",
            "name": "Customer DB",
            "description": "Customer DB Delivery",
            "status": "ACTIVE",
            "targetSystemReference": "AXON",
            "physicalLocation": "Oracle db",
            "deliveryTemplateId": "94df4d34-22f3-3f3e-9409-2b0354c87365",
            "deliveryMethodId": "c35181e7-7832-3b5c-b0d3-fc2360a82f05",
            "deliveryFormatId": "fe4e89ab-80e9-3e0d-8aba-71b79deab4b8",
            "dataCollectionId": "d2aef733-4886-38df-8eb3-6db8be9d0fb9"
        }
    ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered.

The following example shows the response of an API call to create a delivery target: 

```
{
    "processingTime": 4769,
    "objects": [
        {
            "index": 1,
            "id": "13c937c3-e6d8-3006-a530-f3cd6ecf2a8f",
            "refId": "Target561",
            "name": "Customer DB"
        }
    ]
}
```

The following table describes the parameters of each delivery target that is created: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the delivery target in the` objects` JSON array. This value does not impact how the<br>delivery target is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the delivery target.|
|`refId`|Reference identifier of the delivery target.|
|`name`|Name of the delivery target.|


#### Retrieve delivery targets
Use a REST API to retrieve the details of a delivery target in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryTargets**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`search`|Optional. Enter the search term that you<br>want to use to find a delivery target.|Ensure that the search term that you<br>enter don't contain an asterisk (*).|
|`fields`|Optional. Enter the fields on which the<br>search term applies. The terms that you<br>entered in the` search ` field parameter are<br>used to search the fields that you specify<br>here.|Enter the following values:<br>-<br>` NAME `<br>-<br>` DESCRIPTION`|
|`ids `|Optional. Enter the system generated<br>unique identifier of a delivery target.|To enter more than one value, use the<br>following format:<br>` ids=<value1>&ids=<value2>`|
|`dataCollectionIds`|Optional. Enter the system generated<br>unique identifier of the data collection that<br>is associated with the delivery target.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a data<br>collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique<br>identifier of a data collection, open<br>the data collection. The data<br>collection page's URL contains the<br>system generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/`<br>`datacollection/25158afc-3dfb-44ef-8f3e-`<br>`cec1e171d0f1?`<br>`dtn=&tab=summary `, the system<br>generated unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`deliveryTemplateIds`|Optional. Enter the system generated<br>unique identifier of a delivery template.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a<br>delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`status`|Optional. Specify the status of the delivery<br>target. The status indicates whether the<br>delivery target is available for use to the<br>Data Users that order the data collection.|Enter one of the following values:<br>- To find the delivery targets that are<br>available, enter` ACTIVE`.<br>- To find the delivery targets that<br>aren't available, enter` INACTIVE`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`isDefault `|Optional. Specify whether to retrieve the<br>default delivery target of a data collection.|Enter one of the following values:<br>- To find a delivery target that is<br>configured as the default delivery<br>option for a data collection, enter<br>` true `.<br>- To find a delivery target that isn't<br>configured as the default delivery<br>option for a data collection, enter<br>` false`.|
|`deliveryMethodIds`|Optional. Enter the system generated<br>unique identifier of a delivery method.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a<br>delivery method, see<br>“Retrieve delivery methods” on page 143.|
|`deliveryFormatIds`|Optional. Enter the system generated<br>unique identifier of a delivery format.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a<br>delivery format, see<br>“Retrieve delivery formats” on page 136.|
|`createdDateFrom`|Optional. To find delivery targets that were<br>created between a date range, enter the<br>initial date when the delivery targets were<br>created.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` createdDateFrom` parameter,<br>ensure that you also enter a value for<br>the` createdDateTo` parameter.|
|`createdDateTo`|Optional. To find delivery targets that were<br>created between a date range, enter the<br>latest date when the delivery targets were<br>created.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` createdDateTo ` parameter, ensure<br>that you also enter a value for the<br>` createdDateFrom` parameter.|
|`modifiedDateFrom`|Optional. To find delivery targets that were<br>modified between a date range, enter the<br>initial date when the delivery targets were<br>modified.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` modifiedDateFrom` parameter,<br>ensure that you also enter a value for<br>the` modifiedDateTo` parameter.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`modifiedDateTo`|Optional. To find delivery targets that were<br>modified between a date range, enter the<br>latest date when the delivery targets were<br>modified.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` modifiedDateTo ` parameter, ensure<br>that you also enter a value for the<br>` modifiedDateFrom` parameter.|
|`sortByField `|Optional. Specify the parameters to sort<br>the search results.|To sort the search results, enter one<br>of the following values:<br>-<br>` ID `<br>-<br>` NAME `<br>-<br>` TARGET_SYSTEM_REFERENCE `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the<br>search results.|Enter one of the following values:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum number of<br>results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of a delivery target: 

```
https://{{CDMP_URL}}/api/v1/integration/provisioning/deliveryTargets?
search=AWS&status=ACTIVE&sortByField=MODIFIED_BY&sort=ASC
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a delivery target: 

```
{
  "processingTime": 4190,
  "offset": 0,
  "limit": 50,
```

> `"totalCount": 1,`

```
  "objects": [
    {
      "id": "16e32a13-6fba-4f1a-a337-e0aeecf9fab4",
      "refId": "Target345",
      "name": "AWS-1",
      "description": "Data to be made available in a CSV file hosted on AWS, this is to
be sent via FTP.",
      "isDefault": true,
      "status": "ACTIVE",
      "physicalLocation": "AWS",
      "targetSystemReference": "Axon",
      "deliveryTemplate": {
        "id": "7f280b8a-35fb-4325-ada0-2bf5654b4891",
        "refId": "Template345",
        "name": "AWS",
        "description": "Send an excel file via FTP",
        "isDefault": true,
        "status": "ACTIVE",
        "managedAccess": "DISABLED",
        "deliveryType": "MANUAL",
        "templateOwners": [
          {
            "displayName": "John Doe",
            "id": "9f280b8a-35fb-4325-ada0-2bf5654b4892",
            "name": "username1",
            "email": "abc@xyz.com",
            "phone": "2020202023",
            "status": "ACTIVE",
            "userInfo": {
              "initials": "string",
              "avatarColor": "string",
              "description": "string",
              "title": "string",
              "timeZoneId": "string"
            }
          }
        ],
        "targetSystemReference": "Axon",
        "defaultPhysicalLocation": "AWS",
        "deliveryMethods": [
          {
            "id": "5wd2a13-9fha-4f1a-b317-e0vvtcf9efe4",
            "refId": "Method345",
            "name": "FTP",
            "status": "ACTIVE",
            "createdBy": "User2",
            "createdOn": "2022-04-20T10:50:39.251Z",
            "modifiedBy": "User1",
            "modifiedOn": "2022-04-20T10:50:39.251Z"
          }
        ],
        "deliveryFormats": [
          {
            "id": "3h32a13-9cdf-4f1a-b317-e0ttecf9def4",
            "refId": "Format345",
            "name": "XSLX",
            "status": "ACTIVE",
            "createdBy": "User2",
            "createdOn": "2022-04-20T10:50:39.251Z",
            "modifiedBy": "User1",
            "modifiedOn": "2022-04-20T10:50:39.251Z"
          }
        ],
        "createdBy": "User2",
        "createdOn": "2022-04-20T10:50:39.251Z",
        "modifiedBy": "User1",
        "modifiedOn": "2022-04-20T10:50:39.251Z"
      },
      "deliveryMethod": {
        "id": "5wd2a13-9fha-4f1a-b317-e0vvtcf9efe4",
```


```
        "refId": "Method345",
        "name": "FTP",
        "status": "ACTIVE",
        "createdBy": "User2",
        "createdOn": "2022-04-20T10:50:39.251Z",
        "modifiedBy": "User1",
        "modifiedOn": "2022-04-20T10:50:39.251Z"
      },
      "deliveryFormat": {
        "id": "3h32a13-9cdf-4f1a-b317-e0ttecf9def4",
        "refId": "Format345",
        "name": "XSLX",
        "status": "ACTIVE",
        "createdBy": "User2",
        "createdOn": "2022-04-20T10:50:39.251Z",
        "modifiedBy": "User1",
        "modifiedOn": "2022-04-20T10:50:39.251Z"
      },
      "createdBy": "User2",
      "createdOn": "2022-04-20T10:50:39.251Z",
      "modifiedBy": "User1",
      "modifiedOn": "2022-04-20T10:50:39.251Z"
    }
  ]
}
```

The following table describes the parameters of each delivery target that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`processingTime`|Time taken (in milliseconds) to complete the API call.|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of delivery targets retrieved.|
|`objects`|Details of the retrieved delivery target.|
|`objects > id`|System generated unique identifier of the delivery target.|
|`objects > refId`|Reference identifier of the delivery target.|
|`objects > name`|Name of the delivery target.|
|`objects > description`|Description of the delivery target.|
|`objects > isDefault `|Parameter that indicates whether the delivery target is the default<br>delivery option for a data collection. This parameter can have one of the<br>following values:<br>-<br>` true `. The delivery target is the default delivery option for a data<br>collection.<br>-<br>` false`. The delivery target isn't the default delivery option for a data<br>collection.|
|`objects > status `|The status indicates whether the delivery target is available for use to<br>the Data Users that order the data collection. A delivery target can have<br>one of the following statuses:<br>-<br>` ACTIVE `. The delivery target is available.<br>-<br>` INACTIVE`. The delivery target is unavailable.|

|**Parameter**|**Description**|
|---|---|
|`objects > physicalLocation`|The location where the data is delivered.<br>**Note:**If the value of the` managedAccess` parameter is` ENABLED `, the<br>data is not be delivered to the location that is specified in the<br>` physicalLocation` parameter. Instead, the data will be delivered to a<br>location that is generated at the time of order fulfillment. The generated<br>location is unique to each order that is fulfilled using this target.<br>For more information, see the_Manage access to data with Data Access_<br>_Management_topic in the_Set Up Data Marketplace_help.|
|`objects > targetSystemReference`|System where the data was delivered.|
|`objects > deliveryTemplate > id`|System generated unique identifier of the delivery template that was<br>used to create the delivery target.|
|`objects > deliveryTemplate >`<br>`refId`|Reference identifier of the delivery template.|
|`objects > deliveryTemplate > name`|Name of the delivery template.|
|`objects > deliveryTemplate >`<br>`description`|Description of the delivery template.|
|`objects > deliveryTemplate >`<br>`isDefault `|Parameter that indicates whether or not the delivery template is set as<br>the default delivery option for your Data Marketplace instance. The<br>parameter can have one of the following values:<br>-<br>` true `. The delivery template is the default delivery option.<br>-<br>` false`. The delivery target isn't the default delivery option.|
|`objects > deliveryTemplate >`<br>`status `|The status indicates whether the delivery template is available to be<br>used to create a delivery target of a data collection. A delivery template<br>can have one of the following statuses:<br>-<br>` ACTIVE `. The delivery template is available.<br>-<br>` INACTIVE`. The delivery template is unavailable.|
|`objects > deliveryTemplate >`<br>`managedAccess `|This parameter determines whether a unique data location is created<br>for each order that is fulfilled using a target that is based on this<br>template. This parameter can have one of the following values:<br>-<br>` ENABLED `. A unique data location is created for each order that is<br>fulfilled using a target that is based on this template. Furthermore, an<br>access management application such as Data Access Management<br>might customize the delivered data based on the characteristics of<br>the data, the Data User and the usage context that they specified in<br>the order.<br>-<br>` DISABLED`. The same data is made available in a data location that is<br>common to all Data Users.<br>For more information, see the_Manage access to data with Data Access_<br>_Management_topic in the_Set Up Data Marketplace_help.|
|`objects > deliveryTemplate >`<br>`deliveryType `|The type of delivery. A delivery template can have one of the following<br>types:<br>-<br>` AUTOMATIC `. The order approval and fulfillment is automated.<br>-<br>` MANUAL`, a stakeholder of the data collection must approve and fulfill<br>the order manually.|
|`objects > deliveryTemplate >`<br>`templateOwners`|Details of the Delivery Owner that is responsible for the delivery<br>template.|


|**Parameter**|**Description**|
|---|---|
|`objects > deliveryTemplate >`<br>`targetSystemReference`|Target system or resource reference from where the data is obtained.|
|`objects > deliveryTemplate >`<br>`defaultPhysicalLocation`|The default delivery location.|
|`objects > deliveryTemplate >`<br>`deliveryMethods`|Details of the delivery method of the delivery template.|
|`objects > deliveryTemplate >`<br>`deliveryFormats`|Details of the delivery format of the delivery template.|
|`objects > deliveryTemplate >`<br>`createdBy`|System generated unique identifier of the user account that created the<br>delivery template.|
|`objects > deliveryTemplate >`<br>`createdOn`|Date when the delivery template was created.|
|`objects > deliveryTemplate >`<br>`modifiedBy`|System generated unique identifier of the latest user account that<br>modified the delivery template.|
|`objects > deliveryTemplate >`<br>`modifiedOn`|Latest date when the delivery template was modified.|
|`objects > deliveryMethod > id`|System generated unique identifier of the delivery method.|
|`objects > deliveryMethod > refId`|Reference identifier of the delivery method.|
|`objects > deliveryMethod > name`|Name of the delivery method.|
|`objects > deliveryMethod > status `|The status indicates whether a delivery method is available to be added<br>to delivery templates. A delivery method can have one of the following<br>statuses:<br>-<br>` ACTIVE `. The delivery method is available.<br>-<br>` INACTIVE`. The delivery method is not available.|
|`objects > deliveryMethod >`<br>`createdBy`|System generated unique identifier of the user account that created the<br>delivery method.|
|`objects > deliveryMethod >`<br>`createdOn`|Date when the delivery method was created.|
|`objects > deliveryMethod >`<br>`modifiedBy`|System generated unique identifier of the latest user account that<br>modified the delivery method.|
|`objects > deliveryMethod >`<br>`modifiedOn`|Latest date when the delivery method was modified.|
|`objects > deliveryFormat > id`|System generated unique identifier of the delivery format.|
|`objects > deliveryFormat > refId `<br>` objects > deliveryFormat > name`|Reference identifier of the delivery format.<br>Name of the delivery format.|

|**Parameter**|**Description**|
|---|---|
|`objects > deliveryFormat > status `|The status indicates whether a delivery format is available to be added<br>to delivery templates. A delivery format can have one of the following<br>statuses:<br>-<br>` ACTIVE `. The delivery format is available.<br>-<br>` INACTIVE`. The delivery format is not available.|
|`objects > deliveryFormat >`<br>`createdBy`|System generated unique identifier of the user account that created the<br>delivery format.|
|`objects > deliveryFormat >`<br>`createdOn`|Date when the delivery format was created.|
|`objects > deliveryFormat >`<br>`modifiedBy`|System generated unique identifier of the latest user account that<br>modified the delivery format.|
|`objects > deliveryFormat >`<br>`modifiedOn`|Latest date when the delivery format was modified.|
|`objects > createdBy`|System generated unique identifier of the user account that created the<br>delivery target.|
|`objects > createdOn`|Date when the delivery target was created.|
|`objects > modifiedBy`|System generated unique identifier of the latest user account that<br>modified the delivery target.|
|`objects > modifiedOn`|Latest date when the delivery target was modified.|


#### Modify delivery targets
Use a REST API to modify delivery targets in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/provisioning/deliveryTargets**|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`id`|Required. Enter the system generated<br>unique identifier of the delivery target<br>that you want to modify.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a delivery target, see<br>“Retrieve delivery targets” on page 241.|
|`refId`|Optional. Enter a reference identifier<br>for the delivery target.|If you don't specify a reference identifier,<br>Data Marketplace automatically assigns a<br>unique value to the object. The reference<br>identifier that Data Marketplace<br>automatically generates contains a prefix.<br>The Administrator can specify the prefix<br>of the automatically generated reference<br>identifier in Metadata Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a unique<br>value. Ensure that you don't use the prefix<br>value that is configured in Metadata<br>Command Center.|
|`name`|Required. Enter a name for the<br>delivery target.|Ensure that you enter a unique value.<br>Data Marketplace doesn't consider the<br>letter case when it verifies the uniqueness<br>of the` name` parameter's value. For<br>example, if you try to name a delivery<br>target as "Excel over ipfs" while a delivery<br>target called "Excel over IPFS" already<br>exists, the API call fails.|
|`description`|Required. Enter a description for the<br>delivery target.|-|
|`status`|Required. Specify a status for the<br>delivery target. The status determines<br>whether the delivery target is<br>available for use to the Data Users<br>that order the data collection.|Enter one of the following values:<br>- To make the delivery targets available,<br>enter` ACTIVE`.<br>- To make the delivery targets<br>unavailable, enter` INACTIVE`.|
|`targetSystemReference`|Optional. Enter the target system or<br>resource reference from where the<br>data is obtained..|-|
|`physicalLocation`|Optional. Enter the location where the<br>data is delivered to a Data User.|-|
|`deliveryMethodId`|Required. Enter the system generated<br>unique identifier of the delivery<br>method.|For more information about how you can<br>use an API to get the unique identifier of a<br>delivery method, see<br>“Retrieve delivery methods” on page 143.|
|`deliveryFormatId`|Required. Enter the system generated<br>unique identifier of the delivery<br>format.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a delivery format, see<br>“Retrieve delivery formats” on page 136.|

#### Example request
The following example shows how you can use an API to modify a delivery target: 

```
{
    "items": [
        {
            "id": "13c937c3-e6d8-3006-a530-f3cd6ecf2a8f",
            "refId": "Target561",
            "name": "Customer DB",
            "description": "Customer DB Delivery updated",
            "status": "ACTIVE",
            "targetSystemReference": "AXON",
            "physicalLocation": "Oracle db ",
            "deliveryMethodId": "c35181e7-7832-3b5c-b0d3-fc2360a82f05",
            "deliveryFormatId": "fe4e89ab-80e9-3e0d-8aba-71b79deab4b8"
        }
    ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to modify a delivery target: 

```
{
    "processingTime": 4483,
    "objects": [
        {
            "index": 1,
            "id": "13c937c3-e6d8-3006-a530-f3cd6ecf2a8f",
            "refId": "Target561",
            "name": "Customer DB"
        }
    ]
}
```

The following table describes the parameters of each delivery target that is modified: 

|**Parameter**|**Description**|
|---|---|
|`index`|The position of the delivery target in the` objects` JSON array. This value does not impact how the<br>delivery target is used by a Data Marketplace user.|
|`id`|System generated unique identifier of the delivery target.|
|`refId`|Reference identifier of the delivery target.|
|`name`|Name of the delivery target.|


### Add or remove terms of use from data collections
Use a REST API to add terms of use to a data collection or to remove terms of use from a data collection. 

Before you make the API call, ensure that the data collection and the terms of use already exist in Data Marketplace. 


#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataCollections/termsOfUse**|
|Method|PUT|


#### **Note:**
- The number of terms of use that you can add per API call depends on the cloud provider the IDMC POD is hosted on. The following table shows the number of terms of use that you can add per API call on each cloud provider: 

|**Cloud provider**|**Number of terms of use**|
|---|---|
|Amazon Web Services|30|
|Microsoft Azure|10|


- You can call a maximum of 100 APIs per minute. 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`dataCollectionId`|Required. Enter the system<br>generated unique identifier of<br>the data collection to which<br>you want to add the terms of<br>use or from which you want to<br>remove terms of use.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a data collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique identifier of<br>a data collection from the Data Marketplace<br>interface, open the data collection. The data<br>collection page's URL contains the system<br>generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?`<br>`dtn=&tab=summary `, the system generated<br>unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`termsOfUse >`<br>`termsOfUseId`|Required. Enter the system<br>generated unique identifier of<br>the terms of use that you want<br>to add to a data collection or<br>remove from a data collection.|For more information about how you can use an<br>API to get the system generated unique identifier<br>of a terms of use, see<br>“Retrieve terms of use” on page 165.|
|`termsOfUse > operation `|Required. Specify the action<br>that you want to perform.|Enter one of the following values:<br>- To add terms of use to a data collection, enter<br>` ADD`.<br>- To remove terms of use from a data<br>collection, enter` REMOVE`.|


#### Example request
The following example shows how you can use an API to add terms of use to a data collection:. 

```
[
   {
      "dataCollectionId":"599fec17-915c-43ec-91fd-b0078fd4d31d",
      "termsOfUse":[
         {
            "termsOfUseId":"5f4d503b-0fd2-4127-bb66-5c48252d19af",
            "operation":"ADD"
         }
      ]
   }
]
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the request query based on the parameters you had mapped with values. 

If the terms of use is successfully added or removed, the following response is displayed: 

```
204 OK code
```


### Retrieve the terms of use of a data collection
Use a REST API to retrieve the terms of use of a data collection. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/dataCollections/<dataCollectionId>/termsOfUse**<br>`<dataCollectionId>`: Required. Enter the system generated unique identifier of the data collection for<br>which you want to retrieve the terms of use.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>data collection, see<br>“Retrieve data collections” on page 217.|
||To get the system generated unique identifier of a data collection from the Data Marketplace interface,<br>open the data collection. The data collection page's URL contains the system generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary `, the system generated unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`offset`|Optional. Enter the starting<br>index for the paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum<br>number of results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API call to retrieve the terms of use of a data collection: 

```
https://{{CDMP_URL}}/api/v1/integration/dataCollections/794a215f-5479-4b55-8e1b-
a4866ec9fe82/termsOfUse?limit=100
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the request query based on the data collection that you specified.

The following example shows the response of an API call to retrieve the terms of use of a data collection: 

```
{
  "processingTime": 123,
  "offset": 0,
  "limit": 100,
  "totalCount": 230,
  "objects": [
    {
      "id": "7bb0bcc1-23a9-4ffc-9f39-50c7b4df91b0",
      "refId": "TOU-434",
      "name": "Retail only",
      "description": "This data is only for retail employees.",
      "type": "NEUTRAL",
      "status": "DISABLED",
      "acknowledgement": true,
      "referenceLink": null,
      "createdBy": "5tSQ0vG66z9jS0KBQkY84r",
      "createdOn": "2022-05-17T10:05:21.453Z",
      "modifiedBy": "5tSQ0vG66z9jS0KBQkY84r",
      "modifiedOn": "2022-05-17T10:06:57.433Z"
    }
  ]
}
```

The following table describes the parameters of each terms of use that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of terms of use retrieved.|
|`id`|System generated unique identifier of the terms of use.|
|`refId`|Reference identifier of the terms of use.|
|`name`|Name of the terms of use.|
|`description`|Description of the terms of use.|
|`type`|Type of the terms of use.<br>Your administrator defines the terms of use types in Metadata Command Center. For more<br>information about terms of use, see the_Terms of use_topic in the_Set Up Data Marketplace_help.|
|`status `|The status indicates whether the terms of use are available to be added to data collections. A<br>terms of use can have one of the following statuses:<br>-<br>` ENABLED `. The terms of use are available to be added to data collections.<br>-<br>` DISABLED`. The terms of use isn't available to be added to data collections.|
|`acknowledgement `|Parameter that indicates whether the Data User is required to acknowledge the terms of use<br>when they place an order. The parameter can be one of the following values:<br>-<br>` true `. The Data User must acknowledge the terms of use when they place an order.<br>-<br>` false`. The Data User doesn't need to acknowledge the terms of use when they place an<br>order.|
|`referenceLink`|Uniform resource identifier of the terms of use.|
|`createdBy`|System generated unique identifier of the user account that created the terms of use.|


|**Parameter**|**Description**|
|---|---|
|`createdOn`|Date when the terms of use was created.|
|`modifiedBy`|System generated unique identifier of the latest user account that modified the terms of use.|
|`modifiedOn`|Latest date when the terms of use was modified.|


### Retrieve data collection rating
Use REST APIs to retrieve the rating of a data collection. You can use this API to retrieve the average rating of the data collection and the rating for each usage context. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/integration/collaboration/<objectId>/interactions/ratings**<br>`<objectId>`: Required. Enter the system generated unique identifier of the data collection for which you<br>want to retrieve ratings.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>data collection, see<br>“Retrieve data collections” on page 217.|
||To get the system generated unique identifier of a data collection from the Data Marketplace interface,<br>open the data collection. The data collection page's URL contains the system generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/datacollection/25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary `, the system generated unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the rating of a data collection: 

```
https://{{CDMP_URL}}/api/v1/integration/collaboration/d2313aed-330e-44a0-
bcfa-380ee4a95a62/interactions/ratings
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of the API call: 

```
{
  "processingTime": 0,
  "id": "d2313aed-330e-44a0-bcfa-380ee4a95a62",
  "globalRating": 4,
  "contextRatings": [
    {
      "id": "16a5e036-02d7-4c01-a5f7-c99ffca90cde",
      "averageRating": 2
    }
  ]
}
```

The following table contains the parameters in the response body: 

|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the data collection.|
|`globalRating`|Average rating of the data collection for all the usage<br>contexts. This parameter can have a value between` 0 ` and<br>` 5`.|
|`contextRatings > id`|System generated unique identifier of the usage context<br>for which a Data User has rated the data collection.|
|`contextRatings > averageRating`|Average rating of the data collection for a usage context.<br>This parameter can have a value between` 0` and` 5`.|


## Chapter 9: Manage requests, orders and consumer accesses
This chapter includes the following topics: 

- Data collection requests, 258 

- Orders, 268 

- Consumer accesses, 302 

### Data collection requests
If a Data User cannot find a data collection that meets their requirement, they can request for the creation of a new data collection. 

#### Retrieve data collection requests
Use a REST API to retrieve the details of data collection requests in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/requestNewDataCollections**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`requestIds`|Optional. Enter the system generated<br>unique identifier of a data collection<br>request.|To get the system generated unique<br>identifier of a data collection request,<br>open the data collection request. The<br>data collection request page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL` https://{{CDMP_URL}}/`<br>`datacollectionrequest/6e5964ec-`<br>`e0aa-4eac-963a-3dae0fa1d639?`<br>`dtn=Request~9b4d `, the system<br>generated unique identifier is<br>` 6e5964ec-e0aa-4eac-963a-3dae0fa1d639 `.<br>To enter more than one value, use the<br>following format:<br>` ids=<value1>&ids=<value2>`|
|`userIds`|Optional. Enter the system generated<br>unique identifier of the user that<br>requested the new data collection.|To get the system generated unique<br>identifier of a user account, navigate<br>to**My Services > Administrator >**<br>**Users**. On the**Users**page, click a user<br>account. The user account page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `, the<br>system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`status `|Optional. Specify the status of the data<br>collection request.|Enter one of the following values:<br>- To find data collection requests<br>that aren't completed, enter<br>` PENDING`.<br>- To find the data collection requests<br>where a new data collection was<br>created in response to the request,<br>enter` COMPLETED`.<br>- To find rejected data collection<br>requests, enter` REJECTED`.<br>- To find cancelled data collection<br>requests, enter` CANCELLED`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`createdDateFrom`|Optional. To find data collection requests<br>that were created between a date range,<br>enter the initial date when the data<br>collection requests were created.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` createdDateFrom ` parameter, ensure<br>that you also enter a value for the<br>` createdDateTo` parameter.|
|`createdDateTo`|Optional. To find data collection requests<br>that were created between a date range,<br>enter the latest date when the data<br>collection requests were created.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` createdDateTo ` parameter, ensure<br>that you also enter a value for the<br>` createdDateFrom` parameter.|
|`modifiedDateFrom`|Optional. To find data collection requests<br>that were modified between a date range,<br>enter the initial date when the data<br>collection requests were modified.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` modifiedDateFrom` parameter,<br>ensure that you also enter a value for<br>the` modifiedDateTo` parameter.|
|`modifiedDateTo`|Optional. To find data collection requests<br>that were modified between a date range,<br>enter the latest date when the data<br>collection requests were modified.|To specify a date, use the` YYYY-MM-DD ` format. The value that you specify<br>is automatically converted and stored<br>in the Coordinated Universal Time<br>(UTC) time standard.<br>If you have specified a value for the<br>` modifiedDateTo ` parameter, ensure<br>that you also enter a value for the<br>` modifiedDateFrom` parameter.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`referencedDataCollection `<br>` Ids`|Optional. Enter the system generated<br>unique identifier of the data collection<br>based on which the new data collection is<br>to be created.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a data<br>collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique<br>identifier of a data collection from the<br>Data Marketplace interface, open the<br>data collection. The data collection<br>page's URL contains the system<br>generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/`<br>`datacollection/25158afc-3dfb-44ef-8f3e-`<br>`cec1e171d0f1?`<br>`dtn=&tab=summary `, the system<br>generated unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`referencedCategoryIds`|Optional. Enter the system generated<br>unique identifier of the category in which<br>the new data collection is to be created.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a<br>category, see<br>“Retrieve categories” on page 124.<br>To get the system generated unique<br>identifier of a category from the Data<br>Marketplace interface, open the<br>category. The category page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-a1e412c1ce13&dtn=_AfterEBF`<br>`%20may20`, the system generated<br>unique identifier is` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|`offset`|Optional. Enter the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum number of<br>results.|Default is` 50`.<br>Maximum value is` 100`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sortByField `|Optional. Specify the parameters to sort<br>the search results.|To sort the search results, enter one<br>of the following values:<br>-<br>` ID `<br>-<br>` JUSTIFICATION `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` sortByField >`<br>`MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the<br>search results.|You can sort the search results in the<br>following order:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default is` DESC`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of a data collection request: 

```
https://{{CDMP_URL}}/api/v1/integration/requestNewDataCollections?
requestIds=407225ad-7870-4678-8169-43829d80c4bc&sortByField=STATUS&sort=DESC&limit=50
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a data collection request: 

```
{
  "processingTime": 486,
  "offset": 0,
  "limit": 50,
  "totalCount": 1,
  "objects": [
    {
      "id": "407225ad-7870-4678-8169-43829d80c4bc",
      "refId": "d90574a4-417d-4c34-b9f1-4f2da5fc997a",
      "justification": "None of the data collections contain retail information specific
to the EMEA region.",
      "costCenter": "Retail-2007IQ",
      "referencedCategory": {
        "id": "ad90417f-28bf-44ea-8204-127d70a4a7c9",
        "name": "Retail",
        "description": "This category contains information on the operational data of
retail outlets.",
        "refId": "d00be828-d47c-4e9f-a65f-502ba6b633f9",
        "status": "ACTIVE",
        "effectiveStatus": "ACTIVE"
      },
      "referencedDataCollection": {},
      "createdDataCollection": {},
      "usageContext": {},

      "status": "PENDING",
      "createdBy": {
        "displayName": "John Doe",
        "id": "8npzPxKim0XjHE4es2gkAc",
        "name": "John Doe",
        "email": "jdoe@informatica.com",
        "phone": "2020202020",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      },
      "createdOn": "2022-09-15T17:04:29.382Z",
      "modifiedBy": {
        "displayName": "John Doe",
        "id": "8npzPxKim0XjHE4es2gkAc",
        "name": "John Doe",
        "email": "jdoe@informatica.com",
        "phone": "2020202020",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      },
      "modifiedOn": "2022-09-15T17:04:29.382Z"
    }
  ]
}
```

The following table describes the parameters of each data collection request that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Start index for paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of data collection requests retrieved.|
|`id`|System generated unique identifier of the data collection request.|
|`refId`|Reference identifier of the data collection request.|
|`justification`|Business justification entered by the requestor of the new data collection.|
|`costCenter`|Cost center of the requestor of the new data collection.|
|`referencedCategory`|Details of the category in which the new data collection is to be created.|
|`referencedCategory > id`|System generated unique identifier of the category in which the new data<br>collection is to be created.|
|`referencedCategory > name`|Name of the category in which the new data collection is to be created.|
|`referencedCategory >`<br>`description`|Description of the category in which the new data collection is to be created.|
|`referencedCategory > refId`|Reference identifier of the category in which the new data collection is to be<br>created.|
|`referencedCategory > status`|Status of the category in which the new data collection is to be created.|
|`referencedCategory >`<br>`effectiveStatus`|The effective status of the category in which the new data collection is to be<br>created.|


|**Parameter**|**Description**|
|---|---|
|`referencedDataCollection`|Details of the data collection based on which the new data collection is to be<br>created.|
|`createdDataCollection`|Details of the new data collection that is created in response to the data<br>collection request.|
|`usageContext`|Details of the usage context entered by the requestor of the new data<br>collection.|
|`status `|Status of the data collection request. A data collection request can have one<br>of the following statuses:<br>-<br>` PENDING `. A request for a new data collection is submitted.<br>-<br>` COMPLETED `. A new data collection was created in response to the<br>request.<br>-<br>` REJECTED `. The request was rejected by a stakeholder of the category or<br>data collection.<br>-<br>` CANCELLED`. The data collection request was cancelled by the Data User.|
|`createdBy`|Details of the user account that requested a new data collection.|
|`createdOn`|Date when the data collection request was created.|
|`modifiedBy`|Details of the latest user account that modified the data collection request.|
|`modifiedOn`|Latest date when the data collection request was modified.|


#### Reject data collection requests
Use a REST API to reject a request for a new data collection in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v1/integration/requestNewDataCollections/<Id>/reject** `<Id>` : Required. Enter the system generated unique identifier of the data collection request that you want to reject. For more information about how you can use an API to get the system generated unique identifier of a data collection request, see “Retrieve data collection requests” on page 258. To get the system generated unique identifier of a data collection request from the Data Marketplace interface, open the data collection request. The data collection request page's URL contains the system generated unique identifier. For example, in the URL `https://{{CDMP_URL}}/datacollectionrequest/6e5964ece0aa-4eac-963a-3dae0fa1d639?dtn=Request~9b4d ` , the system generated unique identifier is ` 6e5964ec-e0aa-4eac-963a-3dae0fa1d639` . Method PUT 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to reject a data collection request: 

```
https://{{CDMP_URL}}/api/v1/integration/requestNewDataCollections/d2313aed-330e-44a0-
bcfa-380ee4a95a62/reject?comment=Reject%20DCR
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data collection request is rejected successfully: 

```
204 OK code
```

#### Fulfill data collection requests
Use a REST API to fulfill a request for a new data collection in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/requestNewDataCollections/<Id>/fulfill**<br>`<Id>`: Required. Enter the system generated unique identifier of the data collection request that you want<br>to fulfill.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>data collection request, see<br>“Retrieve data collection requests” on page 258.|
||To get the system generated unique identifier of a data collection request from the Data Marketplace<br>interface, open the data collection request. The data collection request page's URL contains the system<br>generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/datacollectionrequest/6e5964ec-e0aa-4eac-963a-3dae0fa1d639?dtn=Request~9b4d `, the system generated unique identifier is<br>` 6e5964ec-e0aa-4eac-963a-3dae0fa1d639`.|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`dataCollectionId`|Required. Enter the system<br>generated unique identifier of the<br>new data collection that is created<br>in response to the data collection<br>request.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a data collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique<br>identifier of a data collection from the Data<br>Marketplace interface, open the data<br>collection. The data collection page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary `, the<br>system generated unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`comment`|Optional. Enter a comment about the<br>action taken.|-|
|`costCenter`|Optional. Enter the cost center of<br>the user that requested the new data<br>collection.|If the requestor entered an incorrect or an<br>invalid cost center, you can enter the<br>correct cost center.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to fulfill data collection requests: 

```
https://{{CDMP_URL}}/api/v1/integration/requestNewDataCollections/b9992251-
e69d-4fdf-9bc1-f1e19f6c908a/fulfill?
dataCollectionId=7c0198bb-7577-4791-822a-6a5c62ceff4c&comment=Fulfill

%20DCR&costCenter=FulfillDCRCostCenter
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data collection request is fulfilled successfully: 

```
204 OK code
```

#### Cancel data collection requests
Use a REST API to cancel a request for a new data collection in Data Marketplace.

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/requestNewDataCollections/<Id>/cancel**<br>`<Id>`: Required. Enter the system generated unique identifier of the data collection request that you want<br>to cancel.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>data collection request, see<br>“Retrieve data collection requests” on page 258.|
||To get the system generated unique identifier of a data collection request from the Data Marketplace<br>interface, open the data collection request. The data collection request page's URL contains the system<br>generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/datacollectionrequest/6e5964ec-e0aa-4eac-963a-3dae0fa1d639?dtn=Request~9b4d `, the system generated unique identifier is<br>` 6e5964ec-e0aa-4eac-963a-3dae0fa1d639`.|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to cancel a data collection request: 

```
https://{{CDMP_URL}}/api/v1/integration/requestNewDataCollections/d2313aed-330e-44a0-
bcfa-380ee4a95a62/cancel?comment=Cancel%20DCR
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data collection request is cancelled successfully: 

```
204 OK code
```

#### Delete data collection requests
If you submitted a data collection request accidentally or if a data collection request is obsolete, you can use a REST API to delete the request. 


#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/requestNewDataCollections/<Id>**<br>`<Id>`: Required. Enter the system generated unique identifier of the data collection request that you want<br>to delete.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>data collection request, see<br>“Retrieve data collection requests” on page 258.|
||To get the system generated unique identifier of a data collection request from the Data Marketplace<br>interface, open the data collection request. The data collection request page's URL contains the system<br>generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/datacollectionrequest/6e5964ec-e0aa-4eac-963a-3dae0fa1d639?dtn=Request~9b4d `, the system generated unique identifier is<br>` 6e5964ec-e0aa-4eac-963a-3dae0fa1d639`.|
|Method|DELETE|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 

#### Example request
The following example shows how you can use an API to delete a data collection request: 

```
https://{{CDMP_URL}}/api/v1/integration/requestNewDataCollections/d2313aed-330e-44a0-
bcfa-380ee4a95a62
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the data collection request is deleted successfully: 

```
204 OK code
```

### Orders
An order is a request made by a Data User to gain access to a data collection. A Data User's order is reviewed by the stakeholders of the data collection who are responsible for the approval and fulfillment of the order. 

#### Retrieve orders
Use REST APIs to retrieve the details of orders in Data Marketplace.

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/orders/**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`orderIds`|Optional. Enter the system generated<br>unique identifier of an order.|To get the system generated unique<br>identifier of a order, open the order.<br>The order page's URL contains the<br>system generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/order/`<br>`3d48daf6-5e75-4e1a-848c-b6821fc33f74?dtn=Order~579b`,<br>the system generated unique identifier<br>is` 3d48daf6-5e75-4e1a-848c-b6821fc33f74 `.<br>To enter more than one value, use the<br>following format:<br>` ids=<value1>&ids=<value2>`|
|`dataCollectionIds`|Optional. Enter the system generated<br>unique identifier of the ordered data<br>collection.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a data<br>collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique<br>identifier of a data collection from the<br>Data Marketplace interface, open the<br>data collection. The data collection<br>page's URL contains the system<br>generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary`,<br>the system generated unique identifier<br>is` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`ordersCreatedBy`|Optional. Enter the system generated<br>unique identifier of the user accounts of<br>the Data Users that placed the order.|To get the system generated unique<br>identifier of a user account, navigate to<br>**My Services > Administrator > Users**.<br>On the**Users**page, click a user<br>account. The user account page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `, the<br>system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`collectionDataOwners`|Optional. Enter the system generated<br>unique identifier of the user accounts of<br>the stakeholders that are assigned the<br>Data Owner stakeholder role on the data<br>collection.|To get the system generated system<br>generated unique identifier of a user<br>account, navigate to**My Services >**<br>**Administrator > Users**. On the**Users**<br>page, click a user account. The user<br>account page's URL contains the<br>system generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `, the<br>system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`collectionTechOwners`|Optional. Enter the system generated<br>unique identifier of the user accounts of<br>the stakeholders that are assigned the<br>Technical Owner stakeholder role on the<br>data collection.|To get the system generated unique<br>identifier of a user account, navigate to<br>**My Services > Administrator > Users**.<br>On the**Users**page, click a user<br>account. The user account page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `, the<br>system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`status`|Optional. Specify the status of the order.|Enter one of the following values:<br>- To find the orders that are approved<br>for fulfillment, enter` APPROVED `.<br>- To find rejected orders, enter<br>` REJECTED `.<br>- To find fulfilled orders, enter<br>` COMPLETE `.<br>- To find cancelled orders, enter<br>` CANCELLED`.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`categoryIds`|Optional. Enter the system generated<br>unique identifier of the category that<br>contains the ordered data collection.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a<br>category, see<br>“Retrieve categories” on page 124.<br>To get the system generated unique<br>identifier of a category from the Data<br>Marketplace interface, open the<br>category. The category page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL` https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-a1e412c1ce13&dtn=_AfterEBF`<br>`%20may20`, the system generated<br>unique identifier is` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|`categoryOwners`|Optional. System generated unique<br>identifier of the owners of the category<br>that contains the ordered data<br>collection.|To get the system generated unique<br>identifier of a user account, navigate to<br>**My Services > Administrator > Users**.<br>On the**Users**page, click a user<br>account. The user account page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `, the<br>system generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`createdDateFrom`|Optional. To find orders that were<br>created between a date range, enter the<br>initial date when the orders were<br>created.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` createdDateFrom ` parameter, ensure<br>that you also enter a value for the<br>` createdDateTo` parameter.|
|`createdDateTo`|Optional. To find orders that were<br>created between a date range, enter the<br>latest date when the orders were<br>created.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` createdDateTo ` parameter, ensure<br>that you also enter a value for the<br>` createdDateFrom` parameter.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`modifiedDateFrom`|Optional. To find orders that were<br>modified between a date range, enter the<br>initial date when the orders were<br>modified.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` modifiedDateFrom` parameter,<br>ensure that you also enter a value for<br>the` modifiedDateTo` parameter.|
|`modifiedDateTo`|Optional. To find orders that were<br>modified between a date range, enter the<br>latest date when the orders were<br>modified.|To specify a date, use the` YYYY-MM-DD `<br>format. The value that you specify is<br>automatically converted and stored in<br>the Coordinated Universal Time (UTC)<br>time standard.<br>If you have specified a value for the<br>` modifiedDateTo ` parameter, ensure<br>that you also enter a value for the<br>` modifiedDateFrom` parameter.|
|`sortByField `|Optional. Specify the parameters to sort<br>the search results.|To sort the search results, enter one of<br>the following values:<br>-<br>` ID `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default value is` MODIFIED_ON`.|
|`sort`|Optional. Set the sorting order of the<br>search results.|You can sort the search results in the<br>following order:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default value is` DESC`.|
|`offset`|Optional. Enter the starting index for the<br>paginated results.|Default value is` 0`.|
|`limit`|Optional. Enter the maximum number of<br>results.|Default value is` 50`.<br>Maximum value is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve and display order details : 

```
https://{{CDMP_URL}}/api/v1/integration/orders?

limit=100&status=APPROVED&ordersCreatedBy=User1&sortByField=MODIFIED_BY&sort=ASC
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve and display order details: 

```
{
    "processingTime": 1789,
    "offset": 0,
    "limit": 100,
    "totalCount": 195,
    "objects": [
      {
        "justification": "Sales analytics",
        "deliveryRequests": null,
        "requestedProvisionedTargetRef": null,
        "refId": "ORD-374",
        "dataCollections": [
          {
            "id": "908626d6-39fb-4bdd-933d-8e275240c8c3",
            "name": "DS",
            "description": "DS",
            "refId": "DCL-993",
            "category": {
              "id": "b51f8cc7-fd05-4759-a63b-342268c24e29",
              "refId": "CAT-613",
              "parentId": null,
              "parentRefId": null,
              "parent": null,
              "name": "Category",
              "description": "Category",
              "status": "ACTIVE",
              "effectiveStatus": "ACTIVE",
              "hasChildCategory": false,
              "hasActiveChildCategory": false,
              "dataOwners": [],
              "createdBy": {
                "displayName": "Johnny Doe",
                "id": "6bX1o0lH3UmbPuF0OF5lkj",
                "name": "15Jul_CDMP_QE",
                "email": "jydoe@informatica.com",
                "phone": "555-243-456",
                "status": "ACTIVE",
                "userInfo": {},
                "isGroup": "true"
              },
              "createdOn": "2022-07-15T19:04:56.151Z",
              "modifiedBy": {
                "displayName": "Johnny Doe",
                "id": "6bX1o0lH3UmbPuF0OF5lkj",
                "name": "15Jul_CDMP_QE",
                "email": "jydoe@informatica.com",
                "phone": "555-243-456",
                "status": "ACTIVE",
                "userInfo": {},
                "isGroup": "true"
              },
              "modifiedOn": "2022-07-15T19:04:56.151Z"
            },
            "usageContexts": [],
            "dataset": [],
            "dataCollectionStatus": "PUBLISHED",
            "dataOwners": [
              {
                "displayName": "Jane Doe",
                "id": "11bnOFaMvrXc0kal2bPXUX",
                "name": "Jane Doe",
                "email": "jdoe@informatica.com",
                "phone": "8758939746",
```


```
                "status": "ACTIVE",
                "userInfo": {},
                "isGroup": "true"
              }
            ],
            "technicalOwners": [
              {
                "displayName": "John Doe",
                "id": "aH2O7v8jSgqeZDYHe45NAJ",
                "name": "John Doe",
                "email": "jdoe@informatica.com",
                "phone": "8758939746",
                "status": "ACTIVE",
                "userInfo": {},
                "isGroup": "true"
              }
            ],
            "createdBy": {
              "displayName": "User1",
                    "id": "6bX1o0lH3UmbPuF0OF5lkj",
                    "name": "User1_name",
                    "email": "ebc@xyz.com",
                    "phone": "555-243-456",
              "status": "ACTIVE",
              "userInfo": {},
              "isGroup": "true"
            },
            "createdOn": "2022-07-15T19:46:46.866Z",
            "modifiedBy": {
              "displayName": "Johnny Doe",
              "id": "6bX1o0lH3UmbPuF0OF5lkj",
              "name": "15Jul_CDMP_QE",
              "email": "jydoe@informatica.com",
              "phone": "555-243-456",
              "status": "ACTIVE",
              "userInfo": {},
              "isGroup": "true"
            },
            "modifiedOn": "2022-07-15T19:46:46.866Z"
          }
        ],
        "termsOfUse": [
          {
            "id": "1b6b2651-bed6-4cc6-bd41-2f1091cc07a1",
            "termsOfUseId": null,
            "termsOfUseRefId": null,
            "generalTermsOfUse": "{"tosMessage":"general terms of use "}",
            "name": Accessible,
            "description": "This data is accessible to anyone in the organization",
            "type": "ACCESSIBLE",
            "status": "ENABLED",
            "acknowledgement": false,
            "parentId": null,
            "referenceLink": null,
            "createdOn": "2022-07-15T19:07:55.552Z"
          }
        ],
        "status": "APPROVED",
        "id": "875a5d43-6d91-473c-ac42-eb02cfe059d9",
        "usageContext": null,
        "createdBy": {
          "displayName": "Johnny Doe",
          "id": "6bX1o0lH3UmbPuF0OF5lkj",
          "name": "15Jul_CDMP_QE",
          "email": "jydoe@informatica.com",
          "phone": "555-243-456",
          "userInfo": {},
          "isGroup": "true"
        },
        "createdOn": "2022-07-15T19:47:28.598Z",

        "modifiedBy": {
          "displayName": "Johnny Doe",
          "id": "6bX1o0lH3UmbPuF0OF5lkj",
          "name": "15Jul_CDMP_QE",
          "email": "jydoe@informatica.com",
          "phone": "555-243-456",
          "userInfo": {},
          "isGroup": "true"
        },
        "modifiedOn": "2022-07-15T19:47:46.143Z"
      },
      {
        "justification": "Standard retail tasks.",
        "deliveryRequests": "No specification.",
        "requestedProvisionedTargetRef": null,
        "refId": "ORD-834",
        "dataCollections": [
          {
            "id": "a290034e-0362-434f-a3cc-7f659e009fae",
            "name": "Collection_TSKV1",
            "description": "Automation",
            "refId": "Collection_TSKV1",
            "category": {
              "id": "dac80150-bde3-4c8f-9129-9e56893de367",
              "refId": "CAT-173",
              "parentId": null,
              "parentRefId": null,
              "parent": null,
              "name": "Category_TSKV1",
              "description": "Automation",
              "status": "ACTIVE",
              "effectiveStatus": "ACTIVE",
              "hasChildCategory": false,
              "hasActiveChildCategory": true,
              "dataOwners": [],
              "createdBy": {
                "displayName": "John Doe",
                "id": "f400dUqjPuaiJ9KBrzOhiB",
                "name": "John Doe",
                "email": "jdoe@informatica.com",
                "phone": "00000099999999999",
                "status": "ACTIVE",
                "userInfo": {},
                "isGroup": "true"
              },
              "createdOn": "2022-07-16T12:06:57.049Z",
              "modifiedBy": {
                "displayName": "John Doe",
                "id": "f400dUqjPuaiJ9KBrzOhiB",
                "name": "John Doe",
                "email": "jdoe@informatica.com",
                "phone": "00000099999999999",
                "status": "ACTIVE",
                "userInfo": {},
                "isGroup": "true"
              },
              "modifiedOn": "2022-07-16T12:06:57.049Z"
            },
            "usageContexts": null,
            "dataset": null,
            "dataCollectionStatus": "PUBLISHED",
            "dataOwners": [],
            "technicalOwners": [],
            "createdBy": {
              "displayName": "User1",
                    "id": "6bX1o0lH3UmbPuF0OF5lkj",
                    "name": "User1_name",
                    "email": "ebc@xyz.com",
                    "phone": "555-243-456",
              "status": "ACTIVE",
```


```
              "userInfo": {},
              "isGroup": "true"
            },
            "createdOn": "2022-07-16T12:07:04.596Z",
            "modifiedBy": {
              "displayName": "John Doe",
              "id": "f400dUqjPuaiJ9KBrzOhiB",
              "name": "John Doe",
              "email": "jdoe@informatica.com",
              "phone": "00000099999999999",
              "status": "ACTIVE",
              "userInfo": {},
              "isGroup": "true"
            },
            "modifiedOn": "2022-07-16T12:07:04.596Z"
          }
        ],
        "termsOfUse": [
          {
            "id": "1b6b2651-bed6-4cc6-bd41-2f1091cc07a1",
            "termsOfUseId": null,
            "termsOfUseRefId": null,
            "generalTermsOfUse": "{"tosMessage":"general terms of use "}",
            "name": Accessible,
            "description": "This data is accessible to anyone in the organization",
            "type": "ACCESSIBLE",
            "status": "ENABLED",
            "acknowledgement": false,
            "parentId": null,
            "referenceLink": null,
            "createdOn": "2022-07-15T19:07:55.552Z"
          }
        ],
        "status": "APPROVED",
        "id": "338a4dba-54f3-4a4b-b7d2-180c07dee68a",
        "usageContext": null,
        "costCenter": "ORG001-IQ",
        "customFields”: [
          {
            "name”: "com.infa.odin.models.custom.ca_6713129624402817413",
            "value”: "2023-04-19",
            "values”: null
          },
          {
            "name”: "com.infa.odin.models.custom.ca_4689891014995490684",
            "value”: null,
            "values”: ["Ferrari","Koenigsegg","Lamborghini"]
          },
          {
            "name”: "com.infa.odin.models.custom.ca_1100233073247017453",
            "value”: "Automobiles",
            "values”: null
          },
          {
            "name”: "com.infa.odin.models.custom.ca_6863327120603592729",
            "value”: "Luxury Ride Services",
            "values”: null
          },
          {
            "name”: "com.infa.odin.models.custom.ca_5660854643729475562",
            "value”: "true",
            "values”: null
          },
          {
            "name”: "com.infa.odin.models.custom.ca_1844904614209918604",
            "value”: "European Manufacturers",
            "values”: null
          },
          {
            "name”: "com.infa.odin.models.custom.ca_1590048468319648867",

            "value”: "10",
            "values”: null
          },
          {
            "name”: "com.infa.odin.models.custom.ca_62634611096284755",
            "value”: "12.5",
            "values”: null
          },
          {
            "name”: "com.infa.odin.models.custom.ca_5429811987158518158",
            "value”: "false",
            "values”: null
          },
          {
            "name”: "com.infa.odin.models.custom.ca_6366370263733010093",
            "value”: "Cars",
            "values”: null
          }
        ],
        "createdBy": {
          "displayName": "John Doe",
          "id": "f400dUqjPuaiJ9KBrzOhiB",
          "name": "John Doe",
          "email": "jdoe@informatica.com",
          "phone": "00000099999999999",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": "true"
        },
        "createdOn": "2022-07-16T12:07:54.597Z",
        "modifiedBy": {
          "displayName": "John Doe",
          "id": "f400dUqjPuaiJ9KBrzOhiB",
          "name": "John Doe",
          "email": "jdoe@informatica.com",
          "phone": "00000099999999999",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": "true"
        },
        "modifiedOn": "2022-07-16T12:08:03.463Z"
      }
    ]
  }
```

The following table describes the parameters of each order that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of orders retrieved.|
|`justification`|The reason entered at the time of order by the Data User to the<br>stakeholder for the requirement of access to the data.|
|`deliveryRequests`|Delivery information of the order.|


|**Parameter**|**Description**|
|---|---|
|`requestedProvisionedTargetRef`|The delivery target requested by the Data User.<br>**Note:**If the value of the` requestedProvisionedTargetRef`<br>parameter is` null`, the order is created without a specific<br>delivery target but rather uses the default delivery template.<br>For more information about how you can retrieve the details of<br>the default delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`refId`|Reference identifier of the order.|
|`createdBy`|Details of the user account that created the order.|
|`createdOn`|Date when the order was created.|
|`modifiedBy`|Details of the latest user account that modified the order.|
|`modifiedOn`|Date when the order was modified.|
|`dataCollections > id`|System generated unique identifier of the ordered data<br>collection.|
|`dataCollections > name`|Name of the ordered data collection.|
|`dataCollections > description`|Purpose of the ordered data collection.|
|`dataCollections > refId`|Reference identifier of the ordered data collection.|
|`dataCollections > category`|Details of the category that contains the ordered data<br>collection.|
|`dataCollections > usageContexts`|Details of the usage type that is used to specify the certified<br>use of the ordered data collection.|
|`dataCollections > dataCollectionStatus `|The status indicates whether the data collection is discoverable<br>by Data Users when they search for it. A data collection can<br>have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to Data<br>Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable to Data<br>Users.|
|`dataCollections > dataOwners`|Details of the stakeholders that are assigned the Data Owner<br>stakeholder role on the data collection.|
|`dataCollections > technicalOwners`|Details of the stakeholders that are assigned the Technical<br>Owner stakeholder role on the data collection.|
|`dataCollections > createdBy`|Details of the user account that created the ordered collection.|
|`dataCollections > createdOn`|Date when the ordered collection was created.|
|`dataCollections > modifiedBy`|Details of the latest user account that modified the ordered<br>collection.|
|`dataCollections > modifiedOn`|Latest date when the ordered data collection was modified.|

|**Parameter**|**Description**|
|---|---|
|`termsOfUse`|Details of the terms of use that apply to the ordered data<br>collection.|
|`termsOfUse > id`|System generated unique identifier of the terms of use<br>snapshot. The terms of use snapshot represents the state of the<br>terms of use of the data collection when the order was placed.|
|`termsOfUse > termsOfUseId`|System generated unique identifier of the terms of use that<br>apply to the ordered data collection.|
|`termsOfUse > termsOfUseRefId`|Reference identifier of the terms of use that apply to the<br>ordered data collection.|
|`termsOfUse > generalTermsOfUse`|Details of the general terms of use that apply to the ordered<br>data collection.|
|`termsOfUse > name`|Name of the terms of use that apply to the ordered data<br>collection.|
|`termsOfUse > description`|Description of the terms of use that apply to the ordered data<br>collection.|
|`termsOfUse > type`|Type of the terms of use that apply to the ordered data<br>collection.<br>Your administrator defines the terms of use types in Metadata<br>Command Center. For more information about terms of use, see<br>the_Terms of use_topic in the_Set Up Data Marketplace_help.|
|`termsOfUse > status `|The status indicates whether the terms of use are available to<br>be added to data collections. A terms of use can have one of<br>the following statuses:<br>-<br>` ENABLED `. The terms of use are available to be added to data<br>collections.<br>-<br>` DISABLED`. The terms of use isn't available to be added to<br>data collections.|
|`termsOfUse > acknowledgement `|Parameter that indicates whether the Data User is required to<br>acknowledge the terms of use when they place an order. The<br>parameter can be one of the following values:<br>-<br>` true `. The Data User must acknowledge the terms of use<br>when they place an order.<br>-<br>` false`. The Data User doesn't need to acknowledge the terms<br>of use when they place an order.|
|`termsOfUse > referenceLink`|Uniform resource identifier of the terms of use.|
|`termsOfUse > createdOn`|Date when the terms of use that apply to the ordered data<br>collection were created.|


|**Parameter**|**Description**|
|---|---|
|`status `|Status of the order. An order can have one of the following<br>statuses:<br>-<br>` APPROVED `. The order is approved for fulfillment. A<br>stakeholder of the ordered data collection must deliver the<br>data to the Data User.<br>-<br>` REJECTED `. The order was rejected by a stakeholder of the<br>ordered data collection.<br>-<br>` COMPLETE `. The ordered data was delivered to the Data User.<br>-<br>` CANCELLED`. The order was cancelled.|
|`id`|System generated unique identifier of the order.|
|`usageContext`|Details of the usage context that the Data User selected at the<br>time of order.|
|`costCenter`|Cost center of the Data User that placed the order.|
|`customFields`|The custom fields of the order and their values.<br>Custom attributes are additional properties for Data<br>Marketplace items that are defined by your administrator in<br>Metadata Command Center.<br>For more information about custom attributes, see the_Create_<br>_custom attributes for items_topic in the_Set Up Data Marketplace_<br>help.|
|`customFields > name`|System generated unique identifier of the custom attribute.<br>For more information about how you can retrieve the custom<br>attribute name as it appears on the Data Marketplace interface,<br>see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value. This parameter can have only a<br>single value.|
|`customFields > values`|The custom attribute value. This parameter can have multiple<br>values.|


#### Approve orders
Use REST APIs to approve orders in Data Marketplace.

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v1/integration/orders/<orderId>/approve** `<orderId>` : Required. Enter the system generated unique identifier of the order that you want to approve. For more information about how you can use an API to get the system generated unique identifier of a order, see “Retrieve orders” on page 268. To get the system generated unique identifier of a order from the Data Marketplace interface, open the order. The order page's URL contains the system generated unique identifier. For example, in the URL `https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848cb6821fc33f74?dtn=Order~579b ` , the system generated unique identifier is ` 3d48daf6-5e75-4e1a-848c-b6821fc33f74` . Method PUT 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`comment`|Optional. Enter a comment<br>about the action taken.|-|
|`costCenter`|Optional. Enter a cost center<br>value.|You can use this parameter to modify the cost<br>center that the Data User entered at the time of<br>order.|


The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`customFields`|The header where you can<br>modify the custom attributes<br>of the order.|You can use this parameter to modify the<br>additional information that the Data User entered<br>at the time of order.<br>Whether you must enter a value or not enter a<br>value in a custom attribute is determined by how<br>the custom attribute was defined by your<br>administrator in Metadata Command Center.<br>Custom attributes are additional properties for<br>Data Marketplace items that are defined by your<br>administrator in Metadata Command Center. For<br>more information about custom attribute, see<br>the_Create custom attributes for items_topic in<br>the_Set Up Data Marketplace_help.|
|`customFields > name`|Enter the system generated<br>unique identifier of the custom<br>attribute that you want to<br>modify.|For more information about how you can retrieve<br>the system generated unique identifier of a<br>custom attribute, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value.|This parameter can have only a single value.|
|`customFields > values`|The custom attribute value.|This parameter can have multiple values.|


#### Example request
The following example shows how you can use an API to approve an order: 

#### **Request query**
```
https://{{CDMP_URL}}/api/v1/integration/orders/16e32a13-6fba-4f1a-a337-e0aeecf9fab4/
approve
```

#### **Request body**
```
{
  "customFields": [
    {
      "name": "com.infa.odin.models.custom.ca_6713129624402817413",
      "value": "2023-04-19"
    },
    {
      "name": "com.infa.odin.models.custom.ca_1590048468319648867",
      "value": 10
    },
    {
      "name": "com.infa.odin.models.custom.ca_5660854643729475562",
      "value": true
    },
    {
      "name": "com.infa.odin.models.custom.ca_62634611096284755",
      "value": 12.5
    },
    {
      "name": "com.infa.odin.models.custom.ca_4689891014995490684",
      "values": ["Ferrari","Koenigsegg","Lamborghini"]
    },
    {
      "name": "com.infa.odin.models.custom.ca_5429811987158518158",
      "value": false

    },
    {
      "name": "com.infa.odin.models.custom.ca_1100233073247017453",
      "value": "Automobiles"
    },
    {
      "name": "com.infa.odin.models.custom.ca_1844904614209918604",
      "value": "European Manufacturers"
    },
    {
      "name": "com.infa.odin.models.custom.ca_6863327120603592729",
      "value": "Luxury Ride Services"
    }
  ]
}
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to approve an order: 

```
{
    "justification": "Justification",
    "deliveryRequests": "This order is to be approved from CAI",
    "requestedProvisionedTargetRef": “d02e28ea-6686-4954-85c7-d60c33e4e9c5”,
    "refId": "ORD-693",
    "status": "APPROVED",
    "id": "16e32a13-6fba-4f1a-a337-e0aeecf9fab4",
    "createdBy": {
      "displayName": "User1",
      "id": "6bX1o0lH3UmbPuF0OF5lkj",
      "name": "User1_name",
      "email": "ebc@xyz.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": "true"
    },
    "createdOn": "2022-07-21T17: 17: 16.901Z",
    "modifiedBy": {
      "displayName": "User1",
      "id": "6bX1o0lH3UmbPuF0OF5lkj",
      "name": "User1_name",
      "email": "ebc@xyz.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": "true"
    },
    "modifiedOn": "2022-07-22T07: 47: 23.384Z",
    "costCenter": "ORG001-IQ"
  }
```


The following table describes the parameters of each order that is approved: 

|**Parameter**|**Description**|
|---|---|
|`justification`|Business justification of the action taken on the order.|
|`deliveryRequests`|Delivery information of the order.|
|`requestedProvisionedTargetRef`|The delivery target requested by the Data User.<br>**Note:**If the value of the` requestedProvisionedTargetRef ` parameter is<br>` null`, the order is created without a specific delivery target but rather uses<br>the default delivery template.<br>For more information about how you can retrieve the details of the default<br>delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`refId`|Reference identifier of the order.|
|`status `|Status of the order. An order can have one of the following statuses:<br>-<br>` APPROVED `. The order is approved for fulfillment. A stakeholder of the<br>ordered data collection must deliver the data to the Data User.<br>-<br>` REJECTED `. The order was rejected by a stakeholder of the ordered data<br>collection.<br>-<br>` COMPLETE `. The ordered data was delivered to the Data User.<br>-<br>` CANCELLED`. The order was cancelled.|
|`id`|System generated unique identifier of the order.|
|`createdBy > displayName`|Name of the user that placed the order.|
|`createdBy > id`|Reference identifier of the order creator's user account.|
|`createdBy > name`|Username of the user that placed the order.|
|`createdBy > email`|Email address of the user that placed the order.|
|`createdBy > phone`|Contact number of the user that placed the order.|
|`createdBy > status `|The status indicates whether or not a user account or user group is active. A<br>user account or user group can have one of the following statuses:<br>-<br>` ACTIVE `. The user account or user group is active.<br>-<br>` INACTIVE`. The user account or user group is not active.|
|`createdBy > userInfo`|Details retrieved from the**My Data**page of the user account that placed the<br>order.|
|`createdBy > isGroup `|Parameter that indicates whether the user account is part of a group. This<br>parameter can have one of the following values:<br>-<br>` true `. The user account is part of a group<br>-<br>` false`. The user account isn't part of a group|
|`createdOn`|Date when the order was placed.|
|`modifiedBy > displayName`|Name of the last user that modified the order.|
|`modifiedBy > id`|Reference identifier of the user account of the last user that modified the<br>order.|
|`modifiedBy > name`|Username of the last user that modified the order.|

|**Parameter**|**Description**|
|---|---|
|`modifiedBy > email`|Email address of the last user that modified the order.|
|`modifiedBy > phone`|Contact number of the last user that modified the order.|
|`modifiedBy > status `|The status indicates whether or not a user account or user group is active. A<br>user account or user group can have one of the following statuses:<br>-<br>` ACTIVE `. The user account or user group is active.<br>-<br>` INACTIVE`. The user account or user group is not active.|
|`modifiedBy > userInfo`|Details retrieved from the**My Data**page of the user account that last<br>modified the order.|
|`modifiedBy > isGroup `|Parameter that indicates whether the user account is part of a group. This<br>parameter can have one of the following values:<br>-<br>` true `. The user account is part of a group<br>-<br>` false`. The user account isn't part of a group|
|`modifiedOn`|Latest date when the order was modified.|
|`costCenter`|The cost center of the Data User that placed the order.|


#### Reject orders
Use REST APIs to reject orders in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v1/integration/orders/<orderId>/reject** `<orderId>` : Required. Enter the system generated unique identifier of the order that you want to reject. For more information about how you can use an API to get the system generated unique identifier of a order, see “Retrieve orders” on page 268. To get the system generated unique identifier of a order from the Data Marketplace interface, open the order. The order page's URL contains the system generated unique identifier. For example, in the URL `https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848cb6821fc33f74?dtn=Order~579b ` , the system generated unique identifier is ` 3d48daf6-5e75-4e1a-848c-b6821fc33f74` . Method PUT 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to reject an order: 

```
https://{{CDMP_URL}}/api/v1/integration/orders/16e32a13-6fba-4f1a-a337-e0aeecf9fab4/
reject
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to reject an order: 

```
{
    "justification": "Justification",
    "deliveryRequests": "This order is to be cancelled from CAI",
    "requestedProvisionedTargetRef": “d02e28ea-6686-4954-85c7-d60c33e4e9c5”,
    "refId": "ORD-693",
    "status": "CANCELLED",
    "id": "ORD-693",
    "createdBy": {
      "displayName": "User1",
      "id": "6bX1o0lH3UmbPuF0OF5lkj",
      "name": "User1_name",
      "email": "ebc@xyz.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": "true"
    },
    "createdOn": "2022-07-21T17: 17: 16.901Z",
    "modifiedBy": {
      "displayName": "User1",
      "id": "6bX1o0lH3UmbPuF0OF5lkj",
      "name": "User1_name",
      "email": "ebc@xyz.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": "true"
    },
    "modifiedOn": "2022-07-22T07: 47: 23.384Z"
  }
```

The following table describes the parameters of each order that is rejected: 

|**Parameter**|**Description**|
|---|---|
|`justification`|Business justification of the action taken on the order.|
|`deliveryRequests`|Delivery information of the order.|
|`requestedProvisionedTargetRef`|The delivery target requested by the Data User.<br>**Note:**If the value of the` requestedProvisionedTargetRef ` parameter is<br>` null`, the order is created without a specific delivery target but rather uses<br>the default delivery template.<br>For more information about how you can retrieve the details of the default<br>delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`refId`|Reference identifier of the order.|
|`status `|Status of the order. An order can have one of the following statuses:<br>-<br>` APPROVED `. The order is approved for fulfillment. A stakeholder of the<br>ordered data collection must deliver the data to the Data User.<br>-<br>` REJECTED `. The order was rejected by a stakeholder of the ordered data<br>collection.<br>-<br>` COMPLETE `. The ordered data was delivered to the Data User.<br>-<br>` CANCELLED`. The order was cancelled.|
|`id`|System generated unique identifier of the order.|
|`createdBy > displayName`|Name of the user that placed the order.|
|`createdBy > id`|Reference identifier of the order creator's user account.|
|`createdBy > name`|Username of the user that placed the order.|
|`createdBy > email`|Email address of the user that placed the order.|
|`createdBy > phone`|Contact number of the user that placed the order.|
|`createdBy > status `|The status indicates whether or not a user account or user group is active. A<br>user account or user group can have one of the following statuses:<br>-<br>` ACTIVE `. The user account or user group is active.<br>-<br>` INACTIVE`. The user account or user group is not active.|
|`createdBy > userInfo`|Details retrieved from the**My Data**page of the user account that placed the<br>order.|
|`createdBy > isGroup `|Parameter that indicates whether the user account is part of a group. This<br>parameter can have one of the following values:<br>-<br>` true `. The user account is part of a group<br>-<br>` false`. The user account isn't part of a group|
|`createdOn`|Date when the order was placed.|
|`modifiedBy > displayName`|Name of the last user that modified the order.|
|`modifiedBy > id`|Reference identifier of the user account of the last user that modified the<br>order.|
|`modifiedBy > name`|Username of the last user that modified the order.|


|**Parameter**|**Description**|
|---|---|
|`modifiedBy > email`|Email address of the last user that modified the order.|
|`modifiedBy > phone`|Contact number of the last user that modified the order.|
|`modifiedBy > status `|The status indicates whether or not a user account or user group is active. A<br>user account or user group can have one of the following statuses:<br>-<br>` ACTIVE `. The user account or user group is active.<br>-<br>` INACTIVE`. The user account or user group is not active.|
|`modifiedBy > userInfo`|Details retrieved from the**My Data**page of the user account that last<br>modified the order.|
|`modifiedBy > isGroup `|Parameter that indicates whether the user account is part of a group. This<br>parameter can have one of the following values:<br>-<br>` true `. The user account is part of a group<br>-<br>` false`. The user account isn't part of a group|
|`modifiedOn`|Latest date when the order was modified.|


#### Fulfill orders
Use REST APIs to fulfill orders in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/orders/<orderId>/fulfill**|
||`<orderId>`: Required. Enter the system generated unique identifier of the order that you want to fulfill.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>order, see<br>“Retrieve orders” on page 268.|
||To get the system generated unique identifier of a order from the Data Marketplace interface, open the<br>order. The order page's URL contains the system generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848c-b6821fc33f74?dtn=Order~579b `, the system generated unique identifier is<br>` 3d48daf6-5e75-4e1a-848c-b6821fc33f74`.|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`deliveryTargetId`|Required. Enter the system<br>generated unique identifier of<br>the delivery target that you<br>want to use to deliver the data.|For more information about how you can use an<br>API to get the unique identifier of a delivery<br>target, see<br>“Retrieve delivery targets” on page 241.|
|`comment`|Optional. Enter a comment<br>about the action taken.|-|
|`costCenter`|Optional. Enter a cost center<br>value.|You can use this parameter to modify the cost<br>center that the Data User entered at the time of<br>order.|


The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`customFields`|The header where you can<br>modify the custom attributes<br>of the order.|You can use this parameter to modify the<br>additional information that the Data User entered<br>at the time of order.<br>Whether you must enter a value or not enter a<br>value in a custom attribute is determined by how<br>the custom attribute was defined by your<br>Administrator in Metadata Command Center.<br>Custom attributes are additional properties for<br>Data Marketplace items that are defined by your<br>administrator in Metadata Command Center. For<br>more information about custom attributes, see<br>the_Create custom attributes for items_topic in<br>the_Set Up Data Marketplace_help.|
|`customFields > name`|Enter the system generated<br>unique identifier of the custom<br>attribute that you want to<br>modify.|For more information about how you can retrieve<br>the system generated unique identifier of a<br>custom attribute, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value.|This parameter can have only a single value.|
|`customFields > values`|The custom attribute value.|This parameter can have multiple values.|


#### Example request
The following example shows how you can use an API to fulfill an order: 

#### **Request query**
```
https://{{CDMP_URL}}/api/v1/integration/orders/16e32a13-6fba-4f1a-a337-e0aeecf9fab4/
fulfill?deliveryTargetId=16e32a13-6fba-4f1a-a337-e0aeecf9fab4
```

#### **Request body**
```
{

  "customFields": [
```


```
    {
      "name": "com.infa.odin.models.custom.ca_6713129624402817413",
      "value": "2023-04-19"
    },
    {
      "name": "com.infa.odin.models.custom.ca_1590048468319648867",
      "value": 10
    },
    {
      "name": "com.infa.odin.models.custom.ca_5660854643729475562",
      "value": true
    },
    {
      "name": "com.infa.odin.models.custom.ca_62634611096284755",
      "value": 12.5
    },
    {
      "name": "com.infa.odin.models.custom.ca_4689891014995490684",
      "values": ["Ferrari","Koenigsegg","Lamborghini"]
    },
    {
      "name": "com.infa.odin.models.custom.ca_5429811987158518158",
      "value": false
    },
    {
      "name": "com.infa.odin.models.custom.ca_1100233073247017453",
      "value": "Automobiles"
    },
    {
      "name": "com.infa.odin.models.custom.ca_1844904614209918604",
      "value": "European Manufacturers"
    },
    {
      "name": "com.infa.odin.models.custom.ca_6863327120603592729",
      "value": "Luxury Ride Services"
    }
  ]
}
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to fulfill an order: 

```
{
  "Result": {
    "id": "8002f8f4-99ae-4389-8588-45442fe49969",
    "refId": "ACC-756",
    "status": "AVAILABLE",
    "accessGrantedOn": "2022-07-22T08:37:41.415Z",
    "order": {
      "justification": "Sales analytics",
      "deliveryRequests": "This order is to be fulfilled from CAI",
      "requestedProvisionedTargetRef": "d02e28ea-6686-4954-85c7-d60c33e4e9c5",
      "refId": "ORD-693",
      "dataCollections": [
        {
          "id": "e40b023f-4064-4974-9705-4d510bd14b26",
          "name": "Collection - Order",
          "description": "Collection to place order on",
          "refId": "DCL-341",
          "category": {
            "id": "e979b800-91ec-4e2d-8271-53e289935d78",
            "refId": "CAT-736",
            "parentId": null,
            "parentRefId": null,
            "parent": null,

            "name": "Category with collections under it",
            "description": " Category with collections under it ",
            "status": "ACTIVE",
            "effectiveStatus": "ACTIVE",
            "hasChildCategory": false,
            "hasActiveChildCategory": false,
            "dataOwners": [],
            "createdBy": {
              "displayName": "John Doe",
              "id": "f400dUqjPuaiJ9KBrzOhiB",
              "name": "John Doe",
              "email": "jdoe@informatica.com",
              "phone": "00000099999999999",
              "status": "ACTIVE",
              "userInfo": {},
              "isGroup": "true"
            },
            "createdOn": "2022-07-18T11:05:08.794Z",
            "modifiedBy": {
              "displayName": "John Doe",
              "id": "f400dUqjPuaiJ9KBrzOhiB",
              "name": "John Doe",
              "email": "jdoe@informatica.com",
              "phone": "00000099999999999",
              "status": "ACTIVE",
              "userInfo": {},
              "isGroup": "true"
            },
            "modifiedOn": "2022-07-18T11:05:08.794Z"
          },
          "usageContexts": [],
          "dataset": [],
          "dataCollectionStatus": "PUBLISHED",
          "dataOwners": [],
          "technicalOwners": [],
          "createdBy": {
            "displayName": "John Doe",
            "id": "f400dUqjPuaiJ9KBrzOhiB",
            "name": "John Doe",
            "email": "jdoe@informatica.com",
            "phone": "00000099999999999",
            "status": "ACTIVE",
            "userInfo": {},
            "isGroup": "true"
            },
          "createdOn": "2022-07-18T11:05:12.935Z",
          "modifiedBy": {
            "displayName": "John Doe",
            "id": "f400dUqjPuaiJ9KBrzOhiB",
            "name": "John Doe",
            "email": "jdoe@informatica.com",
            "phone": "00000099999999999",
            "status": "ACTIVE",
            "userInfo": {},
            "isGroup": "true"
            },
          "modifiedOn": "2022-07-18T11:05:12.935Z"
        }
      ],
      "termsOfUseSnapshots": [],
      "status": "COMPLETE",
      "inheritedGroupIds": [],
      "id": "0a7341db-b5b2-4673-a5fe-c7b731d1bd5c",
      "usageContext": {},
      "createdBy": {
        "displayName": "Johnny Doe",
        "id": "6bX1o0lH3UmbPuF0OF5lkj",
        "name": "15Jul_CDMP_QE",
        "email": "jydoe@informatica.com",
        "phone": "555-243-456",
```


```
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": "true"
        },
      },
      "createdOn": "2022-07-22T08:34:30.288Z",
      "modifiedBy": {
        "displayName": "Johnny Doe",
        "id": "6bX1o0lH3UmbPuF0OF5lkj",
        "name": "15Jul_CDMP_QE",
        "email": "jydoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE"
        "userInfo": {},
        "isGroup": "true"
      },
      "modifiedOn": "2022-07-22T08:37:46.974Z",
      "costCenter": "ORG001-IQ"
    },
    "deliveryTarget": {
      "id": "d02e28ea-6686-4954-85c7-d60c33e4e9c5",
      "name": "XLSX over IPFS",
      "description": "An excel workbook sent over IPFS.",
      "isDefault": false,
      "status": "ACTIVE",
      "physicalLocation": "AK",
      "targetSystemReference": "AK",
      "deliveryTemplate": {
        "id": "9bf52d89-7ade-479b-ad88-d657064e6301",
        "name": "XLSX over IPFS",
        "description": "An excel workbook sent over IPFS.",
        "isDefault": false,
        "status": "ACTIVE",
        "managedAccess": "DISABLED",
        "deliveryType": "MANUAL",
        "templateOwners": [],
        "targetSystemReference": "AK",
        "defaultPhysicalLocation": "AK",
        "deliveryMethods": null,
        "deliveryFormats": null,
        "createdBy": "6bX1o0lH3UmbPuF0OF5lkj",
        "createdOn": "2022-07-19T12:40:50.855Z",
        "modifiedBy": "6bX1o0lH3UmbPuF0OF5lkj",
        "modifiedOn": "2022-07-19T17:42:55.341Z"
      },
      "deliveryMethod": {
        "id": "f8b34c4f-bd69-4dd6-9d41-221075444abf",
        "name": "AK",
        "status": "ACTIVE",
        "createdBy": "6bX1o0lH3UmbPuF0OF5lkj",
        "createdOn": "2022-07-19T12:40:24.123Z",
        "modifiedBy": "6bX1o0lH3UmbPuF0OF5lkj",
        "modifiedOn": "2022-07-19T12:40:31.332Z"
      },
      "deliveryFormat": {
        "id": "c6fbbaa4-68f4-4005-a1fb-80bb34ec3707",
        "name": "AK",
        "status": "ACTIVE",
        "createdBy": "6bX1o0lH3UmbPuF0OF5lkj",
        "createdOn": "2022-07-19T12:39:44.926Z",
        "modifiedBy": "6bX1o0lH3UmbPuF0OF5lkj",
        "modifiedOn": "2022-07-19T12:39:52.834Z"
      },
      "dataCollection": {
        "id": "e40b023f-4064-4974-9705-4d510bd14b26",
        "name": "123456789021658142310877",
        "description": "CollectionDescSkearch221658142310875",
        "refId": "DCL-341",
        "category": {},
        "usageContexts": [],

        "dataset": [],
        "dataCollectionStatus": "PUBLISHED",
        "dataOwners": [],
        "technicalOwners": [],
        "createdBy": {
          "displayName": "John Doe",
          "id": "f400dUqjPuaiJ9KBrzOhiB",
          "name": "John Doe",
          "email": "jdoe@informatica.com",
          "phone": "00000099999999999",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": "true"
        },
        "createdOn": "2022-07-18T11:05:12.935Z",
        "modifiedBy": {
          "displayName": "John Doe",
          "id": "f400dUqjPuaiJ9KBrzOhiB",
          "name": "John Doe",
          "email": "jdoe@informatica.com",
          "phone": "00000099999999999",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": "true"
        },
        "modifiedOn": "2022-07-18T11:05:12.935Z"
      },
      "createdBy": "6bX1o0lH3UmbPuF0OF5lkj",
      "createdOn": "2022-07-22T08:34:08.916Z",
      "modifiedBy": "6bX1o0lH3UmbPuF0OF5lkj",
      "modifiedOn": "2022-07-22T08:34:08.916Z"
    },
    "dataCollection": {
      "id": "e40b023f-4064-4974-9705-4d510bd14b26",
      "name": "123456789021658142310877",
      "description": "CollectionDescSkearch221658142310875",
      "refId": "DCL-341",
      "category": {
        "id": "e979b800-91ec-4e2d-8271-53e289935d78",
        "refId": "CAT-736",
        "parentId": null,
        "parentRefId": null,
        "parent": null,
        "name": "CategorySkearchName221658142307670",
        "description": "CategorySkearchDesc221658142307671",
        "status": "ACTIVE",
        "effectiveStatus": "ACTIVE",
        "hasChildCategory": false,
        "hasActiveChildCategory": false,
        "dataOwners": [],
        "createdBy": {
          "displayName": "John Doe",
          "id": "f400dUqjPuaiJ9KBrzOhiB",
          "name": "John Doe",
          "email": "jdoe@informatica.com",
          "phone": "00000099999999999",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": "true"
        },
        "createdOn": "2022-07-18T11:05:08.794Z",
        "modifiedBy": {
          "displayName": "John Doe",
          "id": "f400dUqjPuaiJ9KBrzOhiB",
          "name": "John Doe",
          "email": "jdoe@informatica.com",
          "phone": "00000099999999999",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": "true"
```


```
        },
        "modifiedOn": "2022-07-18T11:05:08.794Z"
      },
      "usageContexts": [],
      "dataset": [],
      "dataCollectionStatus": "PUBLISHED",
      "dataOwners": [],
      "technicalOwners": [],
      "createdBy": {
        "displayName": "John Doe",
        "id": "f400dUqjPuaiJ9KBrzOhiB",
        "name": "John Doe",
        "email": "jdoe@informatica.com",
        "phone": "00000099999999999",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": "true"
      },
      "createdOn": "2022-07-18T11:05:12.935Z",
      "modifiedBy": {
        "displayName": "John Doe",
        "id": "f400dUqjPuaiJ9KBrzOhiB",
        "name": "John Doe",
        "email": "jdoe@informatica.com",
        "phone": "00000099999999999",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": "true"
        },
      "modifiedOn": "2022-07-18T11:05:12.935Z"
    },
    "termsOfUseSnapshots": [
      {
        "id": "26c594c2-4b78-4e6b-b171-22364b4eadc8",
        "termsOfUseId": null,
        "termsOfUseRefId": null,
        "generalTermsOfUse": "{"tosMessage\":"General TOU Order Submission"}",
        "name": Accessible,
        "description": "This data is available to everyone in the organization",
        "type": "ACCESSIBLE",
        "status": "ENABLED",
        "acknowledgement": false,
        "parentId": null,
        "referenceLink": null,
        "createdOn": "2022-07-21T14:44:05.962Z"
      }
    ],
    "consumer": {
      "displayName": "Johnny Doe",
      "id": "6bX1o0lH3UmbPuF0OF5lkj",
      "name": "15Jul_CDMP_QE",
      "email": "jydoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": "true"
      },
    "usageContext": {
    "id": "7dead27e-9d00-4a05-b286-70bef9c91402",
    "name": "uc",
    "description": null,
    "status": "ACTIVE"
    },
    "createdBy": {
      "displayName": "User1",
      "id": "6bX1o0lH3UmbPuF0OF5lkj",
      "name": "User1_name",
      "email": "ebc@xyz.com",
      "phone": "555-243-456",
      "status": "ACTIVE",

      "userInfo": {},
      "isGroup": "true"
      },
    "createdOn": "2022-07-22T08:37:41.764Z",
    "modifiedBy": {
      "displayName": "User1",
      "id": "6bX1o0lH3UmbPuF0OF5lkj",
      "name": "User1_name",
      "email": "ebc@xyz.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": "true"
      },
    "modifiedOn": "2022-07-22T08:37:41.764Z",
    "customFields": [
        {
          "name": "Year",
          "value": "2021",
          "values": null
       }
  }
}
```

The following table describes the parameters of each order that is fulfilled: 

|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the consumer access that<br>is created after the order is fulfilled.|
|`refId`|Referenced identifier of the consumer access that is created<br>after the order is fulfilled.|
|`status `|Status of the consumer access that is created after the order is<br>fulfilled.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to withdraw the<br>Data User's access to the data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data collection is<br>withdrawn.<br>**Note:**For a consumer access that is created after an order is<br>fulfilled, the status is always` AVAILABLE`.|
|`accessGrantedOn`|Date when the order was fulfilled.|
|`order > justification`|The reason entered at the time of order by the Data User to the<br>stakeholder for the requirement of access to the data.|
|`order > deliveryRequests`|Delivery information of the order.|
|`order > requestedProvisionedTargetRef`|The delivery target requested by the Data User.<br>**Note:**If the value of the` requestedProvisionedTargetRef`<br>parameter is` null`, the order is created without a specific<br>delivery target but rather uses the default delivery template.<br>For more information about how you can retrieve the details of<br>the default delivery template, see<br>“Retrieve delivery templates” on page 152.|


|**Parame**|**ter**|**Description**|
|---|---|---|
|`order`|`> termsOfUseSnapshots`|Details of the terms of use snapshot that represents the state of<br>the terms of use of the data collection when the order was<br>placed.|
|`order`|`> status `|Status of the order. An order can have one of the following<br>statuses:<br>-<br>` APPROVED `. The order is approved for fulfillment. A<br>stakeholder of the ordered data collection must deliver the<br>data to the Data User.<br>-<br>` REJECTED `. The order was rejected by a stakeholder of the<br>ordered data collection.<br>-<br>` COMPLETE `. The ordered data was delivered to the Data User.<br>-<br>` CANCELLED`. The order was cancelled.|
|`order`|`> inheritedGroupIds`|System generated unique identifier of the stakeholders of the<br>ordered data collection.|
|`order`|`> id`|System generated unique identifier of the order.|
|`order`|`> usageContext`|Details of the usage context that the Data User selected at the<br>time of order.|
|`order`|`> createdBy`|Details of the user account that created the order.|
|`order`|`> createdOn`|Date when the order was created.|
|`order`|`> modifiedBy`|Details of the latest user account that modified the order.|
|`order`|`> modifiedOn`|Date when the order was modified.|
|`order`|`> refId`|Reference identifier of the order.|
|`order`|`> dataCollections > id`|System generated unique identifier of the data collection.|
|`order`|`> dataCollections > name`|Name of the data collection.|
|`order`|`> dataCollections > description`|Purpose of the data collection.|
|`order`|`> dataCollections > refId`|Reference identifier of the data collection.|
|`order`|`> dataCollections > category`|Details of the category of the data collection.|
|`order`|`> dataCollections > usageContexts`|Details of the usage type that is used to specify the certified<br>use of the data collection.|
|`order`|`> dataCollections > dataset`|Details of the data assets that the data collection contains.|
|`order `<br>` dataCo`|`> dataCollections >`<br>`llectionStatus `|The status indicates whether the data collection is discoverable<br>by Data Users when they search for it. A data collection can<br>have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to Data<br>Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable to Data<br>Users.|

|**Parameter**|**Description**|
|---|---|
|`order > dataCollections > dataOwners`|Details of the stakeholders that are assigned the Data Owner<br>stakeholder role on the data collection.|
|`order > dataCollections >`<br>`technicalOwners`|Details of the stakeholders that are assigned the Technical<br>Owner stakeholder role on the data collection.|
|`order > dataCollections > createdBy`|Details of the user account that created the data collection.|
|`order > dataCollections > createdOn`|Date when the data collection was created.|
|`order > dataCollections > modifiedBy`|Details of the latest user account that modified the data<br>collection.|
|`order > dataCollections > modifiedOn`|Latest date when the data collection was modified.|
|`deliveryTarget`|Details of the delivery target that was used to deliver the data to<br>the Data User.|
|`deliveryTarget > id`|System generated unique identifier of the delivery target.|
|`deliveryTarget > name`|Name of the delivery target.|
|`deliveryTarget > description`|Description of the delivery target.|
|`deliveryTarget > isDefault `|Parameter that indicates whether the delivery target is the<br>default delivery option for a data collection. The parameter can<br>have one of the following values:<br>-<br>` true `. The delivery template is the default delivery option for<br>a data collection.<br>-<br>` false`. The delivery template isn't the default delivery option<br>for a data collection.|
|`deliveryTarget > status `|The status indicates whether the delivery target is available for<br>use to the Data Users that order the data collection. A delivery<br>target can have one of the following statuses:<br>-<br>` ACTIVE `. The delivery target is available.<br>-<br>` INACTIVE`. The delivery target is unavailable.|
|`deliveryTarget > physicalLocation`|Default location where the data is delivered.<br>**Note:**If the value of the` managedAccess ` parameter is<br>` ENABLED`, the data is not be delivered to the location that is<br>specified in the` physicalLocation` parameter. Instead, the<br>data will be delivered to a location that is generated at the time<br>of order fulfillment. The generated location is unique to each<br>order that is fulfilled using this target.<br>For more information, see the_Integrate Data Marketplace with_<br>_other Informatica services_topic in the_Set Up Data Marketplace_<br>help.|
|`deliveryTarget > targetSystemReference`|Default system where the data is delivered.|
|`deliveryTarget > deliveryTemplate`|Details of the delivery template based on which the delivery<br>target was created.|
|`deliveryTarget > deliveryMethod`|Details of the delivery method.|


|**Parameter**|**Description**|
|---|---|
|`deliveryTarget > deliveryFormat`|Details of the delivery format.|
|`deliveryTarget > dataCollection`|Details of the data collection to which the delivery target is<br>assigned.|
|`deliveryTarget > createdBy`|Details of the user account that created the delivery target.|
|`deliveryTarget > createdOn`|Date when the delivery target was created.|
|`deliveryTarget > modifiedBy`|Details of the latest user account that modified the delivery<br>target.|
|`deliveryTarget > modifiedOn`|Latest date when the delivery target was modified.|
|`costCenter`|Cost center of the Data User that was granted access to the<br>data collection.|
|`consumer`|Details of the Data User that was granted access to the data<br>collection.|
|`usageContext`|Details of the usage context that the Data User selected at the<br>time of order.|
|`createdBy`|Details of the user account that fulfilled the order that is<br>associated with the consumer access.|
|`createdOn`|Date when the order that is associated with the consumer<br>access was fulfilled.|
|`modifiedBy`|Details of the latest user account that modified the consumer<br>access.|
|`modifiedOn`|Latest date when the consumer access was modified.|
|`customFields`|The custom fields of the consumer access and their values.<br>Custom attributes are additional properties for Data<br>Marketplace items that are defined by your administrator in<br>Metadata Command Center.<br>For more information about custom attributes, see the_Create_<br>_custom attributes for items_topic in the_Set Up Data Marketplace_<br>help.|
|`customFields > name`|The system generated unique identifier of the custom attribute.<br>For more information about how you can retrieve the custom<br>attribute name as it appears on the Data Marketplace interface,<br>see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value. This parameter can have only a<br>single value.|
|`customFields > values`|The custom attribute value. This parameter can have multiple<br>values.|

#### Cancel orders
Use REST APIs to cancel orders in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/orders/<orderId>/cancel**|
||`<orderId>`: Required. Enter the system generated identifier of the order that you want to cancel.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>order, see<br>“Retrieve orders” on page 268.|
||To get the system generated unique identifier of a order from the Data Marketplace interface, open the<br>order. The order page's URL contains the system generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848c-b6821fc33f74?dtn=Order~579b `, the system generated unique identifier is<br>` 3d48daf6-5e75-4e1a-848c-b6821fc33f74`.|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to cancel an order: 

```
https://{{CDMP_URL}}/api/v1/integration/orders/16e32a13-6fba-4f1a-a337-e0aeecf9fab4/
cancel
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to cancel an order: 

- `{ "justification": "Standard sales team process.",` 

- `"deliveryRequests": "1",` 

- `"requestedProvisionedTargetRef": "AWS", "refId": "947ac3d1-662e-4d3a-a421-12ee38606539-180469874bc",` 

- `"status": "CANCELLED",` 

- `"id": "ORD-693",` 


```
  "createdBy": {
    "displayName": "John Doe",
    "id": "User1",
    "name": "username1",
    "email": "abc@xyz.com",
    "phone": "2020202023",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  }
  "createdOn": "2022-03-20T10:50:39.251Z",
  "modifiedBy": {
    "displayName": "Jane Doe",
    "id": "User2",
    "name": "username2",
    "email": "xyz@abc.com",
    "phone": "2020202021",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  }
  "modifiedOn": "2022-04-20T10:50:39.251Z"
}
```

The following table describes the parameters of each order that is cancelled: 

|**Parameter**|**Description**|
|---|---|
|`justification`|The reason entered at the time of order by the Data User to the stakeholder<br>for the requirement of access to the data.|
|`deliveryRequests`|Delivery information of the order.|
|`requestedProvisionedTargetRef`|The delivery target requested by the Data User.<br>**Note:**If the value of the` requestedProvisionedTargetRef ` parameter is<br>` null`, the order is created without a specific delivery target but rather uses<br>the default delivery template.<br>For more information about how you can retrieve the details of the default<br>delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`refId`|Reference identifier of the order.|
|`status `|Status of the order. An order can have one of the following statuses:<br>-<br>` APPROVED `. The order is approved for fulfillment. A stakeholder of the<br>ordered data collection must deliver the data to the Data User.<br>-<br>` REJECTED `. The order was rejected by a stakeholder of the ordered data<br>collection.<br>-<br>` COMPLETE `. The ordered data was delivered to the Data User.<br>-<br>` CANCELLED`. The order was cancelled.|
|`id`|System generated unique identifier of the order.|
|`createdBy > displayName`|Name of the user that placed the order.|
|`createdBy > id`|Reference identifier of the order creator's user account.|
|`createdBy > name`|Username of the user that placed the order.|
|`createdBy > email`|Email address of the user that placed the order.|
|`createdBy > phone`|Contact number of the user that placed the order.|

|**Parameter**|**Description**|
|---|---|
|`createdBy > status `|The status indicates whether or not a user account or user group is active. A<br>user account or user group can have one of the following statuses:<br>-<br>` ACTIVE `. The user account or user group is active.<br>-<br>` INACTIVE`. The user account or user group is not active.|
|`createdBy > userInfo`|Details retrieved from the**My Data**page of the user account that placed the<br>order.|
|`createdBy > isGroup `|Parameter that indicates whether the user account is part of a group. This<br>parameter can have one of the following values:<br>-<br>` true `. The user account is part of a group<br>-<br>` false`. The user account isn't part of a group|
|`createdOn`|Date when the order was placed.|
|`modifiedBy > displayName`|Name of the last user that modified the order.|
|`modifiedBy > id`|Reference identifier of the user account of the last user that modified the<br>order.|
|`modifiedBy > name`|Username of the last user that modified the order.|
|`modifiedBy > email`|Email address of the last user that modified the order.|
|`modifiedBy > phone`|Contact number of the last user that modified the order.|
|`modifiedBy > status `|The status indicates whether or not a user account or user group is active. A<br>user account or user group can have one of the following statuses:<br>-<br>` ACTIVE `. The user account or user group is active.<br>-<br>` INACTIVE`. The user account or user group is not active.|
|`modifiedBy > userInfo`|Details retrieved from the**My Data**page of the user account that last<br>modified the order.|
|`modifiedBy > isGroup `|Parameter that indicates whether the user account is part of a group. This<br>parameter can have one of the following values:<br>-<br>` true `. The user account is part of a group<br>-<br>` false`. The user account isn't part of a group|
|`modifiedOn`|Latest date when the order was modified.|


#### Delete orders
If you placed an order accidentally or if an order is obsolete, you can use a REST API to delete the order. **Note:** To delete a fulfilled order, ensure that you have already deleted its associated consumer access. For more information about how you can delete a consumer access, see “Delete consumer accesses” on page 351. 


#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/orders/<orderId>**|
||`<orderId>`: Required. Enter the system generated unique identifier of the order that you want to delete.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>order, see<br>“Retrieve orders” on page 268.|
||To get the system generated unique identifier of a order from the Data Marketplace interface, open the<br>order. The order page's URL contains the system generated unique identifier.|
||For example, in the URL` https://{{CDMP_URL}}/order/3d48daf6-5e75-4e1a-848c-b6821fc33f74?dtn=Order~579b `, the system generated unique identifier is<br>` 3d48daf6-5e75-4e1a-848c-b6821fc33f74`.|
|Method|DELETE|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 

#### Example request
The following example shows how you can use an API to delete an order: 

```
https://{{CDMP_URL}}/api/v1/integration/orders/16e32a13-6fba-4f1a-a337-e0aeecf9fab4
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the order is deleted successfully: 

```
204 OK code
```

### Consumer accesses
A consumer access is a record that indicates a Data User's access to a data collection, along with other details such as the date of delivery, details of the stakeholder that delivered the data, and the delivery option that was used for the delivery. 

#### Create consumer accesses
Use a REST API to create a consumer access in Data Marketplace.

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/consumerAccess**|
|Method|POST|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`refId`|Optional. Enter a reference identifier<br>for the consumer access.|If you don't specify a reference identifier,<br>Data Marketplace automatically assigns a<br>unique value to the object. The reference<br>identifier that Data Marketplace<br>automatically generates contains a prefix.<br>The Administrator can specify the prefix<br>of the automatically generated reference<br>identifier in Metadata Command Center.<br>If you want to specify a reference<br>identifier, ensure that you enter a unique<br>value. Ensure that you don't use the prefix<br>value that is configured in Metadata<br>Command Center.|
|`status`|Optional. Specify a status for the<br>consumer access that you want to<br>create.|Enter one of the following values:<br>- For a consumer access where the Data<br>User has access to the data collection,<br>enter` AVAILABLE`.<br>- For a consumer access that is awaiting<br>withdrawal, enter` PENDING_WITHDRAW`.<br>- For a consumer access that is<br>withdrawn, enter` WITHDRAWN`.<br>Default value is` AVAILABLE`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`dataCollectionId`|Required. Enter the system generated<br>unique identifier of the data collection<br>to which the Data User was granted<br>access.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a data collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique<br>identifier of a data collection from the<br>Data Marketplace interface, open the data<br>collection. The data collection page's URL<br>contains the system generated unique<br>identifier.<br>For example, in the URL` https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?dtn=&tab=summary `, the<br>system generated unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`deliveryTargetId`|Required. Enter the system generated<br>unique identifier of the delivery target<br>that was used to deliver the data.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a delivery target, see<br>“Retrieve delivery targets” on page 241.|
|`consumerUserId`|Required. Enter the system generated<br>unique identifier of the user account<br>that was granted access to the data<br>collection.|To get the system generated unique<br>identifier of a user account, navigate to<br>**My Services > Administrator > Users**. On<br>the**Users**page, click a user account. The<br>user account page's URL contains the<br>system generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT `, the system<br>generated unique identifier is<br>` 0LH3xBJC9A6haZ26htGZyT`.|
|`accessGrantedOn`|Optional. Enter the date on which the<br>Data User was granted access to the<br>data collection.|To specify a date, use the` YYYY-MM-DD`<br>format. The value that you specify is<br>automatically converted and stored in the<br>Coordinated Universal Time (UTC) time<br>standard.|
|`usageContextId`|Optional. Enter the system generated<br>unique identifier of the usage type<br>used to specify the context in which<br>the data is to be used as provided by<br>the Data User that was granted<br>access to the collection.|For more information about how you can<br>use an API to get the system generated<br>unique identifier of a usage type, see<br>“Retrieve usage type” on page 174.|
|`costCenter`|Optional. Enter the cost center of the<br>Data User that was granted access to<br>the data collection.|-|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`customFields`|The header where you can modify the<br>custom attributes of the consumer<br>access.|Whether you must enter a value or not<br>enter a value in a custom attribute is<br>determined by how the custom attribute<br>was defined by your administrator in<br>Metadata Command Center.<br>Custom attributes are additional<br>properties for Data Marketplace items<br>that are defined by your administrator in<br>Metadata Command Center. For more<br>information about custom attributes, see<br>the_Create custom attributes for items_<br>topic in the_Set Up Data Marketplace_help.|
|`customFields > name`|Enter the system generated unique<br>identifier of the custom attribute that<br>you want to modify.|For more information about how you can<br>retrieve the system generated unique<br>identifier of a custom attribute, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|Enter a value in the custom attribute.|This parameter can have only a single<br>value.|
|`customFields > values`|Enter a value in the custom attribute.|This parameter can have multiple values.|


#### Example request
The following example shows how you can use an API to create consumer accesses: 

```
{
  "refId": "CAS710",
  "status": "WITHDRAWN",
  "dataCollectionId": "ed76133s-86vb-53rt-2914-677d73r4a7c5",
  "deliveryTargetId": "ad90417f-28bf-44ea-8204-127d70a4a7c9",
  "consumerUserId": "d00be828-d47c-4e9f-a65f-502ba6b633f9",
  "accessGrantedOn": "2022-09-15T17:04:29.382Z",
  "usageContextId": "8npzPxKim0XjHE4es2gkAc",
  "costCenter": "Retail-100IEQ"
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to create consumer accesses: 

```
{
  "processingTime": 489,
  "id": "407225ad-7870-4678-8169-43829d80c4bc",
  "refId": "CAS710"
}
```


The following table describes the parameters of each consumer access that is created: 

|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the consumer access that is created.|
|`refId`|Reference identifier of the consumer access that is created.|


#### Modify custom attributes of consumer accesses
Use a REST API to modify the custom attributes of a consumer access in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/consumerAccess/<consumerAccessId>/**<br>`<consumerAccessId>`: Required. Enter the system generated identifier of the consumer access for which<br>you want to modify custom attributes.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>consumer access, see<br>“Retrieve consumer accesses” on page 315.<br>To get the system generated unique identifier of a consumer access from the Data Marketplace interface,<br>open the consumer access. The consumer access page's URL contains the system generated unique<br>identifier.|
||For example, in the URL` https://{{CDMP_URL}}/access/e254491f-5795-49bd-be0b-385eb11d9d5a?dtn=Access~2e85 `, the system generated unique identifier is<br>` e254491f-5795-49bd-be0b-385eb11d9d5a`.|
|Method|PATCH|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`customFields`|The header where you can modify the<br>custom attributes of the consumer<br>access.|Whether you must enter a value or not<br>enter a value in a custom attribute is<br>determined by how the custom attribute<br>was defined by your administrator in<br>Metadata Command Center.<br>Custom attributes are additional<br>properties for Data Marketplace items<br>that are defined by your administrator in<br>Metadata Command Center. For more<br>information about custom attributes, see<br>the_Create custom attributes for items_<br>topic in the_Set Up Data Marketplace_help.|
|`customFields > name`|Enter the system generated unique<br>identifier of the custom attribute that<br>you want to modify.|For more information about how you can<br>retrieve the system generated unique<br>identifier of a custom attribute, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|Enter a value in the custom attribute.|This parameter can have only a single<br>value.|
|`customFields > values`|Enter a value in the custom attribute.|This parameter can have multiple values.|


#### Example request
The following example shows how you can use an API to modify the custom attributes of a consumer access: 

```
{
  "customFields": [
    {
      "name": "Year of sale",
      "value": "2023",
      "values": null
    }
  ]
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to modify the custom attributes of a consumer access: 

```
{
  "id": "794a215f-5479-4b55-8e1b-a4866ec9fe82",
  "refId": "ACC-246",
```

> `"status": "AVAILABLE",` 

> `"accessGrantedOn": "2022-09-14T13:30:36.405Z",` 

> `"costCenter": "Retail-IQO3456",` 

> `"order": {` 

> `"justification": "Required for the Q3 sales reporting.",` 

> `"deliveryRequests": null,` 


```
    "requestedProvisionedTargetRef": "b441a10a-d64d-4801-94d8-145e805001c4",
    "refId": "ORD-337",
    "dataCollections": null,
    "termsOfUseSnapshots": null,
    "status": "COMPLETE",
    "id": "b986ae59-7fc9-414c-98d7-14c7a1671023",
    "usageContext": null,
    "createdBy": {
      "displayName": "Johnny Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jydoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "createdOn": "2022-09-14T13:30:36.405Z",
    "modifiedBy": {
      "displayName": "Johnny Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jydoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "modifiedOn": "2022-09-14T13:30:36.405Z",
    "costCenter": "Retail-IQO3456"
  },
  "deliveryTarget": {
    "id": "b441a10a-d64d-4801-94d8-145e805001c4",
    "name": "IPFS Delivery",
    "description": "Use this template to deliver data via IPFS.",
    "isDefault": true,
    "status": "ACTIVE",
    "physicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
    "targetSystemReference": "IPFS",
    "deliveryTemplate": {
      "id": "1c865800-86ca-4280-8cdd-89dda66c013c",
      "name": "IPFS",
      "description": "Use this template to deliver data via IPFS.",
      "color": null,
      "isDefault": true,
      "status": "ACTIVE",
      "managedAccess": "DISABLED",
      "deliveryType": "AUTOMATIC",
      "templateOwners": [
        {
          "displayName": "Johnny Doe",
          "id": "6k7aaGYrSOsfN7RFlPH1YU",
          "name": "23Aug_CDMP_QE1",
          "email": "jydoe@informatica.com",
          "phone": "555-243-456",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": true
        }
      ],
      "targetSystemReference": "IPFS",
      "defaultPhysicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
      "deliveryMethods": null,
      "deliveryFormats": null,
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "deliveryMethod": {
      "id": "f8f8as4d-c0f0-3r11-fr04-7b7h2ce46731",

      "name": "IPFS",
      "status": "ACTIVE",
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "deliveryFormat": {
      "id": "e0f8af4d-c0f0-4c02-af03-5b9b2ce43253",
      "name": "CSV",
      "status": "Deliver data as a CSV file.",
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "dataCollection": {
      "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
      "name": "Retail Sales Report",
      "description": "Sales reports for the retail outlets.",
      "refId": "DCL-834",
      "category": null,
      "usageContexts": null,
      "dataset": null,
      "dataCollectionStatus": "PUBLISHED",
      "dataOwners": [],
      "technicalOwners": [],
      "createdBy": {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      },
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      },
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "createdBy": "8npzPxKim0XjHE4es2gkAc",
    "createdOn": "2022-09-14T11:57:00.446Z",
    "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
    "modifiedOn": "2022-09-14T11:57:00.446Z"
  },
  "dataCollection": {
    "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
    "name": "Retail Sales Report",
    "description": "Sales reports for the retail outlets.",
    "refId": "DCL-834",
    "category": null,
    "usageContexts": null,
    "dataset": null,
    "dataCollectionStatus": "PUBLISHED",
    "dataOwners": [
      {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
```


```
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      }
    ],
    "technicalOwners": [
      {
        "displayName": "Johnny Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jydoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      }
    ],
    "createdBy": {
      "displayName": "Jane Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jdoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "createdOn": "2022-09-14T11:57:00.446Z",
    "modifiedBy": {
      "displayName": "Jane Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jdoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "modifiedOn": "2022-09-14T11:57:00.446Z"
  },
  "termsOfUseSnapshots": [
    {
      "id": "e37bf0c7-faf3-4902-8bcc-485d80cc8bb9",
      "termsOfUseId": null,
      "termsOfUseRefId": null,
      "generalTermsOfUse": "This data must not be accessed via unauthorized devices.",
      "name": null,
      "description": null,
      "type": null,
      "status": null,
      "acknowledgement": null,
      "parentId": "null,
      "referenceLink": null,
      "createdOn": "2022-09-14T11:57:00.446Z"
    }
  ],
  "consumer": {
    "displayName": "John Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "usageContext": {
    "id": "9669f6ab-affc-4106-a583-48a6b580b930",
    "name": "Sales Use",
    "description": "This is for sales reporting tasks.",
    "color": null,

    "status": "ACTIVE"
  },
  "createdBy": {
    "displayName": "Jane Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "createdOn": "2022-09-14T13:30:36.726Z",
  "modifiedBy": {
    "displayName": "Jane Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "modifiedOn": "2022-09-15T13:59:40.472Z",
      "customFields": [
        {
          "name": "Year",
          "value": "2021",
          "values": null
       }
}
```

The following table describes the parameters in the response body of each consumer access that is modified: 

|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the consumer<br>access.|
|`refId`|Reference identifier of the consumer access.|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data<br>collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to<br>withdraw the Data User's access to the data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data<br>collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the<br>data collection.|
|`costCenter`|Cost center of the Data User that was granted access to<br>the data collection.|
|`order`|Details of the order placed by the Data User that was<br>granted access to the data collection.|
|`order > justification`|The reason entered at the time of order by the Data User<br>to the stakeholder for the requirement of access to the<br>data.|


|**Parameter**|**Description**|
|---|---|
|`order > deliveryRequests`|Delivery information of the order.|
|`order > requestedProvisionedTargetRef `|The delivery target requested by the Data User.<br>**Note:**If the value of the<br>` requestedProvisionedTargetRef ` parameter is<br>` null`, the order is created without a specific delivery<br>target but rather uses the default delivery template.<br>For more information about how you can retrieve the<br>details of the default delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`order > refId`|Reference identifier of the order.|
|`order > dataCollections`|Details of the ordered data collection.|
|`order > termsOfUseSnapshots`|Details of the terms of use snapshot represents the state<br>of the terms of use of the data collection when the order<br>was placed.|
|`order > status`|Status of the order.|
|`order > id`|System generated unique identifier of the order.|
|`order > usageContext`|Details of the usage type that the Data User selected at<br>the time of order.|
|`order > createdBy`|Details of the user account that ordered the data<br>collection.|
|`order > createdOn`|Date when the order was created.|
|`order > modifiedBy`|Details of the latest user account that modified the<br>order.|
|`order > modifiedOn`|Latest date when the order was modified.|
|`order > costCenter`|Cost center of the Data User that ordered the data<br>collection.|
|`deliveryTarget`|Details of the delivery target that was used to deliver the<br>data to the Data User.|
|`deliveryTarget > id`|System generated unique identifier of the delivery target.|
|`deliveryTarget > name`|Name of the delivery target.|
|`deliveryTarget > description`|Description of the delivery target.|
|`deliveryTarget > isDefault `|Parameter that indicates whether the delivery target is<br>the default delivery option for a data collection. The<br>parameter can have one of the following values:<br>-<br>` true `. The delivery template is the default delivery<br>option for a data collection.<br>-<br>` false`. The delivery template isn't the default delivery<br>option for a data collection.|

|**Parameter**|**Description**|
|---|---|
|`deliveryTarget > status`|Status of the delivery target.|
|`deliveryTarget > physicalLocation`|Default location where the data is delivered.<br>**Note:**If the value of the` managedAccess ` parameter is<br>` ENABLED`, the data is not be delivered to the location that<br>is specified in the` physicalLocation` parameter.<br>Instead, the data will be delivered to a location that is<br>generated at the time of order fulfillment. The generated<br>location is unique to each order that is fulfilled using this<br>target.<br>For more information, see the_Integrate Data Marketplace_<br>_with other Informatica services_topic in the_Set Up Data_<br>_Marketplace_help.|
|`deliveryTarget > targetSystemReference`|Default system where the data is delivered.|
|`deliveryTarget > deliveryTemplate`|Details of the delivery template based on which the<br>delivery target was created.|
|`deliveryTarget > deliveryMethod`|Details of the delivery method.|
|`deliveryTarget > deliveryFormat`|Details of the delivery format.|
|`deliveryTarget > dataCollection`|Details of the data collection to which the delivery target<br>is assigned.|
|`deliveryTarget > createdBy`|Details of the user account that created the delivery<br>target.|
|`deliveryTarget > createdOn`|Date when the delivery target was created.|
|`deliveryTarget > modifiedBy`|Details of the latest user account that modified the<br>delivery target.|
|`deliveryTarget > modifiedOn`|Latest date when the delivery target was modified.|
|`dataCollection`|Details of the data collection for which the Data User was<br>granted access.|
|`dataCollection > id`|System generated unique identifier of the data collection.|
|`dataCollection > name`|Name of the data collection.|
|`dataCollection > description`|Purpose of the data collection.|
|`dataCollection > refId`|Reference identifier of the data collection.|
|`dataCollection > category`|Details of the category of the data collection.|
|`dataCollection > usageContexts`|Details of the usage type that is used to specify the<br>certified use of the data collection.|
|`dataCollection > dataset`|Details of the data assets that the data collection<br>contains.|


|**Parameter**|**Description**|
|---|---|
|`dataCollection > dataCollectionStatus `|The status that indicates whether the data collection is<br>discoverable by Data Users when they search for it. A<br>data collection can have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to<br>Data Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable<br>to Data Users.|
|`dataCollection > dataOwners`|Details of the stakeholders that are assigned the Data<br>Owner stakeholder role on the data collection.|
|`dataCollection > technicalOwners`|Details of the stakeholders that are assigned the<br>Technical Owner stakeholder role on the data collection.|
|`dataCollection > createdBy`|Details of the user account that created the data<br>collection.|
|`dataCollection > createdOn`|Date when the data collection was created.|
|`dataCollection > modifiedBy`|Details of the latest user account that modified the data<br>collection.|
|`dataCollection > modifiedOn`|Latest date when the data collection was modified.|
|`termsOfUseSnapshots`|Details of the terms of use snapshot that represents the<br>state of the terms of use of the data collection when the<br>order was placed.|
|`consumer`|Details of the Data User that was granted access to the<br>data collection.|
|`usageContext`|Details of the usage context that the Data User selected<br>at the time of order.|
|`createdBy`|Details of the user account that created the consumer<br>access.|
|`createdOn`|Date when the consumer access was created.|
|`modifiedBy`|Details of the latest user account that modified the<br>consumer access.|
|`modifiedOn`|Latest date when the consumer access was modified.|
|`customFields`|The custom fields of the consumer access and their<br>values.<br>Custom attributes are additional properties for Data<br>Marketplace items that are defined by your administrator<br>in Metadata Command Center.<br>For more information about custom attributes, see the<br>_Create custom attributes for items_topic in the_Set Up_<br>_Data Marketplace_help.|

|**Parameter**|**Description**|
|---|---|
|`customFields > name`|The system generated unique identifier of the custom<br>attribute.<br>For more information about how you can retrieve the<br>custom attribute name as it appears on the Data<br>Marketplace interface, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value. This parameter can have only<br>a single value.|
|`customFields > values`|The custom attribute value. This parameter can have<br>multiple values.|


#### Retrieve consumer accesses
Use a REST API to retrieve the details of consumer accesses in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/consumerAccess**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`consumerAccessIds`|Optional. Enter the system generated unique<br>identifier of a consumer access.|To get the system generated unique<br>identifier of a consumer access,<br>open the consumer access. The<br>consumer access page's URL<br>contains the system generated<br>unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/access/`<br>`e254491f-5795-49bd-be0b-385eb11d9d5a?`<br>`dtn=Access~2e85 `, the system<br>generated unique identifier is<br>` e254491f-5795-49bd-be0b-385eb11d9d5a `.<br>To enter more than one value, use<br>the following format:<br>` ids=<value1>&ids=<value2>`|
|`dataCollectionIds`|Optional. Enter the system generated unique<br>identifier of the data collection to which the<br>Data User was granted access.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a<br>data collection, see<br>“Retrieve data collections” on page 217.<br>To get the system generated unique<br>identifier of a data collection from<br>the Data Marketplace interface,<br>open the data collection. The data<br>collection page's URL contains the<br>system generated unique identifier.<br>For example, in the URL` https://{{CDMP_URL}}/`<br>`datacollection/25158afc-3dfb-44ef-8f3e-`<br>`cec1e171d0f1?`<br>`dtn=&tab=summary `, the system<br>generated unique identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|
|`deliveryTemplateIds`|Optional. Enter the system generated unique<br>identifier of the delivery template that was<br>used to deliver the data to the Data User.|For more information about how you<br>can use an API to get the system<br>generated unique identifier of a<br>delivery template, see<br>“Retrieve delivery templates” on page 152.|

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`consumerAccessCreatedBy`|Optional. Enter the system generated unique<br>identifier of the user account that created<br>the consumer access.|To get the system generated unique<br>identifier of a user account,<br>navigate to**My Services >**<br>**Administrator > Users**. On the**Users**<br>page, click a user account. The user<br>account page's URL contains the<br>system generated unique identifier.<br>For example, in the URL`/cloudUI/products/administer/main/`<br>`usersAsset/0LH3xBJC9A6haZ26htGZyT`, the<br>system generated unique identifier<br>is` 0LH3xBJC9A6haZ26htGZyT`.|
|`createdDateFrom`|Optional. To find consumer accesses that<br>were created between a date range, enter<br>the initial date when the consumer<br>accesses were created.|To specify a date, use the` YYYY-MM-DD ` format. The value that you<br>specify is automatically converted<br>and stored in the Coordinated<br>Universal Time (UTC) time standard.<br>If you have specified a value for the<br>` createdDateFrom` parameter,<br>ensure that you also enter a value<br>for the` createdDateTo` parameter.|
|`createdDateTo`|Optional. To find consumer accesses that<br>were created between a date range, enter<br>the latest date when the consumer<br>accesses were created.|To specify a date, use the` YYYY-MM-DD ` format. The value that you<br>specify is automatically converted<br>and stored in the Coordinated<br>Universal Time (UTC) time standard.<br>If you have specified a value for the<br>` createdDateTo ` parameter, ensure<br>that you also enter a value for the<br>` createdDateFrom` parameter.|
|`status `|Optional. Specify the status of the<br>consumer access.|Enter one of the following values:<br>- To find consumer accesses<br>where the Data User has access<br>to the data collection, enter<br>` AVAILABLE `.<br>- To find consumer accesses that<br>are pending withdrawal, enter<br>` PENDING_WITHDRAW `.<br>- To find consumer accesses that<br>are withdrawn,` WITHDRAWN`.|
|`sortByField `|Optional. Specify the parameters to sort the<br>search results.|To sort the search results, enter one<br>of the following values:<br>-<br>` ID `<br>-<br>` STATUS `<br>-<br>` CREATED_BY `<br>-<br>` CREATED_ON `<br>-<br>` MODIFIED_BY `<br>-<br>` MODIFIED_ON`<br>Default is` sortByField >`<br>`MODIFIED_ON`.|


|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`sort`|Optional. Set the sorting order of the search<br>results.|You can sort the search results in<br>the following order:<br>- To sort the search results by<br>ascending order, enter` ASC`.<br>- To sort the search results by<br>descending order, enter` DESC`.<br>Default is` DESC`.|
|`offset`|Optional. Enter the starting index for the<br>paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum number of<br>results.|Default is` 50`.<br>Maximum is` 100`.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of a consumer access: 

```
https://{{CDMP_URL}}/api/v1/integration/consumerAccess?
consumerAccessIds=40b32fbd-538a-4369-9246-
d18b7998f011&limit=100&status=AVAILABLE&sortByField=ID&sort=ASC
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the details of a consumer access: 

```
{
  "processingTime": 1346,
  "offset": 0,
  "limit": 100,
  "totalCount": 345,
  "objects": [
    {
      "id": "40b32fbd-538a-4369-9246-d18b7998f011",
      "refId": "ACC-131",
      "status": "AVAILABLE",
      "accessGrantedOn": "2022-09-14T13:30:36.405Z",
      "costCenter": "Retail-IQO3456",
      "order": {
        "justification": "Required for the Q3 sales reporting.",
        "deliveryRequests": null,
        "requestedProvisionedTargetRef": "b441a10a-d64d-4801-94d8-145e805001c4",
        "refId": "ORD-345",
        "dataCollections": null,
        "termsOfUseSnapshots": null,
        "status": "COMPLETE",
        "id": "b986ae59-7fc9-414c-98d7-14c7a1671023",
        "usageContext": null,
        "createdBy": {
          "displayName": "Johnny Doe",
          "id": "6k7aaGYrSOsfN7RFlPH1YU",
          "name": "23Aug_CDMP_QE1",
          "email": "jydoe@informatica.com",
          "phone": "555-243-456",
          "status": "ACTIVE",
          "userInfo": {},

          "isGroup": true
        },
        "createdOn": "2022-09-14T13:30:36.405Z",
        "modifiedBy": {
          "displayName": "Johnny Doe",
          "id": "6k7aaGYrSOsfN7RFlPH1YU",
          "name": "23Aug_CDMP_QE1",
          "email": "jydoe@informatica.com",
          "phone": "555-243-456",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": true
        },
        "modifiedOn": "2022-09-14T13:30:36.405Z",
        "costCenter": "Retail-IQO3456"
      },
      "deliveryTarget": {
        "id": "b441a10a-d64d-4801-94d8-145e805001c4",
        "name": "IPFS Delivery",
        "description": "Use this template to deliver data via IPFS.",
        "isDefault": true,
        "status": "ACTIVE",
        "physicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
        "targetSystemReference": "IPFS",
        "deliveryTemplate": {
          "id": "1c865800-86ca-4280-8cdd-89dda66c013c",
          "name": "IPFS",
          "description": "Use this template to deliver data via IPFS.",
          "color": null,
          "isDefault": true,
          "status": "ACTIVE",
          "managedAccess": "DISABLED",
          "deliveryType": "AUTOMATIC",
          "templateOwners": [
            {
             "displayName": "Johnny Doe",
             "id": "6k7aaGYrSOsfN7RFlPH1YU",
             "name": "23Aug_CDMP_QE1",
             "email": "jydoe@informatica.com",
             "phone": "555-243-456",
             "status": "ACTIVE",
             "userInfo": {},
             "isGroup": true
            }
          ],
          "targetSystemReference": "IPFS",
          "defaultPhysicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
          "deliveryMethods": null,
          "deliveryFormats": null,
          "createdBy": "8npzPxKim0XjHE4es2gkAc",
          "createdOn": "2022-09-14T11:57:00.446Z",
          "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
          "modifiedOn": "2022-09-14T11:57:00.446Z"
        },
        "deliveryMethod": {
          "id": "string",
          "name": "string",
          "status": "ACTIVE",
          "createdBy": "8npzPxKim0XjHE4es2gkAc",
          "createdOn": "2022-09-14T11:57:00.446Z",
          "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
          "modifiedOn": "2022-09-14T11:57:00.446Z"
        },
        "deliveryFormat": {
          "id": "e0f8af4d-c0f0-4c02-af03-5b9b2ce43253",
          "name": "CSV",
          "status": "Deliver data as a CSV file.",
          "createdBy": "8npzPxKim0XjHE4es2gkAc",
          "createdOn": "2022-09-14T11:57:00.446Z",
          "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
          "modifiedOn": "2022-09-14T11:57:00.446Z"
```


```
        },
        "dataCollection": {
          "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
          "name": "Retail Sales Report",
          "description": "Sales reports for the retail outlets.",
          "refId": "DCL-672",
          "category": {},
          "usageContexts": null,
          "dataset": null,
          "dataCollectionStatus": "PUBLISHED",
          "dataOwners": [],
          "technicalOwners": [],
          "createdBy": {
            "displayName": "Janet Doe",
            "id": "6k7aaGYrSOsfN7RFlPH1YU",
            "name": "23Aug_CDMP_QE1",
            "email": "jtdoe@informatica.com",
            "phone": "555-243-456",
            "status": "ACTIVE",
            "userInfo": {},
            "isGroup": true
          },
          "createdOn": "2022-09-14T11:57:00.446Z",
          "modifiedBy": {
            "displayName": "Janet Doe",
            "id": "6k7aaGYrSOsfN7RFlPH1YU",
            "name": "23Aug_CDMP_QE1",
            "email": "jtdoe@informatica.com",
            "phone": "555-243-456",
            "status": "ACTIVE",
            "userInfo": {},
            "isGroup": true
          },
          "modifiedOn": "2022-09-14T11:57:00.446Z"
        },
        "createdBy": "8npzPxKim0XjHE4es2gkAc",
        "createdOn": "2022-09-14T11:57:00.446Z",
        "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
        "modifiedOn": "2022-09-14T11:57:00.446Z"
      },
      "dataCollection": {
          "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
          "name": "Retail Sales Report",
          "description": "Sales reports for the retail outlets.",
          "refId": "DCL-672",
          "category": null,
          "usageContexts": null,
          "dataset": null,
          "dataCollectionStatus": "PUBLISHED",
          "dataOwners": [
            {
            "displayName": "Janet Doe",
            "id": "6k7aaGYrSOsfN7RFlPH1YU",
            "name": "23Aug_CDMP_QE1",
            "email": "jtdoe@informatica.com",
            "phone": "555-243-456",
            "status": "ACTIVE",
            "userInfo": {},
            "isGroup": true
            }
          ],
          "technicalOwners": [
            {
            "displayName": "Johnny Doe",
            "id": "6k7aaGYrSOsfN7RFlPH1YU",
            "name": "23Aug_CDMP_QE1",
            "email": "jydoe@informatica.com",
            "phone": "555-243-456",
            "status": "ACTIVE",
            "userInfo": {},
            "isGroup": true

            }
          ],
          "createdBy": {
            "displayName": "Jane Doe",
            "id": "6k7aaGYrSOsfN7RFlPH1YU",
            "name": "23Aug_CDMP_QE1",
            "email": "jdoe@informatica.com",
            "phone": "555-243-456",
            "status": "ACTIVE",
            "userInfo": {},
            "isGroup": true
          },
          "createdOn": "2022-09-14T11:57:00.446Z",
          "modifiedBy": {
            "displayName": "Jane Doe",
            "id": "6k7aaGYrSOsfN7RFlPH1YU",
            "name": "23Aug_CDMP_QE1",
            "email": "jdoe@informatica.com",
            "phone": "555-243-456",
            "status": "ACTIVE",
            "userInfo": {},
            "isGroup": true
          },
          "modifiedOn": "2022-09-14T11:57:00.446Z"
      },
      "termsOfUseSnapshots": [
        {
          "id": "e37bf0c7-faf3-4902-8bcc-485d80cc8bb9",
          "termsOfUseId": null,
          "termsOfUseRefId": null,
          "generalTermsOfUse": "This data must not be accessed via unauthorized
devices.",
          "name": null,
          "description": null,
          "type": null,
          "status": null,
          "acknowledgement": null,
          "parentId": "null,
          "referenceLink": null,
          "createdOn": "2022-09-14T11:57:00.446Z"
        }
      ],
      "consumer": {
          "displayName": "John Doe",
          "id": "6k7aaGYrSOsfN7RFlPH1YU",
          "name": "23Aug_CDMP_QE1",
          "email": "jdoe@informatica.com",
          "phone": "555-243-456",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": true
          },
      "usageContext": {
          "id": "9669f6ab-affc-4106-a583-48a6b580b930",
          "name": "Sales Use",
          "description": "This is for sales reporting tasks.",
          "color": null,
          "status": "ACTIVE"
          },
      "createdBy": {
          "displayName": "Jane Doe",
          "id": "6k7aaGYrSOsfN7RFlPH1YU",
          "name": "23Aug_CDMP_QE1",
          "email": "jdoe@informatica.com",
          "phone": "555-243-456",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": true
          },
      "createdOn": "2022-09-14T13:30:36.726Z",
      "modifiedBy": {
```


```
          "displayName": "Jane Doe",
          "id": "6k7aaGYrSOsfN7RFlPH1YU",
          "name": "23Aug_CDMP_QE1",
          "email": "jdoe@informatica.com",
          "phone": "555-243-456",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": true
          },
      "modifiedOn": "2022-09-15T13:59:40.472Z",
      "customFields": [
        {
          "name": "Year",
          "value": "2021",
          "values": null
       }
    ]
}
```

The following table describes the parameters of each consumer access that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of consumer accesses retrieved.|
|`id`|System generated unique identifier of the consumer<br>access.|
|`refId`|Reference identifier of the consumer access.|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data<br>collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to<br>withdraw the Data User's access to the data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data<br>collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the<br>data collection.|
|`costCenter`|Cost center of the Data User that was granted access to<br>the data collection.|
|`order`|Details of the order placed by the Data User that was<br>granted access to the data collection.|
|`order > justification`|The reason for the requirement of access to the data<br>provided by the Data User at the time of order.|
|`order > deliveryRequests`|Delivery information of the order.|

|**Parameter**|**Description**|
|---|---|
|`order > requestedProvisionedTargetRef `|The delivery target requested by the Data User.<br>**Note:**If the value of the<br>` requestedProvisionedTargetRef ` parameter is<br>` null`, the order is created without a specific delivery<br>target but rather uses the default delivery template.<br>For more information about how you can retrieve the<br>details of the default delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`order > refId`|Reference identifier of the order.|
|`order > dataCollections`|Details of the ordered data collection.|
|`order > termsOfUseSnapshots`|Details of the terms of use snapshot that represents the<br>state of the terms of use of the data collection when the<br>order was placed.|
|`order > status`|Status of the order.|
|`order > id`|System generated unique identifier of the order.|
|`order > usageContext`|Details of the usage type that the Data User selected at<br>the time of order.|
|`order > createdBy`|Details of the user account that ordered the data<br>collection.|
|`order > createdOn`|Date when the order was created.|
|`order > modifiedBy`|Details of the latest user account that modified the<br>order.|
|`order > modifiedOn`|Latest date when the order was modified.|
|`order > costCenter`|Cost center of the Data User that ordered the data<br>collection.|
|`deliveryTarget`|Details of the delivery target that was used to deliver the<br>data to the Data User.|
|`deliveryTarget > id`|System generated unique identifier of the delivery target.|
|`deliveryTarget > name`|Name of the delivery target.|
|`deliveryTarget > description`|Description of the delivery target.|
|`deliveryTarget > isDefault `|Parameter that indicates whether the delivery target is<br>the default delivery option for a data collection. The<br>parameter can have one of the following values:<br>-<br>` true `. The delivery template is the default delivery<br>option for a data collection.<br>-<br>` false`. The delivery template isn't the default delivery<br>option for a data collection.|
|`deliveryTarget > status`|Status of the delivery target.|


|**Parameter**|**Description**|
|---|---|
|`deliveryTarget > physicalLocation`|Default location where the data is delivered.<br>**Note:**If the value of the` managedAccess ` parameter is<br>` ENABLED`, the data is not be delivered to the location that<br>is specified in the` physicalLocation` parameter.<br>Instead, the data will be delivered to a location that is<br>generated at the time of order fulfillment. The generated<br>location is unique to each order that is fulfilled using this<br>target.<br>For more information, see the_Integrate Data Marketplace_<br>_with other Informatica services_topic in the_Set Up Data_<br>_Marketplace_help.|
|`deliveryTarget > targetSystemReference`|Default system where the data is delivered.|
|`deliveryTarget > deliveryTemplate`|Details of the delivery template based on which the<br>delivery target was created.|
|`deliveryTarget > deliveryMethod`|Details of the delivery method.|
|`deliveryTarget > deliveryFormat`|Details of the delivery format.|
|`deliveryTarget > dataCollection`|Details of the data collection to which the delivery target<br>is assigned.|
|`deliveryTarget > createdBy`|Details of the user account that created the delivery<br>target.|
|`deliveryTarget > createdOn`|Date when the delivery target was created.|
|`deliveryTarget > modifiedBy`|Details of the latest user account that modified the<br>delivery target.|
|`deliveryTarget > modifiedOn`|Latest date when the delivery target was modified.|
|`dataCollection`|Details of the data collection for which the Data User was<br>granted access.|
|`dataCollection > id`|System generated unique identifier of the data collection.|
|`dataCollection > name`|Name of the data collection.|
|`dataCollection > description`|Purpose of the data collection.|
|`dataCollection > refId`|Reference identifier of the data collection.|
|`dataCollection > category`|Details of the category of the data collection.|
|`dataCollection > usageContexts`|Details of the usage type that is used to specify the<br>certified use of the data collection.|
|`dataCollection > dataset`|Details of the data assets that the data collection<br>contains.|

|**Parameter**|**Description**|
|---|---|
|`dataCollection > dataCollectionStatus `|The status determines whether the data collection is<br>discoverable by Data Users when they search for it. A<br>data collection can have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to<br>Data Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable<br>to Data Users.|
|`dataCollection > dataOwners`|Details of the stakeholders that are assigned the Data<br>Owner stakeholder role on the data collection.|
|`dataCollection > technicalOwners`|Details of the stakeholders that are assigned the<br>Technical Owner stakeholder role on the data collection.|
|`dataCollection > createdBy`|Details of the user account that created the data<br>collection.|
|`dataCollection > createdOn`|Date when the data collection was created.|
|`dataCollection > modifiedBy`|Details of the latest user account that modified the data<br>collection.|
|`dataCollection > modifiedOn`|Latest date when the data collection was modified.|
|`termsOfUseSnapshots`|Details of the terms of use snapshot that represents the<br>state of the terms of use of the data collection when the<br>order was placed.|
|`consumer`|Details of the Data User that was granted access to the<br>data collection.|
|`usageContext`|Details of the usage context that the Data User selected<br>at the time of order.|
|`createdBy`|Details of the user account that created the consumer<br>access.|
|`createdOn`|Date when the consumer access was created.|
|`modifiedBy`|Details of the latest user account that modified the<br>consumer access.|
|`modifiedOn`|Latest date when the consumer access was modified.|
|`customFields`|The custom fields of the consumer access and their<br>values.<br>Custom attributes are additional properties for Data<br>Marketplace items that are defined by your administrator<br>in Metadata Command Center.<br>For more information about custom attributes, see the<br>_Create custom attributes for items_topic in the_Set Up_<br>_Data Marketplace_help.|


|**Parameter**|**Description**|
|---|---|
|`customFields > name`|The system generated unique identifier of the custom<br>attribute.<br>For more information about how you can retrieve the<br>custom attribute name as it appears on the Data<br>Marketplace interface, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value. This parameter can have only<br>a single value.|
|`customFields > values`|The custom attribute value. This parameter can have<br>multiple values.|


#### Make consumer accesses available
Use a REST API to make consumer accesses available in Data Marketplace. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/consumerAccess/<consumerAccessId>/available**<br>`<consumerAccessId>`: Required. Enter the system generated identifier of the consumer access for which<br>you want to set the status as` AVAILABLE`.|
||For more information about how you can use an API to get the system generated unique identifier of a<br>consumer access, see<br>“Retrieve consumer accesses” on page 315.|
||To get the system generated unique identifier of a consumer access from the Data Marketplace interface,<br>open the consumer access. The consumer access page's URL contains the system generated unique<br>identifier.|
||For example, in the URL` https://{{CDMP_URL}}/access/e254491f-5795-49bd-be0b-385eb11d9d5a?dtn=Access~2e85 `, the system generated unique identifier is<br>` e254491f-5795-49bd-be0b-385eb11d9d5a`.|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


**Note:** The API has no payload.

#### Example request
The following example shows how you can use an API to make a consumer access available: 

```
https://{{CDMP_URL}}/api/v1/integration/consumerAccess/794a215f-5479-4b55-8e1b-
a4866ec9fe82/available?comment=Request%20Granted
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to make a consumer access available: 

```
{
  "id": "794a215f-5479-4b55-8e1b-a4866ec9fe82",
  "refId": "ACC-246",
  "status": "AVAILABLE",
  "accessGrantedOn": "2022-09-14T13:30:36.405Z",
  "costCenter": "Retail-IQO3456",
  "order": {
    "justification": "Required for the Q3 sales reporting.",
    "deliveryRequests": null,
    "requestedProvisionedTargetRef": "b441a10a-d64d-4801-94d8-145e805001c4",
    "refId": "ORD-337",
    "dataCollections": null,
    "termsOfUseSnapshots": null,
    "status": "COMPLETE",
    "id": "b986ae59-7fc9-414c-98d7-14c7a1671023",
    "usageContext": null,
    "createdBy": {
      "displayName": "Johnny Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jydoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "createdOn": "2022-09-14T13:30:36.405Z",
    "modifiedBy": {
      "displayName": "Johnny Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jydoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "modifiedOn": "2022-09-14T13:30:36.405Z",
    "costCenter": "Retail-IQO3456"
  },
  "deliveryTarget": {
    "id": "b441a10a-d64d-4801-94d8-145e805001c4",
    "name": "IPFS Delivery",
    "description": "Use this template to deliver data via IPFS.",
    "isDefault": true,
    "status": "ACTIVE",
    "physicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
    "targetSystemReference": "IPFS",
    "deliveryTemplate": {
      "id": "1c865800-86ca-4280-8cdd-89dda66c013c",
      "name": "IPFS",
      "description": "Use this template to deliver data via IPFS.",
      "color": null,
      "isDefault": true,
      "status": "ACTIVE",
```


```
      "managedAccess": "DISABLED",
      "deliveryType": "AUTOMATIC",
      "templateOwners": [
        {
          "displayName": "Johnny Doe",
          "id": "6k7aaGYrSOsfN7RFlPH1YU",
          "name": "23Aug_CDMP_QE1",
          "email": "jydoe@informatica.com",
          "phone": "555-243-456",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": true
        }
      ],
      "targetSystemReference": "IPFS",
      "defaultPhysicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
      "deliveryMethods": null,
      "deliveryFormats": null,
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "deliveryMethod": {
      "id": "f8f8as4d-c0f0-3r11-fr04-7b7h2ce46731",
      "name": "IPFS",
      "status": "ACTIVE",
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "deliveryFormat": {
      "id": "e0f8af4d-c0f0-4c02-af03-5b9b2ce43253",
      "name": "CSV",
      "status": "Deliver data as a CSV file.",
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "dataCollection": {
      "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
      "name": "Retail Sales Report",
      "description": "Sales reports for the retail outlets.",
      "refId": "DCL-834",
      "category": null,
      "usageContexts": null,
      "dataset": null,
      "dataCollectionStatus": "PUBLISHED",
      "dataOwners": [],
      "technicalOwners": [],
      "createdBy": {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      },
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},

        "isGroup": true
      },
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "createdBy": "8npzPxKim0XjHE4es2gkAc",
    "createdOn": "2022-09-14T11:57:00.446Z",
    "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
    "modifiedOn": "2022-09-14T11:57:00.446Z"
  },
  "dataCollection": {
    "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
    "name": "Retail Sales Report",
    "description": "Sales reports for the retail outlets.",
    "refId": "DCL-834",
    "category": null,
    "usageContexts": null,
    "dataset": null,
    "dataCollectionStatus": "PUBLISHED",
    "dataOwners": [
      {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      }
    ],
    "technicalOwners": [
      {
        "displayName": "Johnny Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jydoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      }
    ],
    "createdBy": {
      "displayName": "Jane Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jdoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "createdOn": "2022-09-14T11:57:00.446Z",
    "modifiedBy": {
      "displayName": "Jane Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jdoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "modifiedOn": "2022-09-14T11:57:00.446Z"
  },
  "termsOfUseSnapshots": [
    {
      "id": "e37bf0c7-faf3-4902-8bcc-485d80cc8bb9",
      "termsOfUseId": null,
      "termsOfUseRefId": null,
      "generalTermsOfUse": "This data must not be accessed via unauthorized devices.",
```


```
      "name": null,
      "description": null,
      "type": null,
      "status": null,
      "acknowledgement": null,
      "parentId": "null,
      "referenceLink": null,
      "createdOn": "2022-09-14T11:57:00.446Z"
    }
  ],
  "consumer": {
    "displayName": "John Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "usageContext": {
    "id": "9669f6ab-affc-4106-a583-48a6b580b930",
    "name": "Sales Use",
    "description": "This is for sales reporting tasks.",
    "color": null,
    "status": "ACTIVE"
  },
  "createdBy": {
    "displayName": "Jane Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "createdOn": "2022-09-14T13:30:36.726Z",
  "modifiedBy": {
    "displayName": "Jane Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "modifiedOn": "2022-09-15T13:59:40.472Z",
  "customFields": [
        {
          "name": "Year",
          "value": "2021",
          "values": null
       }
}
```

The following table describes the parameters of each consumer access that is made available to Data Users: 

|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the consumer|
||access.|
|`refId`|Reference identifier of the consumer access.|

|**Parameter**|**Description**|
|---|---|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data<br>collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to<br>withdraw the Data User's access to the data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data<br>collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the<br>data collection.|
|`costCenter`|Cost center of the Data User that was granted access to<br>the data collection.|
|`order`|Details of the order placed by the Data User that was<br>granted access to the data collection.|
|`order > justification`|The reason for the requirement of access to the data<br>provided by the Data User at the time of order.|
|`order > deliveryRequests`|Delivery information of the order.|
|`order > requestedProvisionedTargetRef `|The delivery target requested by the Data User.<br>**Note:**If the value of the<br>` requestedProvisionedTargetRef ` parameter is<br>` null`, the order is created without a specific delivery<br>target but rather uses the default delivery template.<br>For more information about how you can retrieve the<br>details of the default delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`order > refId`|Reference identifier of the order.|
|`order > dataCollections`|Details of the ordered data collection.|
|`order > termsOfUseSnapshots`|Details of the terms of use snapshot that represents the<br>state of the terms of use of the data collection when the<br>order was placed.|
|`order > status`|Status of the order.|
|`order > id`|System generated unique identifier of the order.|
|`order > usageContext`|Details of the usage type that the Data User selected at<br>the time of order.|
|`order > createdBy`|Details of the user account that ordered the data<br>collection.|
|`order > createdOn`|Date when the order was created.|
|`order > modifiedBy`|Details of the latest user account that modified the<br>order.|


|**Parameter**|**Description**|
|---|---|
|`order > modifiedOn`|Latest date when the order was modified.|
|`order > costCenter`|Cost center of the Data User that ordered the data<br>collection.|
|`deliveryTarget`|Details of the delivery target that was used to deliver the<br>data to the Data User.|
|`deliveryTarget > id`|System generated unique identifier of the delivery target.|
|`deliveryTarget > name`|Name of the delivery target.|
|`deliveryTarget > description`|Description of the delivery target.|
|`deliveryTarget > isDefault `|Parameter that indicates whether the delivery target is<br>the default delivery option for a data collection. The<br>parameter can have one of the following values:<br>-<br>` true `. The delivery template is the default delivery<br>option for a data collection.<br>-<br>` false`. The delivery template isn't the default delivery<br>option for a data collection.|
|`deliveryTarget > status`|Status of the delivery target.|
|`deliveryTarget > physicalLocation`|Default location where the data is delivered.<br>**Note:**If the value of the` managedAccess ` parameter is<br>` ENABLED`, the data is not be delivered to the location that<br>is specified in the` physicalLocation` parameter.<br>Instead, the data will be delivered to a location that is<br>generated at the time of order fulfillment. The generated<br>location is unique to each order that is fulfilled using this<br>target.<br>For more information, see the_Integrate Data Marketplace_<br>_with other Informatica services_topic in the_Set Up Data_<br>_Marketplace_help.|
|`deliveryTarget > targetSystemReference`|Default system where the data is delivered.|
|`deliveryTarget > deliveryTemplate`|Details of the delivery template based on which the<br>delivery target was created.|
|`deliveryTarget > deliveryMethod`|Details of the delivery method.|
|`deliveryTarget > deliveryFormat`|Details of the delivery format.|
|`deliveryTarget > dataCollection`|Details of the data collection to which the delivery target<br>is assigned.|
|`deliveryTarget > createdBy`|Details of the user account that created the delivery<br>target.|
|`deliveryTarget > createdOn`|Date when the delivery target was created.|
|`deliveryTarget > modifiedBy`|Details of the latest user account that modified the<br>delivery target.|

|**Parameter**|**Description**|
|---|---|
|`deliveryTarget > modifiedOn`|Latest date when the delivery target was modified.|
|`dataCollection`|Details of the data collection for which the Data User was<br>granted access.|
|`dataCollection > id`|System generated unique identifier of the data collection.|
|`dataCollection > name`|Name of the data collection.|
|`dataCollection > description`|Purpose of the data collection.|
|`dataCollection > refId`|Reference identifier of the data collection.|
|`dataCollection > category`|Details of the category of the data collection.|
|`dataCollection > usageContexts`|Details of the usage type that is used to specify the<br>certified use of the data collection.|
|`dataCollection > dataset`|Details of the data assets that the data collection<br>contains.|
|`dataCollection > dataCollectionStatus `|The status determines whether the data collection is<br>discoverable by Data Users when they search for it. A<br>data collection can have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to<br>Data Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable<br>to Data Users.|
|`dataCollection > dataOwners`|Details of the stakeholders that are assigned the Data<br>Owner stakeholder role on the data collection.|
|`dataCollection > technicalOwners`|Details of the stakeholders that are assigned the<br>Technical Owner stakeholder role on the data collection.|
|`dataCollection > createdBy`|Details of the user account that created the data<br>collection.|
|`dataCollection > createdOn`|Date when the data collection was created.|
|`dataCollection > modifiedBy`|Details of the latest user account that modified the data<br>collection.|
|`dataCollection > modifiedOn`|Latest date when the data collection was modified.|
|`termsOfUseSnapshots`|Details of the terms of use snapshot represents the state<br>of the terms of use of the data collection when the order<br>was placed.|
|`consumer`|Details of the Data User that was granted access to the<br>data collection.|
|`usageContext`|Details of the usage context that the Data User selected<br>at the time of order.|


|**Parameter**|**Description**|
|---|---|
|`createdBy`|Details of the user account that created the consumer<br>access.|
|`createdOn`|Date when the consumer access was created.|
|`modifiedBy`|Details of the latest user account that modified the<br>consumer access.|
|`modifiedOn`|Latest date when the consumer access was modified.|
|`customFields`|The custom fields of the consumer access and their<br>values.<br>Custom attributes are additional properties for Data<br>Marketplace items that are defined by your administrator<br>in Metadata Command Center.<br>For more information about custom attributes, see the<br>_Create custom attributes for items_topic in the_Set Up_<br>_Data Marketplace_help.|
|`customFields > name`|The system generated unique identifier of the custom<br>attribute.<br>For more information about how you can retrieve the<br>custom attribute name as it appears on the Data<br>Marketplace interface, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value. This parameter can have only<br>a single value.|
|`customFields > values`|The custom attribute value. This parameter can have<br>multiple values.|


#### Submit consumer access withdrawals
Use a REST API to submit a request to withdraw a Data User's access to a data collection.

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v1/integration/consumerAccess/<consumerAccessId>/pending_withdraw** `<consumerAccessId>` : Required. Enter the system generated identifier of the consumer access for which you want to request a withdrawal. For more information about how you can use an API to get the system generated unique identifier of a consumer access, see “Retrieve consumer accesses” on page 315. To get the system generated unique identifier of a consumer access from the Data Marketplace interface, open the consumer access. The consumer access page's URL contains the system generated unique identifier. For example, in the URL `https://{{CDMP_URL}}/access/e254491f-5795-49bdbe0b-385eb11d9d5a?dtn=Access~2e85 ` , the system generated unique identifier is ` e254491f-5795-49bd-be0b-385eb11d9d5a` . Method PUT 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to submit a withdrawal request for a consumer access: 

```
https://{{CDMP_URL}}/api/v1/integration/consumerAccess/794a215f-5479-4b55-8e1b-
a4866ec9fe82/pending_withdraw?comment=Request%20Withdraw
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to submit a withdrawal request for a consumer access: 

- `{ "id": "794a215f-5479-4b55-8e1b-a4866ec9fe82", "refId": "ACC-246", "status": "PENDING_WITHDRAW",` 

- `"accessGrantedOn": "2022-09-14T13:30:36.405Z", "costCenter": "Retail-IQO3456", "order": {` 


```
    "justification": "Required for the Q3 sales reporting.",
    "deliveryRequests": null,
    "requestedProvisionedTargetRef": "b441a10a-d64d-4801-94d8-145e805001c4",
    "refId": "ORD-337",
    "dataCollections": null,
    "termsOfUseSnapshots": null,
    "status": "COMPLETE",
    "id": "b986ae59-7fc9-414c-98d7-14c7a1671023",
    "usageContext": null,
    "createdBy": {
      "displayName": "Johnny Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jydoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "createdOn": "2022-09-14T13:30:36.405Z",
    "modifiedBy": {
      "displayName": "Johnny Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jydoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "modifiedOn": "2022-09-14T13:30:36.405Z",
    "costCenter": "Retail-IQO3456"
  },
  "deliveryTarget": {
    "id": "b441a10a-d64d-4801-94d8-145e805001c4",
    "name": "IPFS Delivery",
    "description": "Use this template to deliver data via IPFS.",
    "isDefault": true,
    "status": "ACTIVE",
    "physicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
    "targetSystemReference": "IPFS",
    "deliveryTemplate": {
      "id": "1c865800-86ca-4280-8cdd-89dda66c013c",
      "name": "IPFS",
      "description": "Use this template to deliver data via IPFS.",
      "color": null,
      "isDefault": true,
      "status": "ACTIVE",
      "managedAccess": "DISABLED",
      "deliveryType": "AUTOMATIC",
      "templateOwners": [
        {
          "displayName": "Johnny Doe",
          "id": "6k7aaGYrSOsfN7RFlPH1YU",
          "name": "23Aug_CDMP_QE1",
          "email": "jydoe@informatica.com",
          "phone": "555-243-456",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": true
        }
      ],
      "targetSystemReference": "IPFS",
      "defaultPhysicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
      "deliveryMethods": null,
      "deliveryFormats": null,
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },

    "deliveryMethod": {
      "id": "f8f8as4d-c0f0-3r11-fr04-7b7h2ce46731",
      "name": "IPFS",
      "status": "ACTIVE",
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "deliveryFormat": {
      "id": "e0f8af4d-c0f0-4c02-af03-5b9b2ce43253",
      "name": "CSV",
      "status": "Deliver data as a CSV file.",
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "dataCollection": {
      "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
      "name": "Retail Sales Report",
      "description": "Sales reports for the retail outlets.",
      "refId": "DCL-834",
      "category": null,
      "usageContexts": null,
      "dataset": null,
      "dataCollectionStatus": "PUBLISHED",
      "dataOwners": [],
      "technicalOwners": [],
      "createdBy": {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      },
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      },
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "createdBy": "8npzPxKim0XjHE4es2gkAc",
    "createdOn": "2022-09-14T11:57:00.446Z",
    "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
    "modifiedOn": "2022-09-14T11:57:00.446Z"
  },
  "dataCollection": {
    "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
    "name": "Retail Sales Report",
    "description": "Sales reports for the retail outlets.",
    "refId": "DCL-834",
    "category": null,
    "usageContexts": null,
    "dataset": null,
    "dataCollectionStatus": "PUBLISHED",
    "dataOwners": [
      {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
```


```
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      }
    ],
    "technicalOwners": [
      {
        "displayName": "Johnny Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jydoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      }
    ],
    "createdBy": {
      "displayName": "Jane Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jdoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "createdOn": "2022-09-14T11:57:00.446Z",
    "modifiedBy": {
      "displayName": "Jane Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jdoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "modifiedOn": "2022-09-14T11:57:00.446Z"
  },
  "termsOfUseSnapshots": [
    {
      "id": "e37bf0c7-faf3-4902-8bcc-485d80cc8bb9",
      "termsOfUseId": null,
      "termsOfUseRefId": null,
      "generalTermsOfUse": "This data must not be accessed via unauthorized devices.",
      "name": null,
      "description": null,
      "type": null,
      "status": null,
      "acknowledgement": null,
      "parentId": "null,
      "referenceLink": null,
      "createdOn": "2022-09-14T11:57:00.446Z"
    }
  ],
  "consumer": {
    "displayName": "John Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "usageContext": {
    "id": "9669f6ab-affc-4106-a583-48a6b580b930",
    "name": "Sales Use",

    "description": "This is for sales reporting tasks.",
    "color": null,
    "status": "ACTIVE"
  },
  "createdBy": {
    "displayName": "Jane Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "createdOn": "2022-09-14T13:30:36.726Z",
  "modifiedBy": {
    "displayName": "Jane Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "modifiedOn": "2022-09-15T13:59:40.472Z",
  "customFields": [
        {
          "name": "Year",
          "value": "2021",
          "values": null
       }
}
```

The following table describes the parameters of each consumer access for which a withdrawal request is submitted: 

|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the consumer<br>access.|
|`refId`|Reference identifier of the consumer access.|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data<br>collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to<br>withdraw the Data User's access to the data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data<br>collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the<br>data collection.|
|`costCenter`|Cost center of the Data User that was granted access to<br>the data collection.|
|`order`|Details of the order placed by the Data User that was<br>granted access to the data collection.|


|**Parameter**|**Description**|
|---|---|
|`order > justification`|The reason for the requirement of access to the data<br>provided by the Data User at the time of order.|
|`order > deliveryRequests`|Delivery information of the order.|
|`order > requestedProvisionedTargetRef `|The delivery target requested by the Data User.<br>**Note:**If the value of the<br>` requestedProvisionedTargetRef ` parameter is<br>` null`, the order is created without a specific delivery<br>target but rather uses the default delivery template.<br>For more information about how you can retrieve the<br>details of the default delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`order > refId`|Reference identifier of the order.|
|`order > dataCollections`|Details of the ordered data collection.|
|`order > termsOfUseSnapshots`|Details of the terms of use snapshot that represents the<br>state of the terms of use of the data collection when the<br>order was placed.|
|`order > status`|Status of the order.|
|`order > id`|System generated unique identifier of the order.|
|`order > usageContext`|Details of the usage type that the Data User selected at<br>the time of order.|
|`order > createdBy`|Details of the user account that ordered the data<br>collection.|
|`order > createdOn`|Date when the order was created.|
|`order > modifiedBy`|Details of the latest user account that modified the<br>order.|
|`order > modifiedOn`|Latest date when the order was modified.|
|`order > costCenter`|Cost center of the Data User that ordered the data<br>collection.|
|`deliveryTarget`|Details of the delivery target that was used to deliver the<br>data to the Data User.|
|`deliveryTarget > id`|System generated unique identifier of the delivery target.|
|`deliveryTarget > name`|Name of the delivery target.|
|`deliveryTarget > description`|Description of the delivery target.|

|**Parameter**|**Description**|
|---|---|
|`deliveryTarget > isDefault `|Parameter that indicates whether the delivery target is<br>the default delivery option for a data collection. The<br>parameter can have one of the following values:<br>-<br>` true `. The delivery template is the default delivery<br>option for a data collection.<br>-<br>` false`. The delivery template isn't the default delivery<br>option for a data collection.|
|`deliveryTarget > status`|Status of the delivery target.|
|`deliveryTarget > physicalLocation`|Default location where the data is delivered.<br>**Note:**If the value of the` managedAccess ` parameter is<br>` ENABLED`, the data is not be delivered to the location that<br>is specified in the` physicalLocation` parameter.<br>Instead, the data will be delivered to a location that is<br>generated at the time of order fulfillment. The generated<br>location is unique to each order that is fulfilled using this<br>target.<br>For more information, see the_Integrate Data Marketplace_<br>_with other Informatica services_topic in the_Set Up Data_<br>_Marketplace_help.|
|`deliveryTarget > targetSystemReference`|Default system where the data is delivered.|
|`deliveryTarget > deliveryTemplate`|Details of the delivery template based on which the<br>delivery target was created.|
|`deliveryTarget > deliveryMethod`|Details of the delivery method.|
|`deliveryTarget > deliveryFormat`|Details of the delivery format.|
|`deliveryTarget > dataCollection`|Details of the data collection to which the delivery target<br>is assigned.|
|`deliveryTarget > createdBy`|Details of the user account that created the delivery<br>target.|
|`deliveryTarget > createdOn`|Date when the delivery target was created.|
|`deliveryTarget > modifiedBy`|Details of the latest user account that modified the<br>delivery target.|
|`deliveryTarget > modifiedOn`|Latest date when the delivery target was modified.|
|`dataCollection`|Details of the data collection for which the Data User was<br>granted access.|
|`dataCollection > id`|System generated unique identifier of the data collection.|
|`dataCollection > name`|Name of the data collection.|
|`dataCollection > description`|Purpose of the data collection.|
|`dataCollection > refId`|Reference identifier of the data collection.|


|**Parameter**|**Description**|
|---|---|
|`dataCollection > category`|Details of the category of the data collection.|
|`dataCollection > usageContexts`|Details of the usage type that is used to specify the<br>certified use of the data collection.|
|`dataCollection > dataset`|Details of the data assets that the data collection<br>contains.|
|`dataCollection > dataCollectionStatus `|The status determines whether the data collection is<br>discoverable by Data Users when they search for it. A<br>data collection can have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to<br>Data Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable<br>to Data Users.|
|`dataCollection > dataOwners`|Details of the stakeholders that are assigned the Data<br>Owner stakeholder role on the data collection.|
|`dataCollection > technicalOwners`|Details of the stakeholders that are assigned the<br>Technical Owner stakeholder role on the data collection.|
|`dataCollection > createdBy`|Details of the user account that created the data<br>collection.|
|`dataCollection > createdOn`|Date when the data collection was created.|
|`dataCollection > modifiedBy`|Details of the latest user account that modified the data<br>collection.|
|`dataCollection > modifiedOn`|Latest date when the data collection was modified.|
|`termsOfUseSnapshots`|Details of the terms of use snapshot represents the state<br>of the terms of use of the data collection when the order<br>was placed.|
|`consumer`|Details of the Data User that was granted access to the<br>data collection.|
|`usageContext`|Details of the usage context that the Data User selected<br>at the time of order.|
|`createdBy`|Details of the user account that created the consumer<br>access.|
|`createdOn`|Date when the consumer access was created.|
|`modifiedBy`|Details of the latest user account that modified the<br>consumer access.|
|`modifiedOn`|Latest date when the consumer access was modified.|

|**Parameter**|**Description**|
|---|---|
|`customFields`|The custom fields of the consumer access and their<br>values.<br>Custom attributes are additional properties for Data<br>Marketplace items that are defined by your administrator<br>in Metadata Command Center.<br>For more information about custom attributes, see the<br>_Create custom attributes for items_topic in the_Set Up_<br>_Data Marketplace_help.|
|`customFields > name`|The system generated unique identifier of the custom<br>attribute.<br>For more information about how you can retrieve the<br>custom attribute name as it appears on the Data<br>Marketplace interface, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value. This parameter can have only<br>a single value.|
|`customFields > values`|The custom attribute value. This parameter can have<br>multiple values.|


#### Withdraw consumer accesses
Use a REST API to withdraw a Data User's access to a data collection. 

#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v1/integration/consumerAccess/<consumerAccessId>/withdrawn** `<consumerAccessId>` : Required. Enter the system generated unique identifier of the consumer access for which you want to withdraw. For more information about how you can use an API to get the system generated unique identifier of a consumer access, see “Retrieve consumer accesses” on page 315. To get the system generated unique identifier of a consumer access from the Data Marketplace interface, open the consumer access. The consumer access page's URL contains the system generated unique identifier. For example, in the URL `https://{{CDMP_URL}}/access/e254491f-5795-49bdbe0b-385eb11d9d5a?dtn=Access~2e85 ` , the system generated unique identifier is ` e254491f-5795-49bd-be0b-385eb11d9d5a` . Method PUT 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|
|---|---|
|`comment`|Optional. Enter a comment about the action taken.|


**Note:** The API has no payload. 

#### Example request
The following example shows how you can use an API to withdraw a consumer access: 

```
https://{{CDMP_URL}}/api/v1/integration/consumerAccess/794a215f-5479-4b55-8e1b-
a4866ec9fe82/withdrawn?comment=Request%20Withdraw
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to withdraw a consumer access: 

```
{
  "id": "794a215f-5479-4b55-8e1b-a4866ec9fe82",
  "refId": "ACC-246",
  "status": "WITHDRAWN",
  "accessGrantedOn": "2022-09-14T13:30:36.405Z",
  "costCenter": "Retail-IQO3456",
  "order": {
    "justification": "Required for the Q3 sales reporting.",
    "deliveryRequests": null,
    "requestedProvisionedTargetRef": "b441a10a-d64d-4801-94d8-145e805001c4",
    "refId": "ORD-337",
    "dataCollections": {},
    "termsOfUseSnapshots": null,
    "status": "COMPLETE",
    "id": "b986ae59-7fc9-414c-98d7-14c7a1671023",
    "usageContext": null,
    "createdBy": {
      "displayName": "Johnny Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jydoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "createdOn": "2022-09-14T13:30:36.405Z",
    "modifiedBy": {
      "displayName": "Johnny Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jydoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "modifiedOn": "2022-09-14T13:30:36.405Z",
    "costCenter": "Retail-IQO3456"
  },

  "deliveryTarget": {

    "id": "b441a10a-d64d-4801-94d8-145e805001c4",
    "name": "IPFS Delivery",
    "description": "Use this template to deliver data via IPFS.",
    "isDefault": true,
    "status": "ACTIVE",
    "physicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
    "targetSystemReference": "IPFS",
    "deliveryTemplate": {
      "id": "1c865800-86ca-4280-8cdd-89dda66c013c",
      "name": "IPFS",
      "description": "Use this template to deliver data via IPFS.",
      "color": null,
      "isDefault": true,
      "status": "ACTIVE",
      "managedAccess": "DISABLED",
      "deliveryType": "AUTOMATIC",
      "templateOwners": [
        {
          "displayName": "Johnny Doe",
          "id": "6k7aaGYrSOsfN7RFlPH1YU",
          "name": "23Aug_CDMP_QE1",
          "email": "jydoe@informatica.com",
          "phone": "555-243-456",
          "status": "ACTIVE",
          "userInfo": {},
          "isGroup": true
        }
      ],
      "targetSystemReference": "IPFS",
      "defaultPhysicalLocation": "ipfs://{cid}/path/to/subresource/cat.jpg",
      "deliveryMethods": null,
      "deliveryFormats": null,
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "deliveryMethod": {
      "id": "f8f8as4d-c0f0-3r11-fr04-7b7h2ce46731",
      "name": "IPFS",
      "status": "ACTIVE",
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "deliveryFormat": {
      "id": "e0f8af4d-c0f0-4c02-af03-5b9b2ce43253",
      "name": "CSV",
      "status": "Deliver data as a CSV file.",
      "createdBy": "8npzPxKim0XjHE4es2gkAc",
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "dataCollection": {
      "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
      "name": "Retail Sales Report",
      "description": "Sales reports for the retail outlets.",
      "refId": "DCL-834",
      "category": null,
      "usageContexts": null,
      "dataset": null,
      "dataCollectionStatus": "PUBLISHED",
      "dataOwners": [],
      "technicalOwners": [],
      "createdBy": {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
```


```
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      },
      "createdOn": "2022-09-14T11:57:00.446Z",
      "modifiedBy": {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      },
      "modifiedOn": "2022-09-14T11:57:00.446Z"
    },
    "createdBy": "8npzPxKim0XjHE4es2gkAc",
    "createdOn": "2022-09-14T11:57:00.446Z",
    "modifiedBy": "8npzPxKim0XjHE4es2gkAc",
    "modifiedOn": "2022-09-14T11:57:00.446Z"
  },
  "dataCollection": {
    "id": "e1765e74-7907-4a8a-a622-3ed5532c796f",
    "name": "Retail Sales Report",
    "description": "Sales reports for the retail outlets.",
    "refId": "DCL-834",
    "category": null,
    "usageContexts": null,
    "dataset": null,
    "dataCollectionStatus": "PUBLISHED",
    "dataOwners": [
      {
        "displayName": "Janet Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jtdoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      }
    ],
    "technicalOwners": [
      {
        "displayName": "Johnny Doe",
        "id": "6k7aaGYrSOsfN7RFlPH1YU",
        "name": "23Aug_CDMP_QE1",
        "email": "jydoe@informatica.com",
        "phone": "555-243-456",
        "status": "ACTIVE",
        "userInfo": {},
        "isGroup": true
      }
    ],
    "createdBy": {
      "displayName": "Jane Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",
      "email": "jdoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "createdOn": "2022-09-14T11:57:00.446Z",
    "modifiedBy": {
      "displayName": "Jane Doe",
      "id": "6k7aaGYrSOsfN7RFlPH1YU",
      "name": "23Aug_CDMP_QE1",

      "email": "jdoe@informatica.com",
      "phone": "555-243-456",
      "status": "ACTIVE",
      "userInfo": {},
      "isGroup": true
    },
    "modifiedOn": "2022-09-14T11:57:00.446Z"
  },
  "termsOfUseSnapshots": [
    {
      "id": "e37bf0c7-faf3-4902-8bcc-485d80cc8bb9",
      "termsOfUseId": null,
      "termsOfUseRefId": null,
      "generalTermsOfUse": "This data must not be accessed via unauthorized devices.",
      "name": null,
      "description": null,
      "type": null,
      "status": null,
      "acknowledgement": null,
      "parentId": "null,
      "referenceLink": null,
      "createdOn": "2022-09-14T11:57:00.446Z"
    }
  ],
  "consumer": {
    "displayName": "John Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "usageContext": {
    "id": "9669f6ab-affc-4106-a583-48a6b580b930",
    "name": "Sales Use",
    "description": "This is for sales reporting tasks.",
    "color": null,
    "status": "ACTIVE"
  },
  "createdBy": {
    "displayName": "Jane Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "createdOn": "2022-09-14T13:30:36.726Z",
  "modifiedBy": {
    "displayName": "Jane Doe",
    "id": "6k7aaGYrSOsfN7RFlPH1YU",
    "name": "23Aug_CDMP_QE1",
    "email": "jdoe@informatica.com",
    "phone": "555-243-456",
    "status": "ACTIVE",
    "userInfo": {},
    "isGroup": true
  },
  "modifiedOn": "2022-09-15T13:59:40.472Z",
  "customFields": [
        {
          "name": "Year",
          "value": "2021",
          "values": null
       }
}
```


The following table describes the parameters of each consumer access that is withdrawn: 

|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the consumer<br>access.|
|`refId`|Reference identifier of the consumer access.|
|`status `|Status of the consumer access.<br>A consumer access can have the following statuses:<br>-<br>` AVAILABLE `. The Data User has access to the data<br>collection.<br>-<br>` PENDING_WITHDRAW `. A request is submitted to<br>withdraw the Data User's access to the data collection.<br>-<br>` WITHDRAWN`. The Data User's access to the data<br>collection is withdrawn.|
|`accessGrantedOn`|Date on which the Data User was granted access to the<br>data collection.|
|`costCenter`|Cost center of the Data User that was granted access to<br>the data collection.|
|`order`|Details of the order placed by the Data User that was<br>granted access to the data collection.|
|`order > justification`|The reason entered at the time of order by the Data User<br>to the stakeholder for the requirement of access to the<br>data.|
|`order > deliveryRequests`|Delivery information of the order.|
|`order > requestedProvisionedTargetRef `|The delivery target requested by the Data User.<br>**Note:**If the value of the<br>` requestedProvisionedTargetRef ` parameter is<br>` null`, the order is created without a specific delivery<br>target but rather uses the default delivery template.<br>For more information about how you can retrieve the<br>details of the default delivery template, see<br>“Retrieve delivery templates” on page 152.|
|`order > refId`|Reference identifier of the order.|
|`order > dataCollections`|Details of the ordered data collection.|
|`order > termsOfUseSnapshots`|Details of the terms of use snapshot that represents the<br>state of the terms of use of the data collection when the<br>order was placed.|
|`order > status`|Status of the order.|
|`order > id`|System generated unique identifier of the order.|
|`order > usageContext`|Details of the usage type that the Data User selected at<br>the time of order.|

|**Parameter**|**Description**|
|---|---|
|`order > createdBy`|Details of the user account that ordered the data<br>collection.|
|`order > createdOn`|Date when the order was created.|
|`order > modifiedBy`|Details of the latest user account that modified the<br>order.|
|`order > modifiedOn`|Latest date when the order was modified.|
|`order > costCenter`|Cost center of the Data User that ordered the data<br>collection.|
|`deliveryTarget`|Details of the delivery target that was used to deliver the<br>data to the Data User.|
|`deliveryTarget > id`|System generated unique identifier of the delivery target.|
|`deliveryTarget > name`|Name of the delivery target.|
|`deliveryTarget > description`|Description of the delivery target.|
|`deliveryTarget > isDefault `|Parameter that indicates whether the delivery target is<br>the default delivery option for a data collection. The<br>parameter can have one of the following values:<br>-<br>` true `. The delivery template is the default delivery<br>option for a data collection.<br>-<br>` false`. The delivery template isn't the default delivery<br>option for a data collection.|
|`deliveryTarget > status`|Status of the delivery target.|
|`deliveryTarget > physicalLocation`|Default location where the data is delivered.<br>**Note:**If the value of the` managedAccess ` parameter is<br>` ENABLED`, the data is not delivered to the location that is<br>specified in the` physicalLocation` parameter. Instead,<br>the data will be delivered to a location that is generated<br>at the time of order fulfillment. The generated location is<br>unique to each order that is fulfilled using this target.<br>For more information, see the_Integrate Data Marketplace_<br>_with other Informatica services_topic in the_Set Up Data_<br>_Marketplace_help.|
|`deliveryTarget > targetSystemReference`|Default system where the data is delivered.|
|`deliveryTarget > deliveryTemplate`|Details of the delivery template based on which the<br>delivery target was created.|
|`deliveryTarget > deliveryMethod`|Details of the delivery method.|
|`deliveryTarget > deliveryFormat`|Details of the delivery format.|
|`deliveryTarget > dataCollection`|Details of the data collection to which the delivery target<br>is assigned.|


|**Parameter**|**Description**|
|---|---|
|`deliveryTarget > createdBy`|Details of the user account that created the delivery<br>target.|
|`deliveryTarget > createdOn`|Date when the delivery target was created.|
|`deliveryTarget > modifiedBy`|Details of the latest user account that modified the<br>delivery target.|
|`deliveryTarget > modifiedOn`|Latest date when the delivery target was modified.|
|`dataCollection`|Details of the data collection for which the Data User was<br>granted access.|
|`dataCollection > id`|System generated unique identifier of the data collection.|
|`dataCollection > name`|Name of the data collection.|
|`dataCollection > description`|Purpose of the data collection.|
|`dataCollection > refId`|Reference identifier of the data collection.|
|`dataCollection > category`|Details of the category of the data collection.|
|`dataCollection > usageContexts`|Details of the usage type that is used to specify the<br>certified use of the data collection.|
|`dataCollection > dataset`|Details of the data assets that the data collection<br>contains.|
|`dataCollection > dataCollectionStatus `|The status indicates whether the data collection is<br>discoverable by Data Users when they search for it. A<br>data collection can have one of the following statuses:<br>-<br>` PUBLISHED `. The data collection is discoverable to<br>Data Users.<br>-<br>` UNPUBLISHED`. The data collection isn't discoverable<br>to Data Users.|
|`dataCollection > dataOwners`|Details of the stakeholders that are assigned the Data<br>Owner stakeholder role on the data collection.|
|`dataCollection > technicalOwners`|Details of the stakeholders that are assigned the<br>Technical Owner stakeholder role on the data collection.|
|`dataCollection > createdBy`|Details of the user account that created the data<br>collection.|
|`dataCollection > createdOn`|Date when the data collection was created.|
|`dataCollection > modifiedBy`|Details of the latest user account that modified the data<br>collection.|
|`dataCollection > modifiedOn`|Latest date when the data collection was modified.|

|**Parameter**|**Description**|
|---|---|
|`termsOfUseSnapshots`|Details of the terms of use snapshot that represents the<br>state of the terms of use of the data collection when the<br>order was placed.|
|`consumer`|Details of the Data User that was granted access to the<br>data collection.|
|`usageContext`|Details of the usage context that the Data User selected<br>at the time of order.|
|`createdBy`|Details of the user account that created the consumer<br>access.|
|`createdOn`|Date when the consumer access was created.|
|`modifiedBy`|Details of the latest user account that modified the<br>consumer access.|
|`modifiedOn`|Latest date when the consumer access was modified.|
|`customFields`|The custom fields of the consumer access and their<br>values.<br>Custom attributes are additional properties for Data<br>Marketplace items that are defined by your administrator<br>in Metadata Command Center.<br>For more information about custom attributes, see the<br>_Create custom attributes for items_topic in the_Set Up_<br>_Data Marketplace_help.|
|`customFields > name`|The system generated unique identifier of the custom<br>attribute.<br>For more information about how you can retrieve the<br>custom attribute name as it appears on the Data<br>Marketplace interface, see<br>“Retrieve custom attributes” on page 353.|
|`customFields > value`|The custom attribute value. This parameter can have only<br>a single value.|
|`customFields > values`|The custom attribute value. This parameter can have<br>multiple values.|


#### Delete consumer accesses
Use a REST API to delete withdrawn consumer accesses in Data Marketplace. 


#### Endpoint and method
The following table describes the connection properties for the API: 

**Property Description** Endpoint **/api/v1/integration/consumerAccess/<consumerAccessId>** `<consumerAccessId>` : Required. Enter the system generated unique identifier of the consumer access that you want to delete. For more information about how you can use an API to get the system generated unique identifier of a consumer access, see “Retrieve consumer accesses” on page 315. To get the system generated unique identifier of a consumer access from the Data Marketplace interface, open the consumer access. The consumer access page's URL contains the system generated unique identifier. For example, in the URL `https://{{CDMP_URL}}/access/e254491f-5795-49bdbe0b-385eb11d9d5a?dtn=Access~2e85 ` , the system generated unique identifier is ` e254491f-5795-49bd-be0b-385eb11d9d5a` . Method DELETE 

To invoke this API, ensure your user profile is assigned the Data Marketplace Administrator role. 

Alternatively, ensure that your user profile is assigned a role for which the following privileges and permissions are enabled: 

- **Delete** and **Read** permissions are enabled in Metadata Command Center for consumer accesses. 

- **Access Data Marketplace** privilege is enabled in Administrator. 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 

#### Example request
The following example shows how you can use an API to delete a consumer access: 

```
https://{{CDMP_URL}}/api/v1/integration/consumerAccess/794a215f-5479-4b55-8e1b-
a4866ec9fe82
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the consumer access is deleted successfully: 

```
204 OK code
```

### C h a p t e r 1 0
## Chapter 10: Custom attributes
Custom attributes are additional properties for Data Marketplace objects that are defined by your Administrator in Metadata Command Center. 

A stakeholder of a data collection can use custom attributes to specify additional information about a data collection when the standard properties of the collection are insufficient. Additionally, Data Users that order the data collection can use the newly added property to provide more information when they place an order. 

For more information about custom attributes, see the _Create custom attribute for items_ topic in the _Set Up Data Marketplace_ help. 

### Retrieve custom attributes
Use REST APIs to retrieve the custom attribute details for a consumer access, data collection or an order. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/model/customAttributes**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`classType `|Required. Specify the type of item<br>for which you want to retrieve the<br>custom attribute details.|Enter one of the following values:<br>- To retrieve the custom attribute details of a<br>consumer access, enter<br>` com.infa.cdmp.marketplace.Consume `<br>` rAccess `.<br>- To retrieve the custom attribute details of a<br>data collection, enter<br>` com.infa.cdmp.marketplace.DataCol `<br>` lection `.<br>- To retrieve the custom attribute details of<br>an order, enter<br>` com.infa.cdmp.marketplace.Order`.|
|`offset`|Optional. Enter the starting index<br>for the paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum<br>number of results.|Default is` 50`.<br>Maximum is` 100`.|


#### Example request
The following example shows how you can use an API to retrieve the custom attribute details for a data collection: 

```
https://{{CDMP_URL}}/api/v1/integration/model/customAttributes?
classType=com.infa.cdmp.marketplace.DataCollection&offset=0&limit=100
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the custom attribute details for a data collection: 

```
{
  "processingTime": 863,
  "offset": 0,
  "limit": 100,
  "totalCount": 2,
  "classType": "com.infa.cdmp.marketplace.DataCollection",
  "items": [
      {
         "customAttributeId": "com.infa.odin.models.custom.ca_7821688086685433570",
         "name": "GDPR Compliance Required",
         "status": "PUBLISHED",
         "mandatory": true,
         "searchable": false,
         "defaultValues": ["false"],
         "datatype":
             {
                "type": "BOOLEAN",
                "properties": null,
                "dropDownOptions": null

             }
      },
      {
         "customAttributeId": "com.infa.odin.models.custom.ca_3184028726397751640",
         "name": "Date of Issue",
         "status": "PUBLISHED",
         "mandatory": false,
         "searchable": false,
         "defaultValues": ["2023-09-05"],
         "datatype":
             {
                "type": "DATE",
                "properties": null,
                "dropDownOptions": null
             }
      },
      {
         "customAttributeId": "com.infa.odin.models.custom.ca_6928840635020975219",
         "name": "Consumption Rate",
         "status": "PUBLISHED",
         "mandatory": true,
         "searchable": true,
         "defaultValues": ["45.6"],
         "datatype":
             {
                "type": "DECIMAL",
                "properties": null,
                "dropDownOptions": null
             }
      },
      {
         "customAttributeId": "com.infa.odin.models.custom.ca_7907323524500803261",
         "name": "Requirement",
         "status": "PUBLISHED",
         "mandatory": true,
         "searchable": false,
         "defaultValues": ["Secure Data Transfer"],
         "datatype":
             {
                "type": "RICH_TEXT",
                "properties": null,
                "dropDownOptions": null
             }
      },
      {
         "customAttributeId": "com.infa.odin.models.custom.ca_1535512762047882544",
         "name": "User Count",
         "status": "PUBLISHED",
         "mandatory": true,
         "searchable": false,
         "defaultValues": ["10"],
         "datatype":
             {
                "type": "INTEGER",
                "properties": null,
                "dropDownOptions": null
             }
      },
      {
         "customAttributeId": "com.infa.odin.models.custom.ca_8610145587620226269",
         "name": "Network Stack",
         "status": "PUBLISHED",
         "mandatory": false,
         "searchable": false,
         "defaultValues": ["Reticulum"],
         "datatype":
             {
                "type": "DROPDOWN_SINGLE_SELECT",
                "properties": {"multivalued": "false"},
                "dropDownOptions": [
                       {
```


```
                          "value": "Reticulum",
                          "label": "Reticulum"
                       },
                       {
                          "value": "libp2p",
                          "label": "libp2p"
                       }
                  ]
             }
      },
      {
         "customAttributeId": "com.infa.odin.models.custom.ca_5588843841075376340",
         "name": "Network Protocol",
         "status": "PUBLISHED",
         "mandatory": true,
         "searchable": false,
         "defaultValues": ["LXMF"],
         "datatype":
             {
                "type": "PLAIN_TEXT",
                "properties": null,
                "dropDownOptions": null
             }
      },
      {
         "customAttributeId": "com.infa.odin.models.custom.ca_1007408542741800522",
         "name": "Is Critical",
         "status": "PUBLISHED",
         "mandatory": true,
         "searchable": true,
         "defaultValues": ["false"],
         "datatype":
             {
                "type": "BOOLEAN",
                "properties": null,
                "dropDownOptions": null
             }
      },
      {
         "customAttributeId": "com.infa.odin.models.custom.ca_3068304325528979652",
         "name": "Cloud Platform",
         "status": "PUBLISHED",
         "mandatory": false,
         "searchable": true,
         "defaultValues": ["Apache CloduStack"],
         "datatype":
             {
                "type": "DROPDOWN_MULTI_SELECT",
                "properties": {"multivalued": "true"},
                "dropDownOptions": [
                       {
                          "value": "Apache CloduStack",
                          "label": "Apache CloduStack"
                       },
                       {
                          "value": "Microsoft Azure",
                          "label": "Microsoft Azure"
                       },
                       {
                          "value": "Google Cloud Platform",
                          "label": "Google Cloud Platform"
                       },
                       {
                          "value": "Amazon Web Services",
                          "label": "Amazon Web Services"
                       }
                    ]
                }
            }
        ]
  }
```

The following table describes the parameters of each custom attribute that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`totalCount`|Number of custom attributes retrieved.|
|`classType `|Type of item for which the custom attribute details were<br>retrieved.<br>This parameter can have one of the following values:<br>- If the parameter value is<br>` com.infa.cdmp.marketplace.ConsumerAccess `,<br>the item is a consumer access.<br>- If the parameter value is<br>` com.infa.cdmp.marketplace.DataCollection `,<br>the item is a data collection.<br>- If the parameter value is<br>` com.infa.cdmp.marketplace.Order`, the item is<br>an order.|
|`items > customAttributeId`|System generated unique identifier of the custom<br>attribute in Metadata Command Center.|
|`items > name`|Name of the custom attribute as configured in Metadata<br>Command Center.|
|`items > status`|Status of the custom attribute as configured in Metadata<br>Command Center.<br>This parameter returns only the custom attributes that<br>are in` PUBLISHED` state. The` PUBLISHED` state indicates<br>that the custom attributes are displayed in Data<br>Marketplace.|
|`items > mandatory `|This parameter indicates whether or not the custom<br>attribute is mandatory.<br>This parameter can have one of the following values:<br>-<br>` true `. A user must enter a value in this attribute.<br>-<br>` false`. A user doesn't require to enter a value in this<br>attribute.|
|`items > searchable `|This parameter indicates whether or not you can use the<br>custom attribute to search for a data collection.<br>This parameter can have one of the following values:<br>-<br>` true `. You can use the custom attribute to search for a<br>data collection.<br>-<br>` false`. You can't use the custom attribute to search<br>for a data collection.|
|`items > defaultValues`|Default value of the custom attribute as configured in<br>Metadata Command Center.|
|`items > datatype > type`|Type of the custom attribute as configured in Metadata<br>Command Center.|


|**Parameter**|**Description**|
|---|---|
|`items > datatype > properties`|Subtype of the custom attribute as configured in<br>Metadata Command Center.<br>The value of the` properties` parameter depends on the<br>value configured for the` type` parameter.|
|`items > datatype > dropDownOptions `|The values that are acceptable inputs to the custom<br>attribute, as configured in Metadata Command Center.<br>**Note:**This parameter is displayed in the response body<br>only if the custom attribute is of type<br>` DROPDOWN_SINGLE_SELECT`.|

### C h a p t e r 1 1
## Chapter 11: Collaboration on objects
You can collaborate with other users in Data Marketplace. This allows you to enhance the quality of data that is available in your organization's Data Marketplace, and allows you to extract more business value from your organization's data. 

You can collaborate with other users in the following sections of Data Marketplace: 

- On the **Chat** panel of a data asset, data collection or category. 

- On the **Timeline** section of an order, consumer access or data collection request. 

### Comment on an object
Use REST APIs to add a comment on one of the following sections in Data Marketplace: 

- On the **Chat** panel of a data asset, data collection or category. 

- On the **Timeline** section of an order, consumer access or data collection request. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/integration/collaboration/<objectId>/interactions/comments**|
||`<objectId>`: Required. Enter the system generated unique identifier of the object to which you want to<br>add a comment.|
||For more information about how you can retrieve the system generated unique identifier of an object,<br>“How do I retrieve the system-generated unique identifier of a Data Marketplace item?” on page 373.|
|Method|POST|


#### **Note:**
- You can publish a maximum of 100 comments per object. 

- You can publish only one comment per API call. 

- You can call a maximum of 100 APIs per minute. 

For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`text`|Required. Enter your comment.|Enter a comment with length from 0 through<br>500 characters.<br>**Note:**If you use an API to add a comment, you<br>cannot tag a user in your comment. To tag a<br>user, add a comment from the Data<br>Marketplace user interface. For more<br>information, see the_Collaboration in Data_<br>_Marketplace_topic in the_Introduction and_<br>_Getting Started_help.|


#### Example request
The following example shows how you can use an API to add a comment to a data collection: 

```
{
  "text": "This data collection does not cover the EMEA region data."
}
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to add a comment to a data collection: 

```
{
}

  "id": "49d4856a-90b3-3584-abd6-47db0fe9acb6"
```

The following table describes the parameters of each custom field that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`id`|System generated unique identifier of the comment that<br>you added.|


### Retrieve comments on an object
Use REST APIs to retrieve the comments that are added to one of the following sections in Data Marketplace: 

- On the **Chat** panel of a data asset, data collection or category. 

- On the **Timeline** section of an order, consumer access or data collection request.

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/integration/collaboration/<objectId>/interactions/comments**|
||`<objectId>`: Required. Enter the system generated unique identifier of the object to which you want to<br>add a comment.|
||For more information about how you can retrieve the system generated unique identifier of an object,<br>“How do I retrieve the system-generated unique identifier of a Data Marketplace item?” on page 373.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The following table describes the parameters that you enter in the request query: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`offset`|Optional. Enter the starting index<br>for the paginated results.|Default is` 0`.|
|`limit`|Optional. Enter the maximum<br>number of results.|Default is` 50`.<br>Maximum is` 100`.|


#### Example request
The following example shows how you can use an API to retrieve the comments that are added to a data collection: 

```
https://{{CDMP_URL}}/api/v1/integration/collaboration/8c4c2089-6c7e-4696-
a8f6-44f87639d65c/interactions/comments
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of an API call to retrieve the comments that are added to a data collection: 

```
{
  "offset": 0,
  "limit": 50,
  "totalCount": 1,
  "objects": [
    {
      "id": "49d4856a-90b3-3584-abd6-47db0fe9acb6",
      "text": "This data collection does not cover the EMEA region data.",
      "createdBy": "7w2uGIARApUgxniNoHHWsJ",
      "createdOn": "2022-03-08T17:05:28.208Z",
      "modifiedBy": "7w2uGIARApUgxniNoHHWsJ",
```


```
      "modifiedOn": "2022-03-08T17:09:34.623Z"
    }
  ]

}
```

The following table describes the parameters of each custom field that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Starting index for the paginated results.|
|`limit`|Maximum number of results.|
|`id`|System generated unique identifier of the comment.|
|`text`|Contents of the comment.|
|`createdBy`|System generated unique identifier of the user account<br>that added the comment.|
|`createdOn`|Date when the comment was added.|
|`modifiedBy`|System generated unique identifier of the latest user<br>account that modified the comment.|
|`modifiedOn`|Latest date when the comment was modified.|

### C h a p t e r 1 2
## Chapter 12: Data Marketplace customizations
You can personalize the look and feel of Data Marketplace according to your organization's requirements. You must have the Data Marketplace Administrator profile to customize the look and feel of your organization's Data Marketplace instance. 

### Retrieve customizations
Use REST APIs to retrieve the details of the customizations applied to your Data Marketplace instance. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/admin/settings/customizations/lookandfeel**|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 

#### Example request
The following example shows how you can use an API to retrieve the details of the customizations applied to your Data Marketplace instance: 

```
https://{{CDMP_URL}}/api/v1/integration/admin/settings/customizations/lookandfeel
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered.

The following example shows the response of the API call: 

```
{
  "processingTime": 1015,
  "orgLogoURL": "string",
  "primaryColor": "#039FB0",
  "backgroundColor": "#EAEAEA",
  "borderRadius": "11"
}
```

The following table describes the parameters in the response body: 

|**Parameter**|**Description**|
|---|---|
|`orgLogoURL`|URL to the location that contains the logo of your<br>organization.|
|`primaryColor`|Primary color of the Data Marketplace interface. This is<br>the color used by the buttons, checkbox and other such<br>user interface elements.|
|`backgroundColor`|Background color of a page.|
|`borderRadius`|Border radius of the buttons on the Data Marketplace<br>interface.|


### Update customizations
Use REST APIs to update the customizations applied to your Data Marketplace instance. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/admin/settings/customizations/lookandfeel**|
|Method|PATCH|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15.

#### Request
The following table describes the parameters that you enter in the body of the API payload: 

|**Parameter**|**Description**|**Additional Information**|
|---|---|---|
|`orgLogoURL`|Optional. Enter the URL to the location that<br>contains the logo of your organization.|-|
|`primaryColor`|Optional. Specify a primary color for the Data<br>Marketplace interface. This is the color used<br>by the buttons, checkbox and other such user<br>interface elements.|Enter a hexadecimal value that<br>represents the color that you<br>want to use.|
|`backgroundColor`|Optional. Specify a background color for a<br>page.|Enter a hexadecimal value that<br>represents the color that you<br>want to use.|
|`borderRadius`|Optional. Specify a border radius for the<br>buttons on the Data Marketplace interface.|Enter a value between 0 and 20.|


#### Example request
The following example shows how you can use an API to update the customizations applied to your Data Marketplace instance: 

```
{
  "orgLogoURL": "https://www.informatica.com/",
  "primaryColor": "#039FB0",
  "backgroundColor": "#EAEAEA",
  "borderRadius": 11
}
```

#### Response
When you pass the API payload in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of the API call: 

```
{
  "processingTime": 1015,
  "orgLogoURL": "string",
  "primaryColor": "#039FB0",
  "backgroundColor": "#EAEAEA",
  "borderRadius": "11"
}
```


The following table describes the parameters in the response body: 

|**Parameter**|**Description**|
|---|---|
|`orgLogoURL`|URL to the location that contains the logo of your<br>organization.|
|`primaryColor`|Primary color of the Data Marketplace interface. This is<br>the color used by the buttons, checkbox and other such<br>user interface elements.|
|`backgroundColor`|Background color of a page.|
|`borderRadius`|Border radius of the buttons on the Data Marketplace<br>interface.|


### Reset customizations
Use REST APIs to reset the customizations applied to your Data Marketplace instance. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/admin/settings/customizations/lookandfeel**|
|Method|DELETE|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload. 

#### Example request
The following example shows how you can use an API to reset the customizations applied to your Data Marketplace instance: 

```
https://{{CDMP_URL}}/api/v1/integration/admin/settings/customizations/lookandfeel
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered.

If you see the following response code, it means that the customizations applied to your Data Marketplace instance are reset successfully 

```
204 OK code
```


### C h a p t e r 1 3
## Chapter 13: Technical content
A technical content is a specialized payload information that allows Data Marketplace to seamlessly interface with external data repositories. 

This API allows you to specify the type of data that must be supplied from Data Marketplace to the external data repository, and the type of data that must be supplied from the external data repository to Data Marketplace, to allow seamless interaction between the two. 

For example, to use data from an object data store to create a new data collection in your organization's data lake. After a Data User's data collection request in Data Marketplace is approved, you can use this API via an Application Integration IPD process to retrieve the request details, and compile them into a standardized data interchange file such as a JavaScript Object Notation (JSON) manifest file. After this, Application Integration sends this file to IDMC Data Integration. Data Integration stages the data into the data lake, and then reports the outcome of the staging operation to Application Integration. At this stage, Application Integration calls the “Create data collections” on page 212 API to create a new data collection in Data Marketplace. 

### Retrieve the technical content of an object
Use a REST API to retrieve the technical content of an object. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/technicalContent/<objectId>**|
||`<objectId>`: Required. Enter the system generated identifier of the Data Marketplace object for which<br>you want to retrieve the API payload.|
||For more information about how you can retrieve the system generated unique identifier of an object, seeChapter<br>1<br>4<br>,<br>“Chapter 14: Frequently Asked Questions” on page 372.|
|Method|GET|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 

#### Request
The API has no payload.

#### Example request
The following example shows how you can use an API to retrieve the technical content: 

```
https://{{CDMP_URL}}/api/v1/integration/technicalContent/794a215f-5479-4b55-8e1b-
a4866ec9fe82
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

The following example shows the response of the API call: 

```
{
  "content": "string"
}
```

The following table describes the parameters of each technical content that is retrieved: 

|**Parameter**|**Description**|
|---|---|
|`content`|Specialized payload information that allows Data<br>Marketplace to interface with non-Informatica products.|


### Update the technical content of an object
Use a REST API to update the technical content of an object. 

#### Endpoint and method
The following table describes the connection properties for the API: 

|**Property**|**Description**|
|---|---|
|Endpoint|**/api/v1/integration/technicalContent/<objectId>**|
||`<objectId>`: Required. Enter the system generated identifier of the Data Marketplace object for which<br>you want to replace the API payload.|
||For more information about how you can retrieve the system generated unique identifier of an object, seeChapter<br>1<br>4<br>,<br>“Chapter 14: Frequently Asked Questions” on page 372.|
|Method|PUT|


For more information about how you can call an API, see “Authenticate using Application Integration” on page 15. 


#### Request
The following table describes the parameters that you enter in the request query: 

**Parameter Description** `content` Required. Enter the specialized payload information that allows Data Marketplace to interface with nonInformatica products. 

#### Example request
The following example shows how you can use an API to update the technical content: 

```
{
  "content": "string"
}
```

#### Response
When you pass the API query parameters in the REST client, the client displays a response for the parameter values that you have entered. 

If you see the following response code, it means that the technical content is updated successfully: 

```
204 OK code
```

# Part IV: Frequently Asked Questions
This part contains the following chapter: 

- Frequently Asked Questions, 372

### C h a p t e r 1 4
## Chapter 14: Frequently Asked Questions
This chapter includes the following topics: 

- When to authenticate with JWT and when with Application Integration?, 372 

- Can I use data360 endpoints to design an Application Integration process?, 372 

- How do I retrieve the system-generated unique identifier of a Data Marketplace item?, 373 

- How do I retrieve the system-generated unique identifier of a user account or user group?, 376 

- How do I retrieve the system-generated unique identifier of an asset group or user role?, 376 

### When to authenticate with JWT and when with Application Integration?
Some Data Marketplace APIs support both JWT and Application Integration authentication, while others require you to only use JWT authentication or use Application Integration. For APIs that support both methods of authentication, JWT provides you with greater flexibility because it works with REST clients, cURL, and can also be used to design Application Integration processes. Use Application Integration for APIs that exclusively require this type of authentication. 

### Can I use data360 endpoints to design an Application Integration process?
Yes, you can use a Data Marketplace API with a base URL in the following format to design an Application Integration process: 

```
https://idmc-api.dm-<region>.informaticacloud.com/data360/marketplace/
```

For more information about Data Marketplace API that use the data360 endpoint, see “Authenticate using JWT” on page 21. For more information about how you can design an Application Integration process, see the _Design_ help in Application Integration.

### How do I retrieve the system-generated unique identifier of a Data Marketplace item?
Depending on the type of item, you can get the unique identifier from the user interface or using an API. The following table explains how you can get the unique identifier of various items: 

|**Item**|**Using an API**|**From the User Interface**|
|---|---|---|
|Category|For more information about how you<br>can use an API to get the system-generated unique identifier of a<br>category, see<br>“Retrieve all categories” on page 31.|To get the system-generated<br>unique identifier of a category<br>from the Data Marketplace user<br>interface, open the category. The<br>category page's URL contains the<br>system-generated unique<br>identifier.<br>For example, in the URL<br>`https://{{CDMP_URL}}/category/view?`<br>`ac=67417f72-e5ab-44f0-add9-`<br>`a1e412c1ce13&dtn=_AfterEB `<br>` F%20may20 `, the system-generated unique identifier is<br>` 67417f72-e5ab-44f0-add9-a1e412c1ce13`.|
|Cost center|For more information about how you<br>can use an API to get the system-generated unique identifier of a cost<br>center, see<br>“Retrieve cost centers” on page 183.|-|
|Delivery format|For more information about how you<br>can use an API to get the system-generated unique identifier of a<br>delivery format, see<br>“Retrieve delivery formats” on page 136.|-|
|Delivery method|For more information about how you<br>can use an API to get the system-generated unique identifier of a<br>delivery method, see<br>“Retrieve delivery methods” on page 143.|-|
|Delivery template|For more information about how you<br>can use an API to get the system-generated unique identifier of a<br>delivery template, see<br>“Retrieve delivery templates” on page 152.|-|
|Delivery target|For more information about how you<br>can use an API to get the system-generated unique identifier of a<br>delivery target, see<br>“Retrieve all delivery targets” on page 77.|-|


|**Item**|**Using an API**|**From the User Interface**|
|---|---|---|
|Terms of use|For more information about how you<br>can use an API to get the system-generated unique identifier of a<br>terms of use, see<br>“Retrieve terms of use” on page 165.|-|
|Usage type|For more information about how you<br>can use an API to get the system-generated unique identifier of a<br>usage type, see<br>“Retrieve usage type” on page 174.|-|
|Data element|For more information about how you<br>can use an API to get the system-generated unique identifier of a data<br>element, see<br>“Retrieve data elements” on page 191.|-|
|Data asset|For more information about how you<br>can use an API to get the system-generated unique identifier of a data<br>asset, see<br>“Retrieve data assets” on page 203.|To get the system-generated<br>unique identifier of a data asset<br>from the Data Marketplace user<br>interface, open the data asset.<br>The data asset page's URL<br>contains the unique identifier.<br>For example, in the URL<br>`https://{{CDMP_URL}}/dataAsset/`<br>`8c4c2089-6c7e-4696-a8f6-44f87639d65c?`<br>`dtn=Table_Profiling_AK_16 `<br>` 76302415587&tab=dataEleme `<br>` nts `, the unique identifier is<br>` 8c4c2089-6c7e-4696-a8f6-44f87639d65c`.|
|Data collection|For more information about how you<br>can use an API to get the system-generated unique identifier of a data<br>collection, see<br>“Retrieve all data collections” on page 51.|To get the system-generated<br>unique identifier of a data<br>collection from the Data<br>Marketplace user interface, open<br>the data collection. The data<br>collection page's URL contains<br>the unique identifier.<br>For example, in the URL<br>`https://{{CDMP_URL}}/datacollection/`<br>`25158afc-3dfb-44ef-8f3e-cec1e171d0f1?`<br>`dtn=&tab=summary `, the unique<br>identifier is<br>` 25158afc-3dfb-44ef-8f3e-cec1e171d0f1`.|

|**Item**|**Using an API**|**From the User Interface**|
|---|---|---|
|Data collection<br>request|For more information about how you<br>can use an API to get the system-generated unique identifier of a data<br>collection request, see<br>“Retrieve data collection requests” on page 258.|To get the system-generated<br>unique identifier of a data<br>collection request from the Data<br>Marketplace user interface, open<br>the data collection request. The<br>data collection request page's<br>URL contains the unique<br>identifier.<br>For example, in the URL<br>`https://{{CDMP_URL}}/datacollectionrequest/`<br>`6e5964ec-e0aa-4eac-963a-3dae0fa1d6 `<br>` 39?dtn=Request~9b4d`, the<br>unique identifier is` 6e5964ec-e0aa-4eac-963a-3dae0fa1d6 `<br>` 39`.|
|Order|For more information about how you<br>can use an API to get the system-generated unique identifier of an<br>order, see<br>“Retrieve all orders” on page 94.|To get the system-generated<br>unique identifier of a order from<br>the Data Marketplace user<br>interface, open the order. The<br>order page's URL contains the<br>unique identifier.<br>For example, in the URL<br>`https://{{CDMP_URL}}/order/`<br>`3d48daf6-5e75-4e1a-848c-b6821fc33f74?`<br>`dtn=Order~579b `, the unique<br>identifier is<br>` 3d48daf6-5e75-4e1a-848c-b6821fc33f74`.|
|Consumer access|For more information about how you<br>can use an API to get the system-generated unique identifier of a<br>consumer access, see<br>“Retrieve all consumer accesses” on page 111.|To get the system-generated<br>unique identifier of a consumer<br>access from the Data<br>Marketplace user interface, open<br>the consumer access. The<br>consumer access page's URL<br>contains the unique identifier.<br>For example, in the URL<br>`https://{{CDMP_URL}}/access/`<br>`e254491f-5795-49bd-be0b-385eb11d9d5a?`<br>`dtn=Access~2e85 `, the unique<br>identifier is<br>` e254491f-5795-49bd-be0b-385eb11d9d5a`.|
|Custom attributes<br>of an item|For more information about how you<br>can retrieve the system-generated<br>unique identifier of a custom<br>attribute, see<br>“Retrieve custom attributes” on page 353.|-|


### How do I retrieve the system-generated unique identifier of a user account or user group?
#### **How do I retrieve the system-generated unique identifier of a user account?**
To get the system-generated unique identifier of a user account, click **My Services > Administrator > Users** . On the **Users** page, click a user account. The user account page's URL contains the unique identifier. 

For example, in the URL `/cloudUI/products/administer/main/usersAsset/0LH3xBJC9A6haZ26htGZyT ` , the unique identifier is ` 0LH3xBJC9A6haZ26htGZyT` . 

#### **How do I retrieve the system-generated unique identifier of a user group?**
To get the system-generated unique identifier of a user group, click **My Services > Administrator > User Groups** . On the **User Groups** page, click a user group. The user group page's URL contains the unique identifier. 

For example, in the URL `/cloudUI/products/administer/main/userGroupsAsset/ 90uizkSWg0ycuu7hSNXSW4 ` , the unique identifier is ` 90uizkSWg0ycuu7hSNXSW4` . 

How do I retrieve the system-generated unique identifier of an asset group or user role? 

#### **How do I retrieve the system-generated unique identifier of an asset group?**
To get the system-generated unique identifier of an asset group, click **My Services > Metadata Command Center > Customize** . On the **Customize** page, select the **Asset Groups** tab. On the **Asset Groups** tab, click an asset group. The user role page's URL contains the unique identifier. 

For example, in the URL `/assetGroup/07a3ef9c-9410-421f-9b21-64ec9ac9778f ` , the unique identifier is ` 07a3ef9c-9410-421f-9b21-64ec9ac9778f` . 

#### **How do I retrieve the system-generated unique identifier of a user role?**
To get the system-generated unique identifier of a user role, click **My Services > Administrator > User Roles** . On the **User Roles** page, click a user role. The user role page's URL contains the unique identifier. 

For example, in the URL `/cloudUI/products/administer/main/userRolesAsset/ 90uizkSWg0ycuu7hSNXSW4 ` , the unique identifier is ` 90uizkSWg0ycuu7hSNXSW4` .
