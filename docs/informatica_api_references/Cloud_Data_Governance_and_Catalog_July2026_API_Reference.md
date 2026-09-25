# Informatica Cloud Data Governance and Catalog — API Reference

July 2026 · Published July 30, 2026

© Copyright Informatica LLC 2021, 2026

## Contents

- Preface (PDF p. 7)
- Chapter 1: Introduction (PDF p. 8)
  - Authentication (PDF p. 8)
  - Send requests (PDF p. 9)
  - Response codes (PDF p. 10)
  - API rate limits (PDF p. 11)
  - Download sample API collections (PDF p. 11)
- Chapter 2: Import assets (PDF p. 12)
  - Prerequisites (PDF p. 13)
  - Request body (PDF p. 13)
  - Example request (PDF p. 14)
  - Response body (PDF p. 14)
- Chapter 3: Search for assets (PDF p. 15)
  - Retrieve assets in the catalog (PDF p. 15)
    - Request parameters (PDF p. 16)
    - Request body (PDF p. 16)
    - Example requests (PDF p. 21)
    - Response body (PDF p. 25)
  - Retrieve details of an asset (PDF p. 27)
    - Request parameters (PDF p. 28)
    - Examples (PDF p. 29)
    - Response body (PDF p. 37)
  - Retrieve details of multiple assets (PDF p. 37)
    - Request parameters (PDF p. 38)
    - Request body (PDF p. 39)
    - Example request (PDF p. 40)
- Chapter 4: Manage assets (PDF p. 50)
  - Create assets (PDF p. 50)
    - Request body (PDF p. 51)
    - Example requests (PDF p. 51)
  - Update assets (PDF p. 53)
    - Request parameters (PDF p. 53)
    - Request body (PDF p. 53)
    - Example requests (PDF p. 55)
  - Delete assets (PDF p. 60)
    - Request parameters (PDF p. 60)
    - Example response (PDF p. 61)
- Chapter 5: Manage data quality scores (PDF p. 62)
  - Import data quality scores (PDF p. 62)
    - Prerequisites (PDF p. 62)
    - Request body (PDF p. 62)
    - Example request (PDF p. 63)
    - Response body (PDF p. 63)
  - Get data quality scores (PDF p. 64)
    - Request body (PDF p. 65)
    - Example request (PDF p. 65)
    - Response body (PDF p. 65)
  - Upload data quality scores (PDF p. 66)
    - Request body (PDF p. 67)
    - Example request (PDF p. 68)
- Chapter 6: Manage data access assets (PDF p. 69)
  - Data access control API (PDF p. 69)
    - Retrieve data access control assets (PDF p. 70)
    - Create data access control assets (PDF p. 70)
    - Modify data access control assets (PDF p. 73)
    - Delete data access control assets (PDF p. 74)
  - Data de-identification API (PDF p. 75)
    - Retrieve data de-identification assets (PDF p. 75)
    - Create data de-identification assets (PDF p. 76)
    - Modify data de-identification assets (PDF p. 79)
    - Delete data de-identification assets (PDF p. 80)
  - Data filter API (PDF p. 81)
    - Retrieve data filter assets (PDF p. 81)
    - Create data filter assets (PDF p. 82)
    - Modify data filter assets (PDF p. 86)
    - Delete data filter assets (PDF p. 88)
  - Data protection API (PDF p. 88)
    - Retrieve data protection assets (PDF p. 88)
    - Create data protection assets (PDF p. 89)
    - Modify data protection assets (PDF p. 92)
    - Delete data protection assets (PDF p. 94)
  - Policy pushdown resync API (PDF p. 94)
    - Policy pushdown resync API endpoints (PDF p. 95)
  - Precedence tier API (PDF p. 95)
    - Retrieve precedence tier assets (PDF p. 96)
    - Create precedence tier assets (PDF p. 96)
    - Modify precedence tier assets (PDF p. 98)
    - Delete precedence tier assets (PDF p. 99)
  - Publish API (PDF p. 99)
    - Publish API endpoints (PDF p. 99)
- Chapter 7: Export assets (PDF p. 101)
  - Prerequisites (PDF p. 102)
  - Export assets using search queries (PDF p. 102)
    - Request parameters (PDF p. 103)
    - Request body (PDF p. 104)
    - Example requests (PDF p. 104)
  - Export assets using asset IDs (PDF p. 105)
    - Request parameters (PDF p. 106)
    - Request body (PDF p. 107)
    - Example request (PDF p. 107)
  - Response body (PDF p. 108)
- Chapter 8: Get audit history (PDF p. 109)
  - Get audit history of multiple assets (PDF p. 109)
    - Request parameters (PDF p. 110)
    - Example request (PDF p. 111)
  - Get audit history of a specific asset (PDF p. 111)
    - Request parameters (PDF p. 112)
    - Example request (PDF p. 114)
- Chapter 9: Manage catalog sources (PDF p. 115)
- Chapter 10: Monitor jobs (PDF p. 116)
- Appendix A: Search for assets (PDF p. 117)
  - Search query examples (PDF p. 117)
    - Business asset search query examples (PDF p. 118)
    - Technical asset search query examples (PDF p. 121)
    - Data access asset search query examples (PDF p. 125)
    - Asset groups search query examples (PDF p. 128)
    - Relationship search query examples (PDF p. 129)
    - Stakeholder search query examples (PDF p. 131)
    - Lifecycle search query examples (PDF p. 134)
    - Date and time search query examples (PDF p. 135)
    - Collaboration search query examples (PDF p. 135)
    - Classifications search query examples (PDF p. 137)
    - Custom attributes search query examples (PDF p. 138)
    - Data quality rule occurrences search query examples (PDF p. 139)
    - Glossary assets associations with data classifications search query examples (PDF p. 141)
  - Class types for business assets (PDF p. 141)
  - File size limits for API payload fields (PDF p. 142)

# Preface

Refer to _API Reference_ to create and manage assets in Data Governance and Catalog with the APIs that Informatica provides. To use these APIs, you need a basic knowledge of Data Governance and Catalog, JSON, and API programming techniques.

# Chapter 1: Introduction

You can make API calls to Data Governance and Catalog using a REST client, the cURL tool, or a suitable programming interface. Use these API calls to create and manage assets, search for assets, and view asset details. 

## Authentication

Before you make REST API calls, you must authenticate yourself with JWT authentication. First, use the Login API with your organization user name and password to receive the session ID and org ID. Then, use the session ID and org ID to generate a JSON Web (JW) token. Subsequently, you use the JW token to secure your API calls without having to authenticate yourself for each call. 

#### Obtain the session ID

To get a session ID, use the Login API V1. The Login API is also available with SAML. For more information about logging in using SAML, see the _REST API Reference_ in the Administrator help. Ensure that you have your Intelligent Data Management Cloud™ (IDMC) user name and password. 

The following table describes the components of the POST request: 

|**Component**|**Description**|
|---|---|
|URI|**<LoginURL>/identity-service/api/v1/Login**<br>Note that `<LoginURL>` is the IDMC login URL based on the region, such as https://dm-**us**.informaticacloud.com, https://dm-**em**.informaticacloud.com, https://dm-**ap**.informaticacloud.com,<br>https://dm-**uk**.informaticacloud.com, and so on.|
|Header|`Content-Type: application/json`|
|Request Body|`{`<br>`"username": "<your_IICS_user>",`<br>`"password": "<your_IICS_password>"`<br>`}`|


The response generates the session ID and the org ID. Note the value of the `sessionId` field to include in the subsequent API call. 

In Administrator, session ID is specified as the default authentication method. This authentication involves generating a session ID and then using the session ID value to generate the JW token. The organization administrator can switch from session ID-based authentication to JW token-based authentication.

#### Generate a JW Access Token

Use the `sessionId` value from the Login API response, and send a GET or POST request to generate a JW access token. 

The following table describes the components of the GET request: 

**Component Description** URI **<LoginURL>/identity-service/api/v1/jwt/Token?client_id=idmc_api&nonce=1234** Note that `<LoginURL>` is the IDMC login URL based on the region, such as https://dm- **us** .informaticacloud.com, https://dm- **em** .informaticacloud.com, https://dm- **ap** .informaticacloud.com, https://dm- **uk** .informaticacloud.com, and so on. Headers - `cookie: USER_SESSION=<sessionId value>` - `IDS-SESSION-ID: <sessionId value>` 

The response generates the JW access token that you include in the authorization header for all subsequent API calls. The access token expires after 30 minutes from the initial grant of the token. You must regenerate the access token after every 30 minutes to authenticate your API calls. 

**Note:** If the administrator configures JW token-based authentication in Administrator, the login API response includes the JW token value in the `session ID` field. 

## Send requests

When you make an API call in the REST client, the request is sent in JSON format. Depending on the request, the REST client receives a response in JSON format that contains the information for the request that was sent. 

To make an API call, enter the URL to access the API, header requests, methods, and request parameters. 

#### URL

Here's the URL structure for requests: 

```
<baseApiUrl><endpoint>
```

#### Defining the base API URL

The login response includes the base API URL that you must include in subsequent calls. The base API URL differs for each POD. 

The base API URL has the following format: 

`https://idmc-api.dm-[two-letter-regional-identifier].informaticacloud.com/`

For example, you use the following base API URL for the USW1 POD: 

`https://idmc-api.dm-us.informaticacloud.com/`

For more information on POD API URLs, see POD availability and networking. 

Send requests       9

#### Headers

The following table describes the header request that you should use to send the Data Governance and Catalog API requests: 

|**Header**|**Description**|
|---|---|
|`Content-Type:application/json`|This header is required for the POST method. It indicates that the client is<br>sending a JSON request.|
|`Authorization:Bearer`<br>`<jwt_token>`|This header is required to secure all your API requests.|
|`X-INFA-ORG-ID:<Org ID>`|This header is required for POST and GET methods. It indicates the user's<br>organization to send API requests to.<br>You can obtain the org ID from the Login API.|
|`IDS-SESSION-ID`|This header is required for sending API requests to upload your data quality<br>scores to Data Governance and Catalog.|
|`x-infa-show-custom-attribute-label`|This header controls how the API response displays custom attributes.<br>When set to` true`, the response shows user-friendly custom attribute labels<br>instead of class names.<br>When set to` false`, the response shows the class names for custom attributes.|
|`x-infa-show-association-label`|This header controls how the API response displays associations (relationships).<br>When set to` true`, the response shows user-friendly association labels instead of<br>association names.<br>When set to` false`, the response shows the association names.|


## Response codes

When you call any of the Data Governance and Catalog APIs, the response returns one of the standard HTTP response codes with information about the success or failure of the API call. 

The standard response codes are defined in the following table: 

|**Response Code**|**Description**|
|---|---|
|200 OK|The request was completed successfully.|
|201 Created|The request was completed and the new resource is created successfully.|
|202 Accepted|The request was accepted for processing, but has not been completed.|
|204|No content to send back.|
|400 Bad Request|The request could not be processed because it contains missing or invalid information.|
|401 Unauthorized|The request is not authorized. The authentication may be missing or invalid.|

|**Response Code**|**Description**|
|---|---|
|403 Forbidden|The user cannot be authorized to perform this request.|
|404 Not Found|The request includes a resource URL that does not exist.|
|405 Method Not Allowed|The HTTP method specified in the request is not supported for this request.|
|429 Too Many Requests|The user has sent too many requests in a given amount of time and has reached the API<br>rate limit.|
|500 Internal Server Error|The server encountered an error because of which the request is not fulfilled.|


## API rate limits

Data Governance and Catalog limits the number of API calls to provide optimal use of API resources. 

By default, every API provides response headers for rate limits that report on the number of API calls for each API key. 

The following table describes the rate limit response headers returned with each API call: 

|**Response Header**|**Description**|
|---|---|
|`ratelimit-limit`|Returns the number of requests allowed for the client in the time window. By default, you can<br>make 120 API calls per minute and 10000 calls per day.|
|`ratelimit-remaining`|Returns the number of remaining requests in the current window.|
|`ratelimit-reset`|Returns the time remaining in the current window within which you can make the remaining<br>API calls. This time is specified in seconds.|


For example, if the value of `ratelimit-remaining ` is 118 calls and ` ratelimit-reset ` is 28 seconds, then it means that you can make 118 API calls in the next 28 seconds. If the ` ratelimit-remaining ` is 0, wait for the ` ratelimit-reset` value to reset before attempting the API request again. 

If you send too many requests in a given amount of time and has reached the API rate limit, the response header `HTTP 429: Too Many Requests` is returned. 

## Download sample API collections

Informatica provides sample API collections in Postman that you can use to search for assets, export and import assets, and monitor jobs in Data Governance and Catalog. 

For information about the sample Postman collection for Data Governance and Catalog APIs, see the Knowledge Base article KB 000209295. 

API rate limits       11

# Chapter 2: Import assets

Use the import API to import bulk assets into the catalog. You can import new business assets into the catalog, create, update or delete existing business assets in the catalog, or re-import curated technical assets that you previously exported from the catalog. 

Send a POST request after uploading the import file in the body of the request. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|POST|**<baseApiUrl>/data360/content/import/v1/assets**|


The base API URL differs for each pod. For more information, see “Send requests” on page 9. 

The following image shows the overall process of using the import API and the expected response for the endpoint: 


<!-- Start of picture text -->
Obtain the POST<br>sessionID using GET<br>the Login API GenerateSusing a JW token<br>APL data360/content/import/v1<br>foe the JWT Token Import bulk assets using the<br>Username assets<br>RequestHeader:<br>Password endpoint<br>y cookie: USER_SESSION-= {session}<br>ooaae 10S-SESSIONA ((sessionid)) ei<br>orale Response:<br>pg Request Header:<br>7 XAINFA-ORGD<br>: orgld<br>rey<br>_alsb,file: upload the import file in the xls,.xlsm,<br>or xs format<br>errorsand} config: warnings.specifythe validation poieyincaseof<br>=<br>+<br>+ JobJob 1DURI~unique~Job API submitted tracking URIjob id to monitor<br>1<br><!-- End of picture text -->

## Prerequisites

Before you import assets into the catalog, verify the following prerequisites. 

You can import assets into the catalog only if your organization administrator grants you the following privileges and permissions: 

For more information about the privileges and permissions, see the _Asset Management_ help. 

## Request body

Use the request body to upload the import file and specify the action that the request should take in case of errors or warnings. 

Let us assume that you want to import the `MyCurations.xls` file containing bulk asset details. In the body of the request, specify the parameters in the following format: 

```
file: MyCurations.xls

config:

{
  "validationPolicy": "CONTINUE_ON_ERROR_WARNING"
}
```

The following table describes the parameters that you can specify in the body of the request: 

|**Parameter**|**Description**|
|---|---|
|`file`|Specify the full path to the import file or upload the import file. This should be a Microsoft Excel file in<br>the`.xlsx`,`.xlsm`,`.xlsb`,`.xls`, or`.csv` format. You can upload only one Microsoft Excel or CSV file in<br>each request.|
|`config `|Specify one of the following validation policies to specify the action that the API request takes in case<br>of errors and warning:<br>-<br>` STOP_ON_ERROR `. If the import file contains a row with errors, the process stops, and no data is<br>imported. If there are no errors, the system imports valid data from the file, and warnings are marked<br>as skipped.<br>-<br>` STOP_ON_WARNING `. If the import file contains a row of data with warnings, the process stops, and no<br>data is imported.<br>-<br>` CONTINUE_ON_ERROR_WARNING`. All valid rows from the file are imported. Rows of data with<br>warnings and errors are marked as skipped.<br>If you do not specify the validation policy, then the default value is` CONTINUE_ON_ERROR_WARNING`.|


Prerequisites       13

## Example request

#### Import a bulk asset file in the catalog

The following example shows a POST request to import a bulk asset Microsoft Excel file named `MyCurations.xls` .The validation policy is set to import all valid rows from the file and skip rows with warnings and errors. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/content/import/v1/assets
Authorization: Bearer <jwt_token>
X-INFA-ORG-ID:<Org ID>

Body
--form 'file=@"/path/to/file"' \

--form 'config="{

  \"validationPolicy\": \"CONTINUE_ON_ERROR_WARNING\"

}";type=application/json'
```

## Response body

When you send a POST request to import bulk assets into the catalog, the API triggers a job to import the assets. 

The following example shows the response of the POST request to import bulk assets from the file `MyCurations.xls` : 

```
{
    "jobId": "6a084b71-3696-4a50-b83b-9ef0f2c7f30d",

    "jobUri": "/data360/observable/v1/jobs/6a084b71-3696-4a50-b83b-9ef0f2c7f30d"
}
```

The following table describes the response body parameters for the import API request: 

|**Parameter**|**Description**|
|---|---|
|`jobId`|Unique ID of the import job that the request triggers.|
|`jobUri`|Job tracking URI that you can use to send a GET request to monitor the status of the import job.|


For more information about using the jobs API to monitor the status of jobs, see Chapter 10, “Monitor jobs” on page 116.

# Chapter 3: Search for assets

Use the search API to either list the assets in the catalog or get details of a specific asset in the catalog. You can search for all types of assets including business assets, technical assets, classifications, glossary assets, and more. Based on the search query that you enter, appropriate search results are returned. 

The following image shows the overall process of using the search API and the expected response for each endpoint: 


<!-- Start of picture text -->
Obtain the POST<br>he Login API 5 Te<br>qroe a If you know the asset ID<br>ane “Athoriation: Beer Token id} endpoint /detalls endpoin<br>response:pegs JENA3 “Athoriation:Bere Token<br>ORG rgd equesteade<br>Parameters:r ENA ORG orp<br>,et:eondcin, searers) co,i seme seaman<br>, eset) —c<br>+ proiingrests<br>+: Objctio +1 GlossaryssoitonsStakeholders<br>i 5 Sytem‘uibutes,Uneagefocsabuts,Sel Neighbourhodand cstom<br>+ bakes<br>4,<br>i<br><!-- End of picture text -->

## Retrieve assets in the catalog

To view the list of assets in the catalog that match your search query, send a POST request. While sending the request, you can specify the search query as request URL parameters and specify the filter criteria in the body of the request. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|POST|**<baseApiUrl>/data360/search/v1/assets**|

The base API URL differs for each pod. For more information, see “Send requests” on page 9. 

### Request parameters

To view the list of specific assets, enter the search queries using URL parameters for POST request. 

The following table describes the parameters that you can specify in the request URL: 

|**Parameter**|**Description**|
|---|---|
|`knowledgeQuery `|Specify the asset type or name along with any search criteria. For<br>information about search query examples, see<br>“<br>Searchqueryexamples<br>”<br>onpage<br>117.<br>For example,` knowledgeQuery=Business Terms ` and<br>` knowledgeQuery=data elements related to business term`.|
|`segments `|Specify the level of asset detail that you want the API request to return.<br>Enter any segment value from the following values:<br>-<br>` all `. Returns the summary, system attributes, and the API request<br>link to view further details of the asset.<br>-<br>` summary `. Returns the name, description, and location of the asset.<br>-<br>` systemAttributes `. Returns the origin, class type of the asset,<br>base types, user that created or updated the asset, and the date on<br>which the asset was created or updated.<br>-<br>` customAttributes `. Returns the custom fields specified for the<br>asset.<br>-<br>` selfAttributes `. Returns the properties of assets.<br>-<br>` details`. Returns the URI for the asset that you can click to send a<br>request to view further details of a specific asset.<br>The default value is` segments=all`.|


### Request body

Use the request body to specify parameters to filter, rank, and sort your search results. The API response returns a maximum of 100 results that match the specified criteria. 

In the body of the request, specify the parameters in the following format: 

```
{

    "totalHits": true,
from": 0
    "size": 10,
    "searchFields": [
        "string"
    ],
    "filterSpec": [
        {
            "type": "dsl",
            "attribute": "string",
            "values": [
                "string"
            ],
            "expr": "string"
        }
    ],
   "rankingSpec":
    { "boostSpec": [
            {
                "fields": [
                    "string"
                ]

            }
        ],
      "scoringSpec": [{
            "type": "simple",
            "attribute": "core.identity",
            "order":"asc"
        },
        {
            "type": "simple",
            "attribute": "core.name",
            "order":"asc"
         }]
    }
    ,
   "after":["03c1883a-dcb7-4ae0-a6a5-c2743e52fb69",
                "column67"]
}
```

The following table describes the important parameters that you can specify in the body of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`totalHits `|String|-<br>` true `. Returns the exact count of assets when the search result<br>retrieves more than 10,000 assets.<br>-<br>` false`. Returns an approximate count of the assets with a<br>maximum limit of 10,000.|
|pagination parameters<br>`from` and` size `|Numeric|Use the following parameters to retrieve records using pagination:<br>-<br>` from `. Specify a numeric value to set an offset for pagination. The<br>default value is zero.<br>-<br>` size`. Specify a numeric value to set the maximum number of<br>results to display on each page starting from the offset value.<br>The default value is 10. You can set a maximum page size of 100<br>results.<br>The following examples show how you can use the` from` and` size`<br>parameters:<br>`{`<br>`"from": 0,`<br>`"size": 50`<br>`}`|
|||Retrieves 50 records per page starting from the first record,<br>assuming that the total_hits is more than 50 records.<br>`{`<br>`"from": 50,`<br>`"size": 50`<br>`}`|
|||Retrieves 50 records per page starting from the 50th record,<br>assuming that the total_hits is more than 50 records.|
|`searchFields`|String|Specify the asset fields that you want to search. For example, you<br>can search for assets whose name and description fields match the<br>term` SSN`.<br>This does not influence the ordering of the search results.|


Retrieve assets in the catalog       17

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`filterSpec `<br>` rankingSpec`|String<br>NA|Specify a list of fields and values that you want the API to use to<br>filter search results. You can use any of the following filter type or a<br>combination of both filter types:<br>**Simple**. Specify the asset attribute and one or more values to match<br>the assets. For example, the following` filterSpec` parameter<br>returns the assets that are in the 'Published' lifecycle status:<br>`{`<br>`"from": 0,`<br>`"size": 100,`<br>`"`**`filterSpec`**`": [`<br>`{`<br>`"type": "simple",`<br>`"attribute": "core.assetLifecycle",`<br>`"values": [`<br>`"Published"`<br>`]`<br>`}`<br>`]`<br>`}`<br>**dsl**. Specify an advanced filter expression to match the assets. For<br>example, the following` filterSpec` parameter returns business<br>terms that are created in the last one hour:<br>`{`<br>`"from": 0,`<br>`"size": 100,`<br>`"`**`filterSpec`**`": [`<br>`{`<br>`"type": "dsl",`<br>`"expr": "core.classType `<br>` core.infa.ccfg.models.governance.BusinessTerm and `<br>` core.createdOn within last 1 hour"`<br>`}`<br>`]`<br>`}`<br>Determines the order in which the assets appear in the search<br>response. You can order the assets by specifying the asset scoring<br>or boosting criteria or both.|

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`boostSpec`|String|Specify one or more fields by which you want to sort the search<br>results. If you specify multiple fields, all specified fields are treated<br>equally for sorting.<br>For example, the following` boostSpec` parameter first returns<br>assets whose names match the search criteria, followed by the<br>assets whose descriptions match the criteria.<br>`{`<br>`"explain": false,`<br>`"from": 0,`<br>`"size": 100,`<br>`"rankingSpec": {`<br>`"`**`boostSpec`**`": [`<br>`{`<br>`"fields": [`<br>`"core.name"`<br>`]`<br>`},`<br>`{`<br>`"fields": [`<br>`"core.description"`<br>`]`<br>`}`<br>`]`<br>`}`<br>`}`|


Retrieve assets in the catalog       19

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`scoringSpec`|String|Specify one of the following two types of scoring criteria:<br>**Simple**. Specify the sorting order (ascending or descending) on<br>attributes such as name or identity.<br>For example, the following` scoringSpec` parameter returns<br>business terms that are sorted in the descending order of their<br>names.<br>`{`<br>`"from": 0,`<br>`"size": 100,`<br>`"rankingSpec": {`<br>`"`**`scoringSpec`**`": [`<br>`{`<br>`"type": "simple",`<br>`"attribute": "core.name",`<br>`"order": "desc"`<br>`}`<br>`]`<br>`},`<br>`"filterSpec": [`<br>`{`<br>`"type": "dsl",`<br>`"expr": "core.classType `<br>` com.infa.ccgf.models.governance.BusinessTerm"`<br>`}`<br>`]`<br>`}`<br>**dsl**. Specify an advanced filter criteria in an expression to sort the<br>search results. For example, the following` scoringSpec` parameter<br>returns the list of business term assets sorted by assets that have<br>an average rating greater than one.<br>`{`<br>`"from": 0,`<br>`"size": 100,`<br>`"rankingSpec": {`<br>`"`**`scoringSpec`**`": [`<br>`{`<br>`"type": "dsl",`<br>`"expr":`<br>`"core.supplement.averageRating greater than 1"`<br>`}`<br>`]`<br>`},`<br>`"filterSpec": [`<br>`{`<br>`"type": "dsl",`<br>`"expr": "core.classType `<br>` com.infa.ccgf.models.governance.BusinessTerm"`<br>`}`<br>`]`<br>`}`|
|`after`|Object|Enter the value from the` SortValues` field of an asset that the API<br>response returns. When you run the API request with this value, the<br>response returns the next set of specified assets after the asset<br>that you have specified the value for.|

Search for more than 10000 assets 

To search more than 10000 assets, set the `scoringSpec ` type in the ` rankingSpec ` parameter to **Simple** . Among the attributes that you specify for the sorting order, specify at least one attribute that has unique values. For example, you can sort the order on the ` core.identity` attribute, which is the internal ID of the asset. 

You can retrieve a maximum of 100 assets with each API request. To see the next 100 assets, copy the value from the `SortValues ` field of the last asset that the response returns and enter it in the ` after` field of the request body before you send the request again. 

### Example requests

List the next set of results from the catalog 

The following request returns the first 50 assets sorted in the ascending order of their internal IDs. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=*&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
Body
{
    "from": 0,
    "size": 50,
    "rankingSpec": {
        "scoringSpec": [
            {
                "type": "simple",
                "attribute": "core.identity",
                "order": "asc"
            }
        ]
    }
}
```

To see the next 50 assets and so on, copy the value from the `SortValues ` parameter of the 50th asset that the response returns and enter it in the ` after` parameter of the request body as shown in the following example. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=*&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
Body
{
    "size": 50,
    "rankingSpec": {
        "scoringSpec": [
            {
                "type": "simple",
                "attribute": "core.identity",
                "order": "asc"
            }
        ]
    },
    "after" : ["<The value from the sortValues field of the 50th asset>"]
}
```

To continue retrieving more assets from the catalog, replace the value of the `after` parameter in the request body during each API request. 

Retrieve assets in the catalog       21

#### List business terms

The following request returns up to 100 business terms based on the `size` body parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=*&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
Body
{
    "from": 0,
    "size": 100,
    "filterSpec": [
        {
            "type": "simple",
            "attribute": "core.classType",
            "values": [
                "com.infa.ccgf.models.governance.BusinessTerm"
            ]
        }
    ]
}
```

List business terms in the 'Draft' lifecycle status 

The following request returns business terms that are in the 'Draft' lifecycle status. The response displays up to 100 business terms based on the `size` body parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=*&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID: <Org ID>
Body
{
    "from": 0,
    "size": 100,
    "filterSpec": [
        {
            "type": "simple",
            "attribute": "core.classType",
            "values": [
                "com.infa.ccgf.models.governance.BusinessTerm"
            ]
        },
        {
            "type": "simple",
            "attribute": "core.assetLifecycle",
            "values": [
                "Draft"
            ]
        }
    ]
}
```

List business terms created in the last 30 days 

The following request returns business terms created in the last 30 days based on the `filterSpec ` body parameter. The response displays up to 50 business terms based on the ` size` body parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=*&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID: <Org ID>
Body

{
    "from": 0,
    "size": 50,
    "filterSpec": [
        {
            "type": "dsl",
            "expr": "core.classType com.infa.ccgf.models.governance.BusinessTerm and
core.createdOn within last 30 day"
        }
    ]
}
```

#### List data elements related to business terms

The following request returns data elements that are related to business terms based on the `knowledgeQuery ` parameter. The response displays up to 50 data elements based on the ` size` body parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=data elements related to business term&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID: <Org ID>
Body
{
    "from": 0,
    "size": 50
}
```

#### List assets related to the business term 'email'

The following request returns up to 100 assets related to the business term 'email' based on the `knowledgeQuery` URL parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=all related to business term email&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID: <Org ID>

Body
{
    "from": 0,
    "size": 100
}
```

#### List the tables that are profiled

The following request returns up to 50 tables that are profiled based on the `knowledgeQuery ` URL parameter. The list of tables appear in the order of the average rating as specified in the ` scoringSpec` body parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=tables which are profiled&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID: <Org ID>

Body
{
    "from": 0,
    "size": 50,
    "rankingSpec": {
        "scoringSpec": [
            {
                "type": "simple",
                "attribute": "core.supplement.averageRating",
                "values": [
                    "5"
                ]
            }
```

Retrieve assets in the catalog       23

```
        ]
    }
}
```

List data elements related to business terms that are critical data elements 

The following request returns up to 50 data elements that are marked as critical data element and related to the business terms 'Country' and 'First Name' as specified in the `filterSpec` body parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=data elements related to business term which are critical data
element&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID: <Org ID>
Body
{
  "from": 0,
  "size": 50,
  "filterSpec": [
    {
      "type": "simple",
      "attribute": "core.name",
      "values": [
        "Country", "FirstName"
      ]
    }
  ]
}
```

List the system assets 

The following request returns the system assets with the names 'Consumer Website' and 'Snowflake Product' as specified in the `filterSpec ` body parameter. The response displays up to 100 system assets based on the ` size` body parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=system&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID: <Org ID>
Body
{
    "from": 0,
    "size": 100,
    "filterSpec": [
        {
            "type": "simple",
            "attribute": "core.name",
            "values": [
                "Consumer Website",
                "Snowflake Product"
            ]
        }
    ]
}
```

#### List data classifications

The following request returns up to 100 data classifications as specified in the `size` body parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=classification&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID: <Org ID>
Body

{
    "from": 0,
    "size": 100
}
```

#### List data entity classifications

The following request returns data entity classifications as specified in the `filterSpec ` body parameter. The response returns up to 50 classifications as specified in the ` size` body parameter. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets?
knowledgeQuery=classification&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID: <Org ID>
Body
{
  "from": 0,
  "size": 50,
  "filterSpec": [
    {
      "type": "dsl",
      "expr": "core.classType core.DataEntityClassification"
    }
  ]
}
```

### Response body

The POST request response returns the list of assets that match the search criteria. 

The response displays the `total_hits` parameter that shows the total number of assets that match the search criteria. The response returns 100 assets per page. 

The following table describes the possible parameters for each asset in the response body: 

|**Parameter**|**Description**|
|---|---|
|`identity`|The internal ID of the asset. You can derive this ID by looking at the URL of the asset page in<br>the Data Governance and Catalog application.|
|`externalID`|The unique reference ID of the asset.|
|`summary`|Contains the name, description, and location of the asset in the catalog.|
|`systemAttributes `|Contains the following meta parameters:<br>-<br>` modifiedOn `. The date on which the asset was last modified<br>-<br>` createdBy `. The user who created the asset.<br>-<br>` modifiedBy `. The user who last modified the asset.<br>-<br>` classType `. The type of asset.<br>-<br>` origin `. The origin of the assets in the catalog.<br>-<br>` createdOn`. The date on which the asset was created.|
|`customAttributes`|Contains the user-configured attributes of the asset.|
|`selfAttributes`|Contains different types of details depending on whether the asset is a business asset or a<br>technical asset. Details include the lifecycle status, profiling and classification status, rating,<br>information about whether the asset is a certified asset or a critical data element, and so on.|


Retrieve assets in the catalog       25

|**Parameter**|**Description**|
|---|---|
|`details`|Contains the URI for the particular asset. Click the URI to send a request to view complete<br>details of that asset.|
|`sortValues`|Contains a value to indicate the position of the asset in the response body. Enter this value in<br>the` after` field of the request body to return the next set of specified assets after this asset.|


#### Example response

The following example is an excerpt from a POST request to view business terms in the 'Draft' lifecycle status. 

```
{
    "summary": {
        "total_hits": "1038"
    },
    "hits": [
        {
            "core.identity": "2f8e6839-14fe-4b25-a868-a6324be30164",
            "core.externalId": "BT-06092022-24",
            "summary": {
                "core.location": "CDGC://2f8e6839-14fe-4b25-a868-a6324be30164",
                "core.name": "BT-06092022-24",
                "core.description": " Desc Create using admin user from group and
\nDeleteed stakeholder user."
            },
            "systemAttributes": {
                "core.modifiedOn": 1689252674028,
                "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                "core.classType": "com.infa.ccgf.models.governance.BusinessTerm",
                "core.origin": "CDGC",
                "type": [
                    "core.IClassBusiness",
                    "core.IClass",
                    "com.infa.ccgf.models.governance.GlossaryBase",
                    "com.infa.ccgf.models.governance.BusinessTerm"
                ],
                "core.createdOn": 1689252674028
            },
            "customAttributes": {
                "com.infa.odin.models.custom.ca_2205258177888237465": "default",
                "com.infa.odin.models.custom.ca_5534006856514172118": "default"
            },
            "selfAttributes": {
                "com.infa.ccgf.models.governance.FormatType": "Text",
                "core.mergedObjects": [],
                "com.infa.ccgf.models.governance.BusinessLogic": "BusinessLogic",
                "com.infa.ccgf.models.governance.isCDE": true,
                "core.supplement.certified": [
                    false
                ],
                "core.assetLifecycle": "Draft",
                "com.infa.ccgf.models.governance.Examples": [
                    "Example-1",
                    "Exampl25"
                ],
                "com.infa.ccgf.models.governance.securityClassification": "Confidential",
                "com.infa.ccgf.models.governance.AliasNames": [
                    "BT",
                    "BTObject"
                ],
                "core.producer": "CDGC",
                "com.infa.ccgf.models.governance.FormatDescription": "Format Description"
            },
            "details": "/data360/search/assets/BT-06092022-24?

scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                                "sortValues":[
                "03c1ea71-cac8-4e39-9f4a-6774c657a8cf",
                "idsltc"
            ]
        }
    ]
}
```

## Retrieve details of an asset

To retrieve complete details of a particular asset, send a GET request using the search API. Based on the request URL parameters that you pass, the response includes details such as profiling, classification, relationships, custom attributes, and lineage information of the specified asset. 

Specify the asset ID in endpoint of the GET request. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|GET|**<baseApiUrl>/data360/search/v1/assets/<assetID>**|


The `<baseApiUrl>` differs for each pod. For more information about the base API URL, see “Send requests” on page 9. 

The `<assetID>` that you enter can be the internal ID or the external ID of the asset. You can specify in the `scheme` parameter of the request URL whether you want to search the asset by external ID or internal ID. 

If you don't know the ID of a particular asset, you can first send a POST request using the `<baseApiUrl>/ data360/search/assets ` endpoint to view the list of assets in the catalog. The response for this request returns the internal ID of each asset in the ` core.identity ` parameter and the unique reference ID of the asset in the ` core.externalId` parameter. For more information, see “Retrieve assets in the catalog” on page 15. 

You can also configure the API response to display custom attribute names and relationship names as parameter names. For more information, see the “Send requests” on page 9 topic. 

Retrieve details of an asset       27

### Request parameters

In the request URL parameters, specify the type of asset ID that you want to use to search for assets. To limit the API response to specific details of an asset, you can additionally specify the type of asset details that you want the request to return. 

The following table describes the parameters that you can specify in the request URL: 

|**Parameter**|**Description**|
|---|---|
|`scheme `|Specify the type of asset ID you want to use to query assets. Enter one<br>of the following values:<br>-<br>` internal `. Indicates that the asset ID that you specify in the request<br>is the internal ID of the asset.<br>-<br>` external`. Indicates that the asset ID that you specify in the request<br>is the unique reference ID of the asset.|
|`segments`|Specify the type of asset details that you want the API request to<br>return. The default value is` segments=all `. Enter any segment value<br>from the following values:<br>-<br>` all `. Returns the internal ID, external ID, summary, system attributes,<br>and the API request link to view further details of the asset.<br>-<br>` summary `. Returns the name, description, and location of the asset.<br>-<br>` systemAttributes `. Returns the origin, class type, base types, user<br>that created or updated the asset and the date on which the asset<br>was created or updated.<br>-<br>` stakeholdership `. Returns the type of user and role that are<br>assigned as stakeholder for the asset.<br>-<br>` customAttributes `. Returns the custom attributes specified for the<br>asset.<br>-<br>` selfAttributes `. Returns the properties of assets.<br>-<br>` dataProfile `. Returns the profiling statistics of the asset.<br>-<br>` dataClassification `. Returns the data classification associated<br>with the asset.<br>-<br>` dataClassification:all `. Returns all segments of the data<br>classification.<br>-<br>` dataClassification:summary `. Returns the summary of the data<br>classification.<br>-<br>` dataClassification:selfAttributes `. Returns all the<br>attributes of the data classification which are not included in other<br>segments.<br>-<br>` dataClassification:systemAttributes `. Returns the system<br>attributes of the data classification.<br>-<br>` dataClassification:relationshipAttributes `. Returns the<br>relationship attributes between the asset and the data classification.<br>-<br>` glossary `. Returns the glossary associated with the asset.<br>-<br>` glossary:all `. Returns all segments of the glossary.<br>-<br>` glossary:summary `. Returns the summary of the glossary.<br>-<br>` glossary:systemAttributes `. Returns the system attributes of<br>the glossary.<br>-<br>` glossary:selfAttributes `. Returns all the attributes of the<br>glossary that are not included in other segments.<br>-<br>` glossary:relationshipAttributes `. Returns the relationship<br>attributes between the asset and the glossary.<br>-<br>` dataQuality `. Returns the data quality score of the asset.<br>-<br>` dataQuality:all `. Returns all segments of the data quality scores.<br>-<br>` dataQuality:summary `. Returns the summary of the data quality<br>scores.<br>-<br>` dataQuality:systemAttributes`. Returns the system attributes<br>of the data quality scores.|

|**Parameter**|**Description**|
|---|---|
||-<br>`dataQuality:selfAttributes `. Returns all the attributes of data<br>quality scores that are not included in other segments.<br>-<br>` dataQuality:relationshipAttributes `. Returns the<br>relationship attributes between the asset and data quality scores.<br>-<br>` hierarchy `. Returns the immediate child assets of the asset.<br>-<br>` hierarchy:all `. Returns all segments of the immediate child<br>assets in the hierarchy.<br>-<br>` hierarchy:summary `. Returns the summary of the hierarchy.<br>-<br>` hierarchy:selfAttributes `. Returns the properties of the<br>immediate child assets in the hierarchy.<br>-<br>` hierarchy:systemAttributes `. Returns the system attributes of<br>the immediate child assets in the hierarchy.<br>-<br>` hierarchy:customAttributes `. Returns the custom attributes of<br>the immediate assets in the hierarchy.<br>-<br>` neighborhood `. Returns assets that are in a direct relationship with<br>the searched asset.<br>-<br>` neighborhood:all `. Returns assets that are in a direct or indirect<br>relationship with the searched asset.<br>-<br>` neighborhood:<classtype>`. Returns assets of the specified<br>class type that are in a direct relationship with the searched asset.<br>-<br>`lineage-level`. Returns the lineage level of the asset, that is data<br>element or data set level. The default value for` lineage-level ` is<br>` dataset `.<br>-<br>` lineage-direction`. Returns the direction of the asset in the<br>lineage, that is inbound or outbound. The default value for` lineage-direction` is` all `.<br>-<br>` lineage-distance `. Returns the lineage up to a certain distance.<br>-<br>` lineage-level:dataset `. Returns assets that have a direct<br>relationship with the searched asset.<br>-<br>` lineage-direction:all `. Returns assets that are in a direct<br>inbound and outbound relationship with the searched asset.<br>-<br>` lineage-direction:inbound `. Returns assets that are in a direct<br>inbound relationship with the searched asset.<br>-<br>` lineage-direction:outbound `. Returns assets that are in a<br>direct outbound relationship with the searched asset.<br>-<br>` descendants `. Returns the immediate children and related child<br>assets.<br>-<br>` descendants:all `. Returns all the details of the immediate and<br>related child assets.<br>-<br>` descendants:systemAttributes `. Returns the system attributes<br>of the immediate and related child assets.<br>-<br>` descendants:selfAttributes `. Returns the properties of the<br>immediate and related child assets.<br>-<br>` descendants:customAttributes`. Returns the custom attributes<br>of the immediate and related child assets.|


### Examples

#### Search for a business term using the asset ID

The following example shows the GET request to get all the details of a business term by specifying its internal ID. 

```
GET https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets/
612adac1-5863-468f-a38c-fb3ba0a5686e?scheme=internal&segments=all
```

Retrieve details of an asset       29

The following example shows the response for the request. The response returns all details of the business term 'AddressLine2' because the `segments ` parameter in the request is set to ` all` . 

```
GET https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets/
612adac1-5863-468f-a38c-fb3ba0a5686e?scheme=internal&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/jsonBody
header x-infa-show-association-label: true
header x-infa-show-custom-attribute-label: true
{
    "core.identity": "17b1e3ed-f64f-487a-b073-06b78ec30259",
    "core.externalId": "lower-68",
    "summary": {
        "core.location": "CDGC://17b1e3ed-f64f-487a-b073-06b78ec30259",
        "core.name": "AddressLine2"
    },
    "systemAttributes": {
        "core.modifiedOn": 1688816931942,
        "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
        "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
        "core.classType": "com.infa.ccgf.models.governance.BusinessTerm",
        "core.origin": "CDGC",
        "type": [
            "core.IClassBusiness",
            "core.IClass",
            "com.infa.ccgf.models.governance.GlossaryBase",
            "com.infa.ccgf.models.governance.BusinessTerm"
        ],
        "core.createdOn": 1688816931942
    },
    "customAttributes": {
       "New Custom Attribute MSDD 01": [
                "Consumer [2088] - CON"
    },
    "selfAttributes": {
        "com.infa.ccgf.models.governance.FormatType": "Text",
        "core.supplement.certified": [
            false
        ],
        "core.assetLifecycle": "Published",
        "core.producer": "CDGC",
        "com.infa.ccgf.models.governance.isCDE": false
    },
    "dataProfile": {
        "core.classType": "com.infa.ccgf.models.governance.BusinessTerm",
        "core.identity": "17b1e3ed-f64f-487a-b073-06b78ec30259",
        "core.producer": "CDGC",
        "core.origin": "CDGC",
        "core.externalId": "lower-68",
        "core.name": "AddressLine2"
    },
    "neighborhood": [
        {
            "type": "com.infa.odin.models.file.flat.FlatField",
            "neighbors": [
                {
                    "neighbor": "AddressLine2",
                    "details": "/data360/search/assets/86aa9780-99d3-4efb-95ea-
b4d691bb97d0?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                    "paths": [
                        {
                            "serialized": "lower-68 ( AddressLine2 )  <--
com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase -- dqprofiling-dockerpod/
courses/person1.csv/AddressLine2~com.infa.odin.models.file.flat.FlatField
( AddressLine2 ) ",
                            "collection": [
                                {
                                    "association": "is a Strategic Source for",

                                    "curationStatus": "AUTO_ACCEPTED",
                                    "from": "AddressLine2",
                                    "to": "AddressLine2",
                                    "fromType":
"com.infa.odin.models.file.flat.FlatField",
                                    "toType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                    "fromLocation": "99068dfb-2c91-34e2-
bd37-5232fde620b1://99068dfb-2c91-34e2-bd37-5232fde620b1/dqprofiling-dockerpod/courses/
person1.csv/AddressLine2",
                                    "toLocation": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                    "attributes": {
                                        "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.origin": "99068dfb-2c91-34e2-
bd37-5232fde620b1",
                                        "core.modifiedOn": 1689076624370,
                                        "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.confidenceScore": 100.0,
                                        "core.inferred": true,
                                        "core.associationKind":
"com.infa.ccgf.models.governance.semanticKind",
                                        "core.producer": "discovery-capability",
                                        "core.createdOn": 1689076624370,
                                        "core.curationStatus": "AUTO_ACCEPTED"
                                    },
                                    "details": {
                                        "fromUri": "/data360/search/assets/
86aa9780-99d3-4efb-95ea-b4d691bb97d0?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                        "toUri": "/data360/search/assets/lower-68?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                    },
                                    "fromProperties": {
                                        "core.identity": "86aa9780-99d3-4efb-95ea-
b4d691bb97d0",
                                        "core.classType":
"com.infa.odin.models.file.flat.FlatField",
                                        "core.location": "99068dfb-2c91-34e2-
bd37-5232fde620b1://99068dfb-2c91-34e2-bd37-5232fde620b1/dqprofiling-dockerpod/courses/
person1.csv/AddressLine2",
                                        "core.externalId": "dqprofiling-dockerpod/
courses/person1.csv/AddressLine2~com.infa.odin.models.file.flat.FlatField",
                                        "core.name": "AddressLine2"

                                    },
                                    "toProperties": {
                                        "core.identity": "17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "core.classType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                        "core.location": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "core.externalId": "lower-68",
                                        "core.name": "AddressLine2"
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    "neighbor": "AddressLine2",
                    "details": "/data360/search/assets/00b1dbb0-ee68-43e9-
a833-675b5c8f0ab2?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                    "paths": [
                        {
                            "serialized": "lower-68 ( AddressLine2 )  <--
com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase -- dqprofiling-dockerpod/
courses/person1.csv/AddressLine2~com.infa.odin.models.file.flat.FlatField
```

Retrieve details of an asset       31

```
( AddressLine2 ) ",

                            "collection": [
                                {

                                    "association":
"com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase",
                                    "curationStatus": "AUTO_ACCEPTED",
                                    "from": "AddressLine2",
                                    "to": "AddressLine2",
                                    "fromType":
"com.infa.odin.models.file.flat.FlatField",
                                    "toType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                    "fromLocation": "27397996-
ad9a-3f32-8093-2e4b80fcd93c://27397996-ad9a-3f32-8093-2e4b80fcd93c/dqprofiling-dockerpod/
courses/person1.csv/AddressLine2",
                                    "toLocation": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                    "attributes": {
                                        "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.origin": "27397996-
ad9a-3f32-8093-2e4b80fcd93c",
                                        "core.modifiedOn": 1688819441749,
                                        "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.confidenceScore": 100.0,
                                        "core.inferred": true,
                                        "core.associationKind":
"com.infa.ccgf.models.governance.semanticKind",
                                        "core.producer": "discovery-capability",
                                        "core.createdOn": 1688819441749,
                                        "core.curationStatus": "AUTO_ACCEPTED"
                                    },
                                    "details": {
                                        "fromUri": "/data360/search/assets/00b1dbb0-
ee68-43e9-a833-675b5c8f0ab2?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                        "toUri": "/data360/search/assets/lower-68?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                    },
                                    "fromProperties": {
                                        "core.identity": "00b1dbb0-ee68-43e9-
a833-675b5c8f0ab2",
                                        "core.classType":
"com.infa.odin.models.file.flat.FlatField",
                                        "core.location": "27397996-
ad9a-3f32-8093-2e4b80fcd93c://27397996-ad9a-3f32-8093-2e4b80fcd93c/dqprofiling-dockerpod/
courses/person1.csv/AddressLine2",
                                        "core.externalId": "dqprofiling-dockerpod/
courses/person1.csv/AddressLine2~com.infa.odin.models.file.flat.FlatField",
                                        "core.name": "AddressLine2"
                                    },
                                    "toProperties": {
                                        "core.identity": "17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "core.classType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                        "core.location": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "core.externalId": "lower-68",
                                        "core.name": "AddressLine2"
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
        },
        {
            "type": "com.infa.odin.models.relational.Column",
            "neighbors": [

                {
                    "neighbor": "CDGCTABL31COL2",
                    "details": "/data360/search/assets/81e8ea5a-c888-4c40-
a780-849ac48e66fd?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                    "paths": [
                        {
                            "serialized": "lower-68 ( AddressLine2 )  <--
com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase -- CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column
( CDGCTABL31COL2 ) ",
                            "collection": [
                                {

                                    "association":
"com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase",
                                    "curationStatus": "ACCEPTED",
                                    "from": "CDGCTABL31COL2",
                                    "to": "AddressLine2",
                                    "fromType": "com.infa.odin.models.relational.Column",
                                    "toType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                    "fromLocation": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                    "toLocation": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                    "attributes": {
                                        "core.modifiedOn": 1692806775314,
                                        "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.associationKind":
"com.infa.ccgf.models.governance.semanticKind",
                                        "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.inferred": false,
                                        "core.producer": "CDGC",
                                        "core.origin": "CDGC",
                                        "core.createdOn": 1692806775314,
                                        "core.curationStatus": "ACCEPTED"
                                    },
                                    "details": {
                                        "fromUri": "/data360/search/assets/81e8ea5a-
c888-4c40-a780-849ac48e66fd?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                        "toUri": "/data360/search/assets/lower-68?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                    },
                                    "fromProperties": {
                                        "core.identity": "81e8ea5a-c888-4c40-
a780-849ac48e66fd",
                                        "core.classType":
"com.infa.odin.models.relational.Column",
                                        "core.location": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                        "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
                                        "core.name": "CDGCTABL31COL2"
                                    },
                                    "toProperties": {
                                        "core.identity": "17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "core.classType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                        "core.location": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "core.externalId": "lower-68",
                                        "core.name": "AddressLine2"
                                    }
                                }
                            ]
                        }
```

Retrieve details of an asset       33

```
                    ]
                }
            ]
        }
    ]
}
```

Search for a column using the asset ID 

The following example shows the GET request to get all the details of a column by specifying its internal ID. 

```
GET https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets/
6bb417e6-94bb-48eb-88b6-7c6887b58fb6?scheme=internal&segments=all
```

The following example shows the request response. The response includes all details of the column such as the summary, system attributes, glossary associations, data profiling statistics, and relationships with other assets. 

```
{
    "core.identity": "5ab1fc47-68aa-4182-ab8e-80c8dc58e95d",
    "core.externalId": "ab2cc915-4b30-396a-9632-bd89f02ddf1c://CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
    "summary": {
        "core.location": "ab2cc915-4b30-396a-9632-bd89f02ddf1c://ab2cc915-4b30-396a-9632-
bd89f02ddf1c/CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
        "core.name": "CDGCTABL31COL2",
        "core.description": ""
    },
    "systemAttributes": {
        "core.modifiedOn": 1692954334099,
        "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
        "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
        "core.classType": "com.infa.odin.models.relational.Column",
        "core.origin": "ab2cc915-4b30-396a-9632-bd89f02ddf1c",
        "type": [
            "core.IClassTechnical",
            "core.IClass",
            "core.DataElement",
            "com.infa.odin.models.relational.Column"
        ],
        "core.createdOn": 1689007685160
    },
    "selfAttributes": {
        "com.infa.odin.models.relational.DatatypeLength": "100",
        "core.reference": false,
        "core.Position": 2,
        "core.rankArray": [],
        "core.patternStatistics": [],
        "com.infa.odin.models.relational.PrimaryKeyColumn": "false",
        "core.mergedObjects": [],
        "core.businessName": "Customer Records",
        "core.profiled": false,
        "core.resourceType": "Snowflake",
        "core.businessDescription": "",
        "core.lastProfiledState": "Not Profiled",
        "com.infa.odin.models.relational.Datatype": "VARCHAR",
        "core.supplement.certified": [
            false
        ],
        "com.infa.odin.models.relational.Nullable": "true",
        "core.assetLifecycle": "Published",
        "core.resourceName": "SnowFlake_10June_22-10",
        "core.inferredDataTypeSummaries": []
    },
    "dataProfile": {
        "core.lastProfiledState": "Not Profiled",
        "core.topNValueFrequenciesSample": [],
        "core.rankArray": [],
        "core.patternStatistics": [],
        "core.profiled": false,
        "core.classType": "com.infa.odin.models.relational.Column",

        "core.identity": "5ab1fc47-68aa-4182-ab8e-80c8dc58e95d",
        "core.origin": "ab2cc915-4b30-396a-9632-bd89f02ddf1c",
        "core.externalId": "ab2cc915-4b30-396a-9632-bd89f02ddf1c://CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
        "core.name": "CDGCTABL31COL2",
        "core.inferredDataTypeSummaries": [],
        "core.description": ""
    },
    "glossary": [
        {
            "core.identity": "7bc6fa52-b563-48a2-9f3a-643b02cc4421",
            "details": "/data360/search/assets/MLMRSCOE-24?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes",
            "core.externalId": "MLMRSCOE-24",
            "core.name": "Customer Records",
            "core.curationStatus": "ACCEPTED"
        }
    ],
    "neighborhood": [
        {
            "type": "com.infa.odin.models.relational.Table",
            "neighbors": [
                {
                    "neighbor": "CDGCTABLE3",
                    "details": "/data360/search/assets/4e648258-9937-4197-
a933-92d35889fbb0?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                    "paths": [
                        {
                            "serialized": "CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/CDGCTABLE3/
CDGCTABL31COL2~com.infa.odin.models.relational.Column ( CDGCTABL31COL2 )  <--
com.infa.odin.models.relational.TableToColumn -- CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3~com.infa.odin.models.relational.Table ( CDGCTABLE3 ) ",
                            "collection": [
                                {
                                    "association":
"com.infa.odin.models.relational.TableToColumn",
                                    "curationStatus": "NONE",
                                    "from": "CDGCTABLE3",
                                    "to": "CDGCTABL31COL2",
                                    "fromType": "com.infa.odin.models.relational.Table",
                                    "toType": "com.infa.odin.models.relational.Column",
                                    "fromLocation": "ab2cc915-4b30-396a-9632-
bd89f02ddf1c://ab2cc915-4b30-396a-9632-bd89f02ddf1c/CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3",
                                    "toLocation": "ab2cc915-4b30-396a-9632-
bd89f02ddf1c://ab2cc915-4b30-396a-9632-bd89f02ddf1c/CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3/CDGCTABL31COL2",
                                    "attributes": {
                                        "core.modifiedOn": 1689007798639,
                                        "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.associationKind": "core.ParentChild",
                                        "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.origin": "ab2cc915-4b30-396a-9632-
bd89f02ddf1c",
                                        "core.createdOn": 1689007798639
                                    },
                                    "details": {
                                        "fromUri": "/data360/search/assets/
4e648258-9937-4197-a933-92d35889fbb0?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                        "toUri": "/data360/search/assets/
5ab1fc47-68aa-4182-ab8e-80c8dc58e95d?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                    },
                                    "fromProperties": {
                                        "core.identity": "4e648258-9937-4197-
a933-92d35889fbb0",
                                        "core.classType":
"com.infa.odin.models.relational.Table",
                                        "core.location": "ab2cc915-4b30-396a-9632-
```

Retrieve details of an asset       35

```
bd89f02ddf1c://ab2cc915-4b30-396a-9632-bd89f02ddf1c/CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3",
                                        "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3~com.infa.odin.models.relational.Table",
                                        "core.name": "CDGCTABLE3"
                                    },
                                    "toProperties": {
                                        "core.identity": "5ab1fc47-68aa-4182-
ab8e-80c8dc58e95d",
                                        "core.classType":
"com.infa.odin.models.relational.Column",
                                        "core.location": "ab2cc915-4b30-396a-9632-
bd89f02ddf1c://ab2cc915-4b30-396a-9632-bd89f02ddf1c/CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3/CDGCTABL31COL2",
                                        "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
                                        "core.name": "CDGCTABL31COL2"
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
        },
        {
            "type": "com.infa.ccgf.models.governance.BusinessTerm",
            "neighbors": [
                {
                    "neighbor": "Customer Records",
                    "details": "/data360/search/assets/MLMRSCOE-24?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes",
                    "paths": [
                        {
                            "serialized": "CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/CDGCTABLE3/
CDGCTABL31COL2~com.infa.odin.models.relational.Column ( CDGCTABL31COL2 )  --
com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase --> MLMRSCOE-24 ( Customer
Records ) ",
                            "collection": [
                                {
                                    "association":
"com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase",
                                    "curationStatus": "ACCEPTED",
                                    "from": "CDGCTABL31COL2",
                                    "to": "Customer Records",
                                    "fromType": "com.infa.odin.models.relational.Column",
                                    "toType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                    "fromLocation": "ab2cc915-4b30-396a-9632-
bd89f02ddf1c://ab2cc915-4b30-396a-9632-bd89f02ddf1c/CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3/CDGCTABL31COL2",
                                    "toLocation": "CDGC://247e6a83-7ddb-4014-bd8b-
eac7018a31e1/7bc6fa52-b563-48a2-9f3a-643b02cc4421",
                                    "attributes": {
                                        "core.modifiedOn": 1692954335818,
                                        "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.associationKind":
"com.infa.ccgf.models.governance.semanticKind",
                                        "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                        "core.inferred": false,
                                        "core.producer": "CDGC",
                                        "core.origin": "CDGC",
                                        "core.createdOn": 1692954335818,
                                        "core.curationStatus": "ACCEPTED"
                                    },
                                    "details": {
                                        "fromUri": "/data360/search/assets/
5ab1fc47-68aa-4182-ab8e-80c8dc58e95d?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                        "toUri": "/data360/search/assets/MLMRSCOE-24?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes"

                                    },
                                    "fromProperties": {
                                        "core.identity": "5ab1fc47-68aa-4182-
ab8e-80c8dc58e95d",
                                        "core.classType":
"com.infa.odin.models.relational.Column",
                                        "core.location": "ab2cc915-4b30-396a-9632-
bd89f02ddf1c://ab2cc915-4b30-396a-9632-bd89f02ddf1c/CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3/CDGCTABL31COL2",
                                        "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
                                        "core.name": "CDGCTABL31COL2"
                                    },
                                    "toProperties": {
                                        "core.identity": "7bc6fa52-
b563-48a2-9f3a-643b02cc4421",
                                        "core.classType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                        "core.location": "CDGC://247e6a83-7ddb-4014-bd8b-
eac7018a31e1/7bc6fa52-b563-48a2-9f3a-643b02cc4421",
                                        "core.externalId": "MLMRSCOE-24",
                                        "core.name": "Customer Records"
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ]
}
```

### Response body

The GET request returns the details of the asset that you specify in the URL. 

The details that the response body displays for business and technical assets are based on the values that you specify in the `segments ` URL parameter while sending the GET request. For more information about the details returned in the response body, see ` segments` in “Request parameters” on page 16. 

## Retrieve details of multiple assets

To view the details of multiple assets in the catalog, send a POST request. While sending the request, you can specify the reference IDs of one or more assets in the body of the request. In the request parameters, specify the type of asset details that you would like to view. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|POST|**<baseApiUrl>/data360/search/v1/assets/details**|


The `<baseApiUrl>` differs for each pod. For more information about the base API URL, see “Send requests” on page 9. 

Retrieve details of multiple assets       37

### Request parameters

In the request URL parameters, specify the type of asset ID that you want to use to search for assets. To limit the API response to specific details of an asset, you can additionally specify the type of asset details that you want the request to return. 

The following table describes the parameters that you can specify in the request URL: 

|**Parameter**|**Description**|
|---|---|
|`scheme `|Specify the type of asset ID you want to use to query assets. Enter one<br>of the following values:<br>-<br>` internal `. Indicates that the asset ID that you specify in the request<br>is the internal ID of the asset.<br>-<br>` external`. Indicates that the asset ID that you specify in the request<br>is the unique reference ID of the asset.|
|`segments`|Specify the type of asset details that you want the API request to<br>return. The default value is` segments=all `. Enter any segment value<br>from the following values:<br>-<br>` all `. Returns the internal ID, external ID, summary, system attributes,<br>and the API request link to view further details of the asset.<br>-<br>` summary `. Returns the name, description, and location of the asset.<br>-<br>` systemAttributes `. Returns the origin, class type, base types, user<br>that created or updated the asset and the date on which the asset<br>was created or updated.<br>-<br>` stakeholdership `. Returns the type of user and role that are<br>assigned as stakeholder for the asset.<br>-<br>` customAttributes `. Returns the custom attributes specified for the<br>asset.<br>-<br>` selfAttributes `. Returns the properties of assets.<br>-<br>` dataProfile `. Returns the profiling statistics of the asset.<br>-<br>` dataClassification `. Returns the data classification associated<br>with the asset.<br>-<br>` dataClassification:all `. Returns all segments of the data<br>classification.<br>-<br>` dataClassification:summary `. Returns the summary of the data<br>classification.<br>-<br>` dataClassification:selfAttributes `. Returns all the<br>attributes of the data classification which are not included in other<br>segments.<br>-<br>` dataClassification:systemAttributes `. Returns the system<br>attributes of the data classification.<br>-<br>` dataClassification:relationshipAttributes `. Returns the<br>relationship attributes between the asset and the data classification.<br>-<br>` glossary `. Returns the glossary associated with the asset.<br>-<br>` glossary:all `. Returns all segments of the glossary.<br>-<br>` glossary:summary `. Returns the summary of the glossary.<br>-<br>` glossary:systemAttributes `. Returns the system attributes of<br>the glossary.<br>-<br>` glossary:selfAttributes `. Returns all the attributes of the<br>glossary that are not included in other segments.<br>-<br>` glossary:relationshipAttributes `. Returns the relationship<br>attributes between the asset and the glossary.<br>-<br>` dataQuality `. Returns the data quality score of the asset.<br>-<br>` dataQuality:all `. Returns all segments of the data quality scores.<br>-<br>` dataQuality:summary `. Returns the summary of the data quality<br>scores.<br>-<br>` dataQuality:systemAttributes`. Returns the system attributes<br>of the data quality scores.|

|**Parameter**|**Description**|
|---|---|
||-<br>`dataQuality:selfAttributes `. Returns all the attributes of data<br>quality scores that are not included in other segments.<br>-<br>` dataQuality:relationshipAttributes `. Returns the<br>relationship attributes between the asset and data quality scores.<br>-<br>` hierarchy `. Returns the immediate child assets of the asset.<br>-<br>` hierarchy:all `. Returns all segments of the immediate child<br>assets in the hierarchy.<br>-<br>` hierarchy:summary `. Returns the summary of the hierarchy.<br>-<br>` hierarchy:selfAttributes `. Returns the properties of the<br>immediate child assets in the hierarchy.<br>-<br>` hierarchy:systemAttributes `. Returns the system attributes of<br>the immediate child assets in the hierarchy.<br>-<br>` hierarchy:customAttributes `. Returns the custom attributes of<br>the immediate assets in the hierarchy.<br>-<br>` neighborhood `. Returns assets that are in a direct relationship with<br>the searched asset.<br>-<br>` neighborhood:all `. Returns assets that are in a direct or indirect<br>relationship with the searched asset.<br>-<br>` neighborhood:<classtype>`. Returns assets of the specified<br>class type that are in a direct relationship with the searched asset.<br>-<br>`lineage-level`. Returns the lineage level of the asset, that is data<br>element or data set level. The default value for` lineage-level ` is<br>` dataset `.<br>-<br>` lineage-direction`. Returns the direction of the asset in the<br>lineage, that is inbound or outbound. The default value for` lineage-direction` is` all `.<br>-<br>` lineage-distance `. Returns the lineage up to a certain distance.<br>-<br>` lineage-level:dataset `. Returns assets that have a direct<br>relationship with the searched asset.<br>-<br>` lineage-direction:all `. Returns assets that are in a direct<br>inbound and outbound relationship with the searched asset.<br>-<br>` lineage-direction:inbound `. Returns assets that are in a direct<br>inbound relationship with the searched asset.<br>-<br>` lineage-direction:outbound `. Returns assets that are in a<br>direct outbound relationship with the searched asset.<br>-<br>` descendants `. Returns the immediate children and related child<br>assets.<br>-<br>` descendants:all `. Returns all the details of the immediate and<br>related child assets.<br>-<br>` descendants:systemAttributes `. Returns the system attributes<br>of the immediate and related child assets.<br>-<br>` descendants:selfAttributes `. Returns the properties of the<br>immediate and related child assets.<br>-<br>` descendants:customAttributes`. Returns the custom attributes<br>of the immediate and related child assets.|


### Request body

Use the request body to specify the external or internal IDs of assets that you want to view. You can specify in the `scheme` request parameter whether you want to search the asset by external ID or internal ID. 

In the body of each API request, specify up to five comma-separated asset IDs. You can specify the asset IDs in the following format: 

- `[ "0c5777c2-ec04-4a57-8bf0-fc895c4579a2",` 

> `"982cd858-b5b0-4cea-b120-cad3c325e38e",` 

Retrieve details of multiple assets       39

```
    "e907f08f-6327-4214-9401-c794bc0c34db",

]
```

If you don't know the ID of a particular asset, you can first send a POST request to view the list of assets in the catalog. This request returns the internal ID of each asset in the `core.identity ` parameter and the unique reference ID of the asset in the ` core.externalId` parameter. For more information, see “Retrieve assets in the catalog” on page 15. 

### Example request

Search for two assets using internal asset IDs 

The following example shows the POST request to list all the details of a column and an associated business term by passing the internal IDs of both the assets in the request body. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/v1/assets/details?
scheme=internal&segments=all
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
Body
[
    "81e8ea5a-c888-4c40-a780-849ac48e66fd",
    "17b1e3ed-f64f-487a-b073-06b78ec30259"
]
```

The following example shows the response for the request. The response returns all the details of the business term 'AddressLine2' and the column 'CDGCTABL31COL2' because the `segments ` parameter in the request is set to ` all` . 

```
[
    {
        "core.identity": "17b1e3ed-f64f-487a-b073-06b78ec30259",
        "core.externalId": "lower-68",
        "summary": {
            "core.location": "CDGC://17b1e3ed-f64f-487a-b073-06b78ec30259",
            "core.name": "AddressLine2"
        },
        "systemAttributes": {
            "core.modifiedOn": 1688816931942,
            "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
            "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
            "core.classType": "com.infa.ccgf.models.governance.BusinessTerm",
            "core.origin": "CDGC",
            "type": [
                "core.IClassBusiness",
                "core.IClass",
                "com.infa.ccgf.models.governance.GlossaryBase",
                "com.infa.ccgf.models.governance.BusinessTerm"
            ],
            "core.createdOn": 1688816931942
        },
        "customAttributes": {
            "com.infa.odin.models.custom.ca_2205258177888237465": "default",
            "com.infa.odin.models.custom.ca_5534006856514172118": "default"
        },
        "selfAttributes": {
            "com.infa.ccgf.models.governance.FormatType": "Text",
            "core.supplement.certified": [
                false
            ],
            "core.assetLifecycle": "Published",
            "core.producer": "CDGC",
            "com.infa.ccgf.models.governance.isCDE": false
        },
        "neighborhood": [
            {

                "type": "com.infa.odin.models.file.flat.FlatField",
                "neighbors": [
                    {
                        "neighbor": "AddressLine2",
                        "details": "/data360/search/assets/86aa9780-99d3-4efb-95ea-
b4d691bb97d0?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                        "paths": [
                            {
                                "serialized": "lower-68 ( AddressLine2 )  <--
com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase -- dqprofiling-dockerpod/
courses/person1.csv/AddressLine2~com.infa.odin.models.file.flat.FlatField
( AddressLine2 ) ",
                                "collection": [
                                    {
                                        "association":
"com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase",
                                        "curationStatus": "AUTO_ACCEPTED",
                                        "from": "AddressLine2",
                                        "to": "AddressLine2",
                                        "fromType":
"com.infa.odin.models.file.flat.FlatField",
                                        "toType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                        "fromLocation": "99068dfb-2c91-34e2-
bd37-5232fde620b1://99068dfb-2c91-34e2-bd37-5232fde620b1/dqprofiling-dockerpod/courses/
person1.csv/AddressLine2",
                                        "toLocation": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "attributes": {
                                            "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.origin": "99068dfb-2c91-34e2-
bd37-5232fde620b1",
                                            "core.modifiedOn": 1689076624370,
                                            "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.confidenceScore": 100.0,
                                            "core.inferred": true,
                                            "core.associationKind":
"com.infa.ccgf.models.governance.semanticKind",
                                            "core.producer": "discovery-capability",
                                            "core.createdOn": 1689076624370,
                                            "core.curationStatus": "AUTO_ACCEPTED"
                                        },
                                        "details": {
                                            "fromUri": "/data360/search/assets/
86aa9780-99d3-4efb-95ea-b4d691bb97d0?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                            "toUri": "/data360/search/assets/lower-68?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                        },
                                        "fromProperties": {
                                            "core.identity": "86aa9780-99d3-4efb-95ea-
b4d691bb97d0",
                                            "core.classType":
"com.infa.odin.models.file.flat.FlatField",
                                            "core.location": "99068dfb-2c91-34e2-
bd37-5232fde620b1://99068dfb-2c91-34e2-bd37-5232fde620b1/dqprofiling-dockerpod/courses/
person1.csv/AddressLine2",
                                            "core.externalId": "dqprofiling-dockerpod/
courses/person1.csv/AddressLine2~com.infa.odin.models.file.flat.FlatField",
                                            "core.name": "AddressLine2"
                                        },
                                        "toProperties": {
                                            "core.identity": "17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                            "core.classType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                            "core.location": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                            "core.externalId": "lower-68",
                                            "core.name": "AddressLine2"
```

Retrieve details of multiple assets       41

```
                                        }
                                    }
                                ]
                            }
                        ]
                    },
                    {
                        "neighbor": "AddressLine2",
                        "details": "/data360/search/assets/00b1dbb0-ee68-43e9-
a833-675b5c8f0ab2?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                        "paths": [
                            {
                                "serialized": "lower-68 ( AddressLine2 )  <--
com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase -- dqprofiling-dockerpod/
courses/person1.csv/AddressLine2~com.infa.odin.models.file.flat.FlatField
( AddressLine2 ) ",
                                "collection": [
                                    {
                                        "association":
"com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase",
                                        "curationStatus": "AUTO_ACCEPTED",
                                        "from": "AddressLine2",
                                        "to": "AddressLine2",
                                        "fromType":
"com.infa.odin.models.file.flat.FlatField",
                                        "toType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                        "fromLocation": "27397996-
ad9a-3f32-8093-2e4b80fcd93c://27397996-ad9a-3f32-8093-2e4b80fcd93c/dqprofiling-dockerpod/
courses/person1.csv/AddressLine2",
                                        "toLocation": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "attributes": {
                                            "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.origin": "27397996-
ad9a-3f32-8093-2e4b80fcd93c",
                                            "core.modifiedOn": 1688819441749,
                                            "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.confidenceScore": 100.0,
                                            "core.inferred": true,
                                            "core.associationKind":
"com.infa.ccgf.models.governance.semanticKind",
                                            "core.producer": "discovery-capability",
                                            "core.createdOn": 1688819441749,
                                            "core.curationStatus": "AUTO_ACCEPTED"
                                        },
                                        "details": {
                                            "fromUri": "/data360/search/assets/00b1dbb0-
ee68-43e9-a833-675b5c8f0ab2?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                            "toUri": "/data360/search/assets/lower-68?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                        },
                                        "fromProperties": {
                                            "core.identity": "00b1dbb0-ee68-43e9-
a833-675b5c8f0ab2",
                                            "core.classType":
"com.infa.odin.models.file.flat.FlatField",
                                            "core.location": "27397996-
ad9a-3f32-8093-2e4b80fcd93c://27397996-ad9a-3f32-8093-2e4b80fcd93c/dqprofiling-dockerpod/
courses/person1.csv/AddressLine2",
                                            "core.externalId": "dqprofiling-dockerpod/
courses/person1.csv/AddressLine2~com.infa.odin.models.file.flat.FlatField",
                                            "core.name": "AddressLine2"
                                        },
                                        "toProperties": {
                                            "core.identity": "17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                            "core.classType":
"com.infa.ccgf.models.governance.BusinessTerm",

                                            "core.location": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                            "core.externalId": "lower-68",
                                            "core.name": "AddressLine2"
                                        }
                                    }
                                ]
                            }
                        ]
                    }
                ]
            },
            {
                "type": "com.infa.odin.models.relational.Column",
                "neighbors": [
                    {
                        "neighbor": "CDGCTABL31COL2",
                        "details": "/data360/search/assets/81e8ea5a-c888-4c40-
a780-849ac48e66fd?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                        "paths": [
                            {
                                "serialized": "lower-68 ( AddressLine2 )  <--
com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase -- CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column
( CDGCTABL31COL2 ) ",
                                "collection": [
                                    {
                                        "association":
"com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase",
                                        "curationStatus": "ACCEPTED",
                                        "from": "CDGCTABL31COL2",
                                        "to": "AddressLine2",
                                        "fromType":
"com.infa.odin.models.relational.Column",
                                        "toType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                        "fromLocation": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                        "toLocation": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "attributes": {
                                            "core.modifiedOn": 1692806775314,
                                            "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.associationKind":
"com.infa.ccgf.models.governance.semanticKind",
                                            "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.inferred": false,
                                            "core.producer": "CDGC",
                                            "core.origin": "CDGC",
                                            "core.createdOn": 1692806775314,
                                            "core.curationStatus": "ACCEPTED"
                                        },
                                        "details": {
                                            "fromUri": "/data360/search/assets/81e8ea5a-
c888-4c40-a780-849ac48e66fd?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                            "toUri": "/data360/search/assets/lower-68?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                        },
                                        "fromProperties": {
                                            "core.identity": "81e8ea5a-c888-4c40-
a780-849ac48e66fd",
                                            "core.classType":
"com.infa.odin.models.relational.Column",
                                            "core.location": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                            "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
```

Retrieve details of multiple assets       43

```
                                            "core.name": "CDGCTABL31COL2"
                                        },
                                        "toProperties": {
                                            "core.identity": "17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                            "core.classType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                            "core.location": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                            "core.externalId": "lower-68",
                                            "core.name": "AddressLine2"
                                        }
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        ]
    },
    {
        "core.identity": "81e8ea5a-c888-4c40-a780-849ac48e66fd",
        "core.externalId": "d4865a0f-406b-363e-b850-2fc633b3f77b://CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
        "summary": {
            "core.location": "d4865a0f-406b-363e-b850-2fc633b3f77b://d4865a0f-406b-363e-
b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
            "core.name": "CDGCTABL31COL2",
            "core.description": ""
        },
        "systemAttributes": {
            "core.modifiedOn": 1692806773089,
            "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
            "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
            "core.classType": "com.infa.odin.models.relational.Column",
            "core.origin": "d4865a0f-406b-363e-b850-2fc633b3f77b",
            "type": [
                "core.IClassTechnical",
                "core.IClass",
                "core.DataElement",
                "com.infa.odin.models.relational.Column"
            ],
            "core.createdOn": 1688650669237
        },
        "selfAttributes": {
            "com.infa.odin.models.relational.DatatypeLength": "100",
            "core.reference": false,
            "core.Position": 2,
            "core.rankArray": [],
            "core.patternStatistics": [],
            "com.infa.odin.models.relational.PrimaryKeyColumn": "false",
            "core.mergedObjects": [],
            "core.businessName": "AddressLine2",
            "core.profiled": false,
            "core.resourceType": "Snowflake",
            "core.businessDescription": "",
            "core.lastProfiledState": "Not Profiled",
            "com.infa.odin.models.relational.Datatype": "VARCHAR",
            "core.supplement.certified": [
                false
            ],
            "com.infa.odin.models.relational.Nullable": "true",
            "core.assetLifecycle": "Published",
            "core.resourceName": "SnowFlake_CustCheck",
            "core.inferredDataTypeSummaries": []
        },
        "dataProfile": {
            "core.lastProfiledState": "Not Profiled",
            "core.topNValueFrequenciesSample": [],
            "core.rankArray": [],

            "core.patternStatistics": [],
            "core.profiled": false,
            "core.classType": "com.infa.odin.models.relational.Column",
            "core.identity": "81e8ea5a-c888-4c40-a780-849ac48e66fd",
            "core.origin": "d4865a0f-406b-363e-b850-2fc633b3f77b",
            "core.externalId": "d4865a0f-406b-363e-b850-2fc633b3f77b://CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
            "core.name": "CDGCTABL31COL2",
            "core.inferredDataTypeSummaries": [],
            "core.description": ""
        },
        "glossary": [
            {
                "core.identity": "17b1e3ed-f64f-487a-b073-06b78ec30259",
                "details": "/data360/search/assets/lower-68?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes",
                "core.externalId": "lower-68",
                "core.name": "AddressLine2",
                "core.curationStatus": "ACCEPTED"
            }
        ],
        "neighborhood": [
            {
                "type": "com.infa.odin.models.relational.Table",
                "neighbors": [
                    {
                        "neighbor": "CDGCTABLE3",
                        "details": "/data360/search/assets/44ef664f-90ef-4ac2-af4d-
a99f7670081b?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                        "paths": [
                            {
                                "serialized": "CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column ( CDGCTABL31COL2 )  <--
com.infa.odin.models.relational.TableToColumn -- CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3~com.infa.odin.models.relational.Table ( CDGCTABLE3 ) ",
                                "collection": [
                                    {
                                        "association":
"com.infa.odin.models.relational.TableToColumn",
                                        "curationStatus": "NONE",
                                        "from": "CDGCTABLE3",
                                        "to": "CDGCTABL31COL2",
                                        "fromType":
"com.infa.odin.models.relational.Table",
                                        "toType":
"com.infa.odin.models.relational.Column",
                                        "fromLocation": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3",
                                        "toLocation": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                        "attributes": {
                                            "core.modifiedOn": 1688650827020,
                                            "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.associationKind": "core.ParentChild",
                                            "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.origin": "d4865a0f-406b-363e-
b850-2fc633b3f77b",
                                            "core.createdOn": 1688650827020
                                        },
                                        "details": {
                                            "fromUri": "/data360/search/assets/
44ef664f-90ef-4ac2-af4d-a99f7670081b?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                            "toUri": "/data360/search/assets/81e8ea5a-
c888-4c40-a780-849ac48e66fd?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                        },
                                        "fromProperties": {
```

Retrieve details of multiple assets       45

```
                                            "core.identity": "44ef664f-90ef-4ac2-af4d-
a99f7670081b",
                                            "core.classType":
"com.infa.odin.models.relational.Table",
                                            "core.location": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3",
                                            "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3~com.infa.odin.models.relational.Table",
                                            "core.name": "CDGCTABLE3"
                                        },
                                        "toProperties": {
                                            "core.identity": "81e8ea5a-c888-4c40-
a780-849ac48e66fd",
                                            "core.classType":
"com.infa.odin.models.relational.Column",
                                            "core.location": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                            "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
                                            "core.name": "CDGCTABL31COL2"
                                        }
                                    }
                                ]

                            }
                        ]
                    }
                ]
            },
            {
                "type": "com.infa.odin.models.relational.ViewColumn",
                "neighbors": [
                    {
                        "neighbor": "CDGCVIEW3COL2",
                        "details": "/data360/search/assets/
a1172b78-75cb-4485-88dd-8b407441187a?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                        "paths": [
                            {
                                "serialized": "CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column ( CDGCTABL31COL2 )  --
core.DirectionalDataFlow --> CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/CDGCVIEW3/
CDGCVIEW3COL2~com.infa.odin.models.relational.ViewColumn ( CDGCVIEW3COL2 ) ",
                                "collection": [
                                    {
                                        "association": "core.DirectionalDataFlow",
                                        "curationStatus": "NONE",
                                        "from": "CDGCTABL31COL2",
                                        "to": "CDGCVIEW3COL2",
                                        "fromType":
"com.infa.odin.models.relational.Column",
                                        "toType":
"com.infa.odin.models.relational.ViewColumn",
                                        "fromLocation": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                        "toLocation": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCVIEW3/CDGCVIEW3COL2",
                                        "attributes": {
                                            "core.modifiedOn": 1688650826911,
                                            "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.associationKind": "core.DataFlow",
                                            "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.origin": "d4865a0f-406b-363e-
b850-2fc633b3f77b",
                                            "core.createdOn": 1688650826911
                                        },
                                        "details": {
                                            "fromUri": "/data360/search/assets/81e8ea5a-

c888-4c40-a780-849ac48e66fd?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                            "toUri": "/data360/search/assets/
a1172b78-75cb-4485-88dd-8b407441187a?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                        },
                                        "fromProperties": {
                                            "core.identity": "81e8ea5a-c888-4c40-
a780-849ac48e66fd",
                                            "core.classType":
"com.infa.odin.models.relational.Column",
                                            "core.location": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                            "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
                                            "core.name": "CDGCTABL31COL2"
                                        },
                                        "toProperties": {
                                            "core.identity":
"a1172b78-75cb-4485-88dd-8b407441187a",
                                            "core.classType":
"com.infa.odin.models.relational.ViewColumn",
                                            "core.location": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCVIEW3/CDGCVIEW3COL2",
                                            "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCVIEW3/CDGCVIEW3COL2~com.infa.odin.models.relational.ViewColumn",
                                            "core.name": "CDGCVIEW3COL2"
                                        }
                                    }
                                ]
                            }
                        ]
                    }
                ]
            },
            {
                "type": "com.infa.ccgf.models.governance.BusinessTerm",
                "neighbors": [
                    {
                        "neighbor": "AddressLine2",
                        "details": "/data360/search/assets/lower-68?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes",
                        "paths": [
                            {
                                "serialized": "CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/
CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column ( CDGCTABL31COL2 )  --
com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase --> lower-68
( AddressLine2 ) ",
                                "collection": [
                                    {
                                        "association":
"com.infa.ccgf.models.governance.IClassTechnicalGlossaryBase",
                                        "curationStatus": "ACCEPTED",
                                        "from": "CDGCTABL31COL2",
                                        "to": "AddressLine2",
                                        "fromType":
"com.infa.odin.models.relational.Column",
                                        "toType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                        "fromLocation": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                        "toLocation": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                        "attributes": {
                                            "core.modifiedOn": 1692806775314,
                                            "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.associationKind":
"com.infa.ccgf.models.governance.semanticKind",
```

Retrieve details of multiple assets       47

```
                                            "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                            "core.createdOn": 1692806775314,
                                            "core.curationStatus": "ACCEPTED"
                                            "fromUri": "/data360/search/assets/81e8ea5a-

c888-4c40-a780-849ac48e66fd?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                            "toUri": "/data360/search/assets/lower-68?
scheme=external&segments=summary,systemAttributes,customAttributes,selfAttributes"
                                        },
                                        "fromProperties": {
                                            "core.identity": "81e8ea5a-c888-4c40-
a780-849ac48e66fd",
                                            "core.classType":
"com.infa.odin.models.relational.Column",
                                            "core.location": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                            "core.externalId": "CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2~com.infa.odin.models.relational.Column",
                                            "core.name": "CDGCTABL31COL2"
                                        },
                                        "toProperties": {
                                            "core.identity": "17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                            "core.classType":
"com.infa.ccgf.models.governance.BusinessTerm",
                                            "core.location": "CDGC://17b1e3ed-f64f-487a-
b073-06b78ec30259",
                                            "core.externalId": "lower-68",
                                            "core.name": "AddressLine2"
                                        }
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        ],
        "lineage": [
            {
                "direction": "outbound",
                "hops": [
                    {
                        "distance": 1,
                        "items": [
                            {
                                "from": "CDGCTABL31COL2",
                                "to": "CDGCVIEW3COL2",
                                "fromType": "com.infa.odin.models.relational.Column",
                                "toType": "com.infa.odin.models.relational.ViewColumn",
                                "fromLocation": "d4865a0f-406b-363e-
b850-2fc633b3f77b://d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/
CDGC_QE_SCHEMA/CDGCTABLE3/CDGCTABL31COL2",
                                "toLocation": "d4865a0f-406b-363e-b850-2fc633b3f77b://
d4865a0f-406b-363e-b850-2fc633b3f77b/CDGC_QE_SNOWFLAKE/CDGC_QE_SCHEMA/CDGCVIEW3/
CDGCVIEW3COL2",
                                "attributes": {
                                    "core.modifiedOn": 1688650826911,
                                    "core.createdBy": "5gwD6d18cwPeUaAJAyev2V",
                                    "core.associationKind": "core.DataFlow",
                                    "core.modifiedBy": "5gwD6d18cwPeUaAJAyev2V",
                                    "__meta.association": "core.DirectionalDataFlow",
                                    "core.origin": "d4865a0f-406b-363e-
b850-2fc633b3f77b",
                                    "core.createdOn": 1688650826911

                                },
                                "details": {
                                "fromUri": "/data360/search/v1/assets/812526be-c8e0-48f9-
a82c-53055cb5d0e3?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes",
                                "toUri": "/data360/search/v1/assets/
7635cb3e-3425-4440-9244-ce516f197ec5?
scheme=internal&segments=summary,systemAttributes,customAttributes,selfAttributes"
                            },
                            "fromProperties": {
                                "core.identity": "812526be-c8e0-48f9-a82c-53055cb5d0e3",
                                "core.classType": "core.DataElement",
                                "core.location": "f2c12406-bb40-377e-
ae1d-56574cf056e5://f2c12406-bb40-377e-ae1d-56574cf056e5/f2c12406-bb40-377e-
ae1d-56574cf056e5_S3__infagcstest/S3__infagcstest/rodfolder/bucket",
                                "core.externalId": "f2c12406-bb40-377e-
ae1d-56574cf056e5_S3__infagcstest://S3__infagcstest/rodfolder/bucket~core.DataElement",
                                "core.name": "bucket"
                            },
                            "toProperties": {
                                "core.identity": "7635cb3e-3425-4440-9244-ce516f197ec5",
                                "core.classType":
"com.infa.odin.models.relational.ExternalColumn",
                                "core.location": "f2c12406-bb40-377e-
ae1d-56574cf056e5://f2c12406-bb40-377e-ae1d-56574cf056e5/ATHENA-DB/gcsinstance/
account_managers/bucket",
                                "core.externalId": "f2c12406-bb40-377e-
ae1d-56574cf056e5://ATHENA-DB/gcsinstance/account_managers/
bucket~com.infa.odin.models.relational.ExternalColumn",
                                "core.name": "bucket"
                                }
                            }
                        ]
                    }
                ]
            }
        ]
    }
]
```

Retrieve details of multiple assets       49

# Chapter 4: Manage assets

Use APIs to create, update, and delete business assets and to update technical assets. 

The following image shows the overall process of using the create, update, and delete assets API and the expected response for each endpoint: 


<!-- Start of picture text -->
POST<br>the Login APL Generate a JWtoken DELETE<br>ee OC an ert: data360/content/vi/asse<br>,ce eSatencends ee muthorztion:Bearer Token 1D>?scheme=<EXTERNALoeINTERNAL> endpoint- D>2scheme=<EXTERNAhe fs : TERNAL<br>— Request Header: ‘Authorizati: Bearer T o kenn INTERNAL endpoint<br>‘nee raed ‘Accept:Content-Type:application/jsonppleaton/ion :<br>aa oma<br>2Seters ‘Contp e nt-Type:e application/json<br>’ |‘M1 (scheme, segments)<br>ol 1<br>‘ —<br>Pelee’ Peofeeat tResponse:<br>a ‘<br>+ Add/renlee/remove<br>‘"pet :eeesahepaahe parent<br>+=eBasicerrsdetails of .ratachewseear© pevieesdctostakeholder<br>description,Po) | classification<br>oh=a ,<br>pe<br>y<br><!-- End of picture text -->

## Create assets

To create business assets, send a POST request. 

To create business assets, submit a request with the following method and endpoint: 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|POST|**<baseApiUrl>/data360/content/v1/assets**|


The base API URL differs for each pod. For more information, see Send Requests.

### Request body

Use the request body to specify the parameters of the business assets that you want to create. 

In the body of the request, specify the parameters in the following format: 

```
{
    "core.classType": "com.infa.ccgf.models.governance.BusinessTerm",
    "summary": {
        "core.name": "asset name",
        "core.description": "asset description"
    }
}
```

The following table describes the important parameters that you can specify in the body of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`core.classType `|String|Specify the type of business asset.<br>For example,` com.infa.ccgf.models.governance.BusinessTerm`<br>To see the list of values that you can enter for this parameter, see<br>“<br>Classtypesforbusinessassets<br>”<br>onpage<br>141|
|`summary `|String|Contains the name and the basic description of the asset.<br>` core.name `: Specify the name of the asset<br>` core.description`: Specify a descriptive text explaining the asset.|
|`selfAttributes`|String|Optionally specify the properties of the asset. For more information about the<br>properties of business assets, see the_Understanding Business Assets_help.|
|`stakeholdership `|Object|Optionally specify the following attributes to add a stakeholder for the asset.<br>` core.identity `: The internal ID of the stakeholder that you want to add. You can<br>obtain this ID by looking at the URL of the stakeholder page in Data Governance and<br>Catalog.<br>` core.externalid `: The unique reference ID of the stakeholder.<br>` core.role`: Specify the stakeholder role.<br>**Note:**You can obtain the` core.role` ID by checking the user role details in<br>Administrator. Navigate to the**User Roles**page, open the user role, and copy the ID at<br>the end of the URL.|
|`parent`|Object|If you want the asset to be a part of a hierarchy, specify the details of the parent<br>asset.|


### Example requests

#### Create a business term

The following example shows the POST request to create a business term. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
{
  "core.classType": "com.infa.ccgf.models.governance.BusinessTerm",
  "summary": {
    "core.name": "Net Profit",
    "core.description": "Net income value of the Income Statement"
  },
  "selfAttributes": {
```

Create assets       51

```
    "com.infa.ccgf.models.governance.FormatType": "Text",
    "com.infa.ccgf.models.governance.isCDE": false,
    "com.infa.ccgf.models.governance.AliasNames": [
      "Net Profit"
    ]
  }
}
```

#### Create a business term with a parent

The following example shows the POST request to create a business term with a parent. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
{
  "core.classType": "com.infa.ccgf.models.governance.BusinessTerm",
  "summary": {
    "core.name": "Operating Profit",
    "core.description": "Profit for operational activities, which excludes EBIDA"
  },
  "selfAttributes": {
    "com.infa.ccgf.models.governance.FormatType": "Text",
    "com.infa.ccgf.models.governance.isCDE": false,
    "com.infa.ccgf.models.governance.AliasNames": [
      "Operating Income"
    ]
  },
  "parent": {
    "core.identity": "5eef85fa-a38e-4ab4-89d3-8df7d26425dc",
    "core.externalId": "Financial Metrics"
  }
}
```

#### Create a business term with a stakeholder

The following example shows the POST request to create a business term with a stakeholder. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
{
  "core.classType": "com.infa.ccgf.models.governance.BusinessTerm",
  "summary": {
    "core.name": "Cost of Goods Sold",
    "core.description": "Cost value of items sold, which includes the cost of production"
  },
  "selfAttributes": {
    "com.infa.ccgf.models.governance.FormatType": "Text",
    "com.infa.ccgf.models.governance.isCDE": false,
    "com.infa.ccgf.models.governance.AliasNames": [
      "bt-test-alias-2"
    ]
  },
  "stakeholdership": [
    {
      "core.identity": "5eef85fa-a38e-4ab4-89d3-8df7d26425dc",
      "core.externalId": "2vr1gGFwE02kdKX0cxJt4W",
      "core.role": [
        "lVhW8Etrr2Xh1j30320u40"
      ]
    }
  ]
}
```

## Update assets

To update business assets, send a PATCH request. You can also create and remove relationships between assets and enrich technical assets with business context. 

Submit a request with the following method and endpoint. Specify the asset ID and the scheme of the asset that you want to update. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|PATCH|**<baseApiUrl>/data360/content/v1/assets/<assetID>?scheme=<EXTERNAL/INTERNAL>**|


The base API URL differs for each pod. For more information, see Send Requests. 

### Request parameters

Enter the specific parameters in the URL to update assets and create or remove relationships between assets. 

The following table describes the parameters that you can specify in the URL: 

|**Parameter**|**Description**|
|---|---|
|`assetID`|The internal ID or the unique reference ID of the asset that you want to update.|
|`scheme`|Specify the type of asset ID value that you have entered in the` assetID ` parameter. You can enter either<br>internal ID or external ID to update assets. Enter one of the following values:<br>-<br>` INTERNAL `. Indicates that the asset ID that you specify in the request is the internal ID of the asset.<br>**Note:**You can obtain the internal ID by looking at the URL of the asset page in the Data Governance<br>and Catalog application.<br>-<br>` EXTERNAL`. Indicates that the asset ID that you specify in the request is the unique reference ID of the<br>asset.|


### Request body

Use the request body to specify the details of the update that you want to make for the asset. You can also update multiple segments for an asset. 

In the body of the request, specify the parameters in the following format: 

```
[
  {
    "operation": "add",
    "segment": "summary",
    "attributes": {
      "core.description": "This is a table"
"fromIdentity": "3375e658-2d20-43bb-a74f-fc1f5ee7b418",
"toIdentity": "9a6b176a-7492-4810-bf77-62e0107df4e1",
"association": "com.infa.ccgf.models.governance.relatedBusinessTerm"
    }
  }
]
```

Update assets       53

The following table describes the important parameters that you can specify in the body of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`operation`|String|Enter the following values:<br>**•** add<br>**•** remove<br>**•** replace|
|`segment`|Object|Specify the type of update that you<br>want to make for the asset. The<br>default value is` segments=all `. You<br>can enter the following segment<br>values such as:` summary `: Updates<br>the name, description, and the<br>location of the asset.<br>` stakeholdership `: Updates the<br>stakeholder information.<br>` data classification `: Updates<br>the data classification details.<br>` parent`: Updates the parent details.<br>To plan response payloads, see<br>“<br>FilesizelimitsforAPIpayloadfields<br>”<br>onpage<br>142<br>For more information about the<br>properties of business assets, see<br>the_Understanding Business Assets_<br>help.<br>For more information about the<br>properties of technical assets, see<br>the_Understanding Technical Assets_<br>help.|
|`fromIdentity`|String|If you want to create or remove a<br>relationship between the asset and<br>another asset, enter the source of<br>the asset. This is the internal ID of<br>the source asset.|
|`fromExternalIdentity`|String|If you want to create or remove a<br>relationship between the asset and<br>another asset, enter the source of<br>the asset. This is the external ID of<br>the source asset.|
|`toIdentity`|String|If you want to create or remove a<br>relationship between the asset and<br>another asset, enter the target ID of<br>the asset. This is the internal ID of<br>the target asset.|

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`toExternalIdentity`|String|If you want to create or remove a<br>relationship between the asset and<br>another asset, enter the target ID of<br>the asset. This is the external ID of<br>the target asset.|
|`association`|String|If you want to create or remove the<br>relationship between the asset and<br>other assets, specify the type of<br>relationship.|


### Example requests

#### Create relationship

The following example shows the PATCH request to update an asset by creating an "is related to" relationship between two business terms. 

```
PATCH https://cdgc-api.ebf.infaqa.com/ccgf-contentv2/v1/assets/3375e658-2d20-43bb-a74f-
fc1f5ee7b418?scheme=internal
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
{
        "operation": "add",
        "segment": "relationship",
        "items": [
            {
                "fromIdentity": "3375e658-2d20-43bb-a74f-fc1f5ee7b418",
                "toIdentity": "9a6b176a-7492-4810-bf77-62e0107df4e1",
                "association": "com.infa.ccgf.models.governance.relatedBusinessTerm"
            }
        ]
    },
```

#### Delete relationship

The following example shows the PATCH request to delete the "is classified by" relationship between a business term and a metric. 

```
PATCH https://cdgc-api.ebf.infaqa.com/ccgf-contentv2/v1/assets/3375e658-2d20-43bb-a74f-
fc1f5ee7b418?scheme=internal
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
{
        "operation": "remove",
        "segment": "relationship",
        "items": [
            {
                "fromIdentity": "fa1bce9d-2c00-4de3-b3cd-26f5f26b2a29",
                "toIdentity": "0c882d48-f4c5-4abb-8017-852efd389978",
                "association":
"com.infa.ccgf.models.governance.businessTermClassifiedByMetric"
            }
```

Update assets       55

#### Add summary

The following example shows the PATCH request to add the description of a business term. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "add",
    "segment": "summary",
    "attributes": {
      "core.description": "A combination of account number and sort code that represents
an individual's account within a particular financial institution."
    }
  }
]
```

#### Replace summary

The following example shows the PATCH request to replace the description of a business term. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "replace",
    "segment": "summary",
    "attributes": {
      "core.description": "A unique combination of an account number and sort code that
identifies an individual's account at a specific financial institution."
    }
  }
]
```

#### Remove summary

The following example shows the PATCH request to remove the description of a business term. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "remove",
    "segment": "summary",
    "attributes": {
      "core.description": "A unique combination of an account number and sort code that
identifies an individual's account at a specific financial institution"
    }
  }
]
```

#### Add asset properties

The following example shows the PATCH request to mark a business term as critical data element. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json

Content-Type: application/json
Body
[
  {
    "operation": "add",
    "segment": "selfAttributes",
    "attributes": {
      "com.infa.ccgf.models.governance.isCDE": true
    }
  }
]
```

#### Add custom attributes

The following example shows the PATCH request to add a custom attribute value to an asset 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json[
  {
    "operation": "add",
    "segment": "customAttributes",
    "attributes": {
        "com.infa.odin.models.custom.ca_6004001966368974780": "My Custom Attribute Value"
    }
  }
]
```

#### Replace parent

The following example shows the PATCH request to replace the parent of an asset. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "replace",
    "attributes": {
      "parent": {
        "core.externalId": "BTwithParent 1682"
      }
    }
  }
]
```

#### Add stakeholder

The following example shows the PATCH request to add a stakeholder to an existing asset. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "add",
    "segment": "stakeholdership",
    "items": [
      {
        "core.identity": "4dc44acf-599a-32ec-9796-690f0a990069",
        "core.role": [
          "lVhW8Etrr2Xh1j30320u40"
        ]
```

Update assets       57

```
      }
    ]
```

Add glossary 

The following example shows the PATCH request to add a glossary. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "add",
    "segment": "glossary",
    "items": [
      {
        "core.identity": "8d41f699-5ddf-45c7-a11d-f5462791a50c"
      },
      {
        "core.externalId": "BTwithParent 1693",
        "core.curationStatus": "ACCEPTED"
      }
    ]
  }
]
```

#### Replace glossary

The following example shows the PATCH request to update the curation status of the glossary. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "replace",
    "segment": "glossary",
    "attributes": {
      "core.externalId": "BTwithParent 1693",
      "core.curationStatus": "REJECTED"
    }
  }
]
```

#### Remove glossary

The following example shows the PATCH request to remove a glossary. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "remove",
    "segment": "glossary",
    "items": [
      {
        "core.identity": "8d41f699-5ddf-45c7-a11d-f5462791a50c"
      }
    ]

  }
]
```

#### Add data classification

The following example shows the PATCH request to add a data classification. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "add",
    "segment": "dataClassification",
    "items": [
      {
        "core.externalId": "8397ebd1-228d-4ad8-9baa-41cfb672b167",
        "core.curationStatus": "ACCEPTED"
      },
      {
        "core.externalId": "5491a201-8f65-4f17-bb0f-7f6205c44e4c"
      },
      {
        "core.externalId": "c1c46611-5245-4cab-b5c4-07d77a708413"
      }
    ]
  }
]
```

#### Replace data classification

The following example shows the PATCH request to replace the curation status of a data classification. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
  {
    "operation": "replace",
    "segment": "dataClassification",
    "items": [
      {
        "core.externalId": "8397ebd1-228d-4ad8-9baa-41cfb672b167",
        "core.curationStatus": "ACCEPTED"
      }
    ]
  }
]
```

#### Update multiple segments

The following example shows the PATCH request to update multiple segments to an asset. 

```
PATCH https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/TERM-48/?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
Body
[
        {
          "operation": "replace",
          "segment": "summary",
          "attributes": {
            "core.description": "A unique pair of account number and sort code that
identifies an individual’s account at a specific bank."
```

Update assets       59

```
          }
        },
        {
          "operation": "add",
          "segment": "selfAttributes",
          "attributes": {
            "com.infa.ccgf.models.governance.AliasNames": [
              "Account Number",
              "Automation"
            ],
            "com.infa.ccgf.models.governance.isCDE": true
          }
        }
      ]
```

## Delete assets

To delete business assets, send a DELETE request. 

Specify the asset ID and the scheme of the asset you want to delete. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|DELETE|**<baseApiUrl>/data360/content/v1/assets/<assetID>?scheme=<INTERNAL/EXTERNAL>**|


The base API URL differs for each pod. For more information, see Send Requests. 

### Request parameters

Enter the specific parameters in the URL to delete assets. 

The following table describes the important parameters that you can specify in the request URL: 

|**Parameter**|**Description**|
|---|---|
|`assetID`|The asset ID you want to delete.|
|`scheme `|Specify the type of asset ID you want to use to delete assets. You can enter either internal ID or external<br>ID to update assets. Enter one of the following values:<br>-<br>` INTERNAL `. Indicates that the asset ID that you specify in the request is the internal ID of the asset.<br>**Note:**You can obtain the internal ID by looking at the URL of the asset page in the Data Governance<br>and Catalog application.<br>-<br>` EXTERNAL`. Indicates that the asset ID that you specify in the request is the unique reference ID of the<br>asset.|


#### Example request

The following is an example of the request parameters to delete an asset: 

```
DELETE https://idmc-api.dm-us.informaticacloud.com/data360/content/v1/assets/BT-45?
scheme=external
Authorization: Bearer <jwt_token>
Accept: application/json
Content-Type: application/json
```

### Example response

Send a DELETE request to delete assets. 

The following is an example of a response confirming deletion of an asset: 

```
{

  "statusCode": 201,

  "messageCode": "Asset with id: BT-45 deleted."

}
```

Delete assets       61

# Chapter 5: Manage data quality scores

Use APIs to import and retrieve data quality scores for data quality rule occurrences. 

## Import data quality scores

Use the import data quality score API to import data quality scores to data quality rule occurrences. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|POST|**<baseApiUrl>/data-quality/v1/rule-occurrences/runs**|


The `<baseApiUrl>` differs for each pod. For more information, see Send Requests. 

If you update data quality scores through API, the updated data quality scores can take up to one hour to reflect in the **Total Runs Table** of the **Score** tab of a rule occurrence. 

### Prerequisites

Before you import data quality scores to data quality rule occurrences, verify that your organization administrator has assigned you the appropriate permission. 

You can import data quality scores only if your administrator grants you the **Update** permission on data quality rule occurrences through access policies in Metadata Command Center. 

### Request body

Use the request body to upload the import file with scores for data quality rule occurrences. 

The following table describes the parameter that you can specify in the body of the request: 

|**Parameter**|**Description**|
|---|---|
|`file`|Specify the full path to an import file. The import file must contain the data quality scores of the rule<br>occurrences that you want to import into Data Governance and Catalog.<br>You can upload only one CSV file per request, with a maximum file size of 10 MB.|
||Ensure that you populate all the fields of the file that you import.|

The following table describes the columns of the import file: 

|**Column**|**Description**|
|---|---|
|Reference ID|Enter the reference ID for the data quality rule occurrence for which you want to import the data<br>quality score.|
|Score|Specify the data quality rule score for the asset that corresponds to the rule occurrence.<br>Type of Entry: Numeric value.<br>Enter a value between 0 and 100 without the percent sign.<br>**Note:**When you upload data quality scores in Data Governance and Catalog using bulk upload, the<br>scores are automatically rounded to two decimal places. If you set a score with more than two<br>decimal points, the score is rounded to the nearest value and retains only two decimal places.|
|Total Rows|Specify the total number of rows for which the data quality rule is run.<br>Type of Entry: Numeric value.<br>Enter a positive integer value.|
|Failed Rows|Specify the number of rows where the data quality rule run failed.<br>Type of Entry: Numeric value.<br>Enter a positive integer value.|
|Scanned<br>Time|Specify the date and time on which the data quality rule was run on the assets.<br>Type of Entry: To specify a date and time, use the ` YYYY-MM-dd'T'HH:mm:ss.SSS'Z'` format.<br>Example:`2000-10-31T01:30:00.000-05:00`|
|Exception<br>File Path|Specify the path where the exception records are stored. An exception is a record that fails to meet<br>the criteria defined by the data quality rule occurrence.<br>Type of Entry: Formatted text.<br>Enter the complete path of the location of the file where the exception records are stored. Ensure that<br>the path includes the name and extension of the file. Do not enter hyperlinks.|


### Example request

The following example shows a request to import data quality scores to data quality rule occurrences: 

#### **Request query**

```
POST https://idmc-api.dm-us.informaticacloud.com/data-quality/v1/rule-occurrences/
runs
Authorization: Bearer <jwt_token>
Content-Type: multipart/form-data
X-INFA-ORG-ID:<Org ID>
```

#### **Request body**

```
file: SalesScores.csv
```

### Response body

When you send a POST request to import data quality scores to data quality rule occurrences, the API triggers a job to import the scores. 

The following example shows the response of the POST request to import data quality scores from the file `SalesScores.csv` : 

```
{
```

Import data quality scores       63

```
    "jobId": "6a084b71-3696-4a50-b83b-9ef0f2c7f30d",

    "jobUri": "/data360/observable/v1/jobs/6a084b71-3696-4a50-b83b-9ef0f2c7f30d"
}
```

The following table describes the response body parameters for the import API request: 

|**Parameter**|**Description**|
|---|---|
|`jobId`|Unique ID of the import job that the request triggers.|
|`jobUri`|Job tracking URI that you can use to send a GET request to monitor the status of the import job.|


A maximum of 5 jobs can be active at a time. 

For more information about using the jobs API to monitor the status of jobs, see Chapter 10, “Monitor jobs” on page 116. 

## Get data quality scores

Use the get data quality score API to retrieve the score of a data quality rule occurrence. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|GET|**<baseApiUrl>/data360/data-quality/v1/rule-occurrences/<rule-occurrence-id>/runs**|


The `<baseApiUrl>` differs for each pod. For more information, see Send Requests. 

For the `<rule-occurrence-id>` parameter, you can enter either the internal ID or the reference ID of a data quality rule occurrence. You can specify in the `scheme` parameter of the request URL whether you want to search the rule occurrence by reference ID or internal ID. 

If you don't know the ID of a particular data quality rule occurrence, open the rule occurrence from the Data Governance and Catalog user interface. The data quality rule occurrence page's URL contains its internal ID.

### Request body

In the request URL parameters, specify the type of ID that you want to use to retrieve the data quality rule occurrence. 

The following table describes the parameters that you can specify in the request URL: 

|**Parameter**|**Description**|
|---|---|
|`scheme `|Specify the type of asset ID you want to use to query the data quality<br>rule occurrence. Enter one of the following values:<br>-<br>` internal `. Indicates that the asset ID that you specify in the request<br>is the internal ID of the data quality rule occurrence.<br>-<br>` external`. Indicates that the asset ID that you specify in the request<br>is the unique reference ID of the data quality rule occurrence.|
|`filters `|Specify the timestamp to filter the results by the date and time on<br>which the asset was last updated.<br>Use the following format to apply filters:<br>` field name:operator:(field value)`<br>To specify the time range, use the following operators:<br>-<br>`GE `. Greater than or equal to<br>-<br>` LE `. Less than or equal to<br>For example, enter the following:` timestamp:GE:`<br>`(2024-01-29T18:21:14.564Z)`.|
|`offset`|Specify a numeric value to set an offset for pagination. The default<br>value is zero.|
|`limit`|Specify a numeric value between 1 and 100 for the number of records<br>that you want to view on the page. The default value is 100.|
|`sort`|Sort the results by timestamps. By default, the results are sorted in the<br>descending order of the time of its events.<br>For example, you can enter` timestamp:ASC` to sort the results in the<br>ascending order of the time of their events.|


### Example request

The following example shows the GET request to retrieve the score of a data quality rule occurrence: 

```
GET https://idmc-api.dm-us.informaticacloud.com/data360/data-quality/v1/rule-
occurrences/612adac1-5863-468f-a38c-fb3ba0a5686e/runs?
offset=0&limit=100&scheme=INTERNAL&filter=timestamp%3AGE
%3A(2025-05-01T18%3A21%3A14.564Z)&filter=timestamp%3ALE
%3A(2025-07-01T18%3A21%3A14.564Z)&sort=timestamp%3ADESC'
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
```

### Response body

The GET request retrieves the score of the data quality score occurrence that you specify in the request URL. 

The following example shows the response of the GET request to retrieve the data quality score of a rule occurrence: 

```
{
    "summary": {
```

Get data quality scores       65

```
        "responseSize": 2,
        "totalSize": 3
    },
    "runs": [
        {
            "totalRows": 520,
            "scannedTime": "2026-01-11T05:04:59.955Z",
            "score": 84.23,
            "failedRows": 82,
            "exceptionFilePath": "/usr/tmp/ORACLE_SOURCE_DQ_REMEDIATION_1764183823573/
orcl21c/DQ_VOLUME/EMPLOYEES/
DQ_DQRO_CDGC_86089_02_1764183823573-2b9343761355447cbcfb904c4c083230.csv",
            "_links": [
                {
                    "rel": "get-rule-occurrence-details",
                    "url": "/data360/search/v1/assets/
d2edcc25-4243-40e0-913c-23d32b97aa8e?scheme=internal&segments=all",
                    "method": "GET"
                }
            ]
        },
        {
            "totalRows": 522,
            "scannedTime": "2026-01-18T02:07:13.834",
            "score": 85.63,
            "failedRows": 75,
            "exceptionFilePath": "/usr/tmp/ORACLE_SOURCE_DQ_REMEDIATION_1764183823573/
orcl21c/DQ_VOLUME/EMPLOYEES/
DQ_DQRO_CDGC_86089_02_1764653243567-7d2ec6ef10924de3b4c58f6c6011fae.csv",
            "_links": [
                {
                    "rel": "get-rule-occurrence-details",
                    "url": "/data360/search/v1/assets/
d2edcc25-4243-40e0-913c-23d32b97aa8e?scheme=internal&segments=all",
                    "method": "GET"
                }
            ]
        }
    ]
}
```

## Upload data quality scores

Upload one or more data quality scores to Data Governance and Catalog. When you upload a score, specify the data quality rule occurrence for which the scores apply. After you upload the scores, you can see the new scores in Data Governance and Catalog by opening the asset that is related to the rule occurrence you specified. 

**Note:** Effective in the April 2026 release, the usage of this API endpoint is deprecated. Informatica intends to drop support for this endpoint in a future release. 

Deprecated functionality is supported, but Informatica intends to drop support in a future release. Informatica requests that you transition to different functionality before the functionality is dropped. 

To upload data quality scores to data quality rule occurrences, Informatica recommends that you use the following endpoint: 

```
<baseApiUrl>/data-quality/v1/rule-occurrences/runs
```

For more information about the new endpoint, see “Import data quality scores” on page 62.

The following table provides the HTTP method and endpoint for the API call: 

**Method Endpoint** PATCH **<baseApiUrl>/ccgf-ruleautomation/api/v1/dataQuality/publishScore?refBy=INTERNAL** 

The `<baseApiUrl>` differs for each pod. The following table displays the base API URL for some PODs: 

|**POD Name**|**Base API URL**|
|---|---|
|AP SouthEast 1 (APSE1)|https://cdgc-api.dm-ap.informaticacloud.com/|
|Canada Central 1 (CAC1)|https://cdgc-api.dm-na.informaticacloud.com/|
|EM West 1 (EMW1)|https://cdgc-api.dm-em.informaticacloud.com/|
|NA West 1 (USW1)|https://cdgc-api.dm-us.informaticacloud.com/|
|UK (UK1)|https://cdgc-api.dm-uk.informaticacloud.com/|


If you update data quality scores through API, the updated data quality scores can take up to one hour to reflect in the **Total Runs Table** of the **Score** tab of a rule occurrence. 

### Request body

Use the request body to upload one or more data quality scores of an asset. 

In the body of request, specify the asset ID and the data quality scores in the following format: 

```
{
  "scores": [
    {
      "assetId": "e8b757ba-63ca-41a6-b79d-00c66fa176a0",
      "dqscore": {
        "facts": {
          "com.infa.ccgf.models.governance.value": 94,
          "com.infa.ccgf.models.governance.totalCount": 20000,
          "com.infa.ccgf.models.governance.exception": 764,
          "com.infa.ccgf.models.governance.scannedTime": "2022-02-09T10:10:12.441Z"
        }
      }
    }
  ]
}
```

Upload data quality scores       67

The following table describes the parameters that you can specify in the body of the request: 

**Parameter Description** `assetId ` Required. Internal reference identifier of the data quality rule occurrence for which you want to upload the scores. You can determine the identifier by looking at the URL in the browser window when you open the rule occurrence. The identifier is the value between the ` asset/` and `?` parameters. Consider the following example: - URL: `<domain>/asset/22ea8dd9-5128-496b-a51a-f3f0e6ed2b56? type=RuleInstance&name=Rule%20Instance%20July%2009%201214 ` - Identifier: ` 22ea8dd9-5128-496b-a51a-f3f0e6ed2b56 facts ` Required. Attribute of the data quality score. You must enter the following values: - Enter ` com.infa.ccgf.models.governance.value ` to specify the data quality score. - Enter ` com.infa.ccgf.models.governance.exception ` to specify an exception score. - Enter ` com.infa.ccgf.models.governance.scannedTime ` to specify the timestamp of the last rule run. - Enter ` com.infa.ccgf.models.governance.totalCount` to specify the total number of run counts. **Note:** When you upload data quality scores in Data Governance and Catalog using bulk upload, the scores are automatically rounded to two decimal places. If you set a score with more than two decimal points, the score is rounded to the nearest value and retains only two decimal places. 

### Example request

The following example shows a PATCH request to upload two data quality scores: 

```
PATCH https://cdgc-api.dm-us.informaticacloud.com/ccgf-ruleautomation/api/v1/dataQuality/
publishScore?refBy=INTERNAL
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
IDS-SESSION-ID: <sessionId value>
Body
{
  "scores": [
    {
      "assetId": "e8b757ba-63ca-41a6-b79d-00c66fa176a0",
      "dqscore": {
        "facts": {
          "com.infa.ccgf.models.governance.value": 94,
          "com.infa.ccgf.models.governance.totalCount": 20000,
          "com.infa.ccgf.models.governance.exception": 764,
          "com.infa.ccgf.models.governance.scannedTime": "2022-02-09T10:10:12.441Z"
        }
      }
    },
    {
      "assetId": "abc82672-63ca-41a6-b79d-00c66fa176a0",
      "dqscore": {
        "facts": {
          "com.infa.ccgf.models.governance.value": 100,
          "com.infa.ccgf.models.governance.totalCount": 100,
          "com.infa.ccgf.models.governance.exception": 0,
          "com.infa.ccgf.models.governance.scannedTime": "2022-02-09T10:10:12.441Z"
        }
      }
    }
  ]
}
```

# Chapter 6: Manage data access assets

Use data access asset APIs to automate, scale, and integrate the creation and management of data access assets. 

You can use data access asset APIs to perform the following operations: 

- Create, update, delete, and retrieve data access policies and rules. 

- Create, update, delete, and retrieve data protections. 

- Create, update, delete, and retrieve precedence tiers. 

- Publish data access assets. 

- Resynchronize all data access policies on your source system. 

The following rules and guidelines apply to data access asset APIs: 

- Before you make REST API calls, authenticate yourself with JWT authentication. For more information about JWT authentication, see Authentication. 

- The base API URL differs for each POD. 

For more information about the base API URL, see “Send requests” on page 9. 

- Prefix each endpoint, except for the Policy pushdown resync API, with /data360/dam/api/v1/ to route to the latest version of the API. 

For more information on data access assets, see _Data Access Management_ in the Data Governance and Catalog documentation. 

## Data access control API

Use the data access control API to create, update, delete, or retrieve data access control policies and data access control rules. 

Data access control policies grant groups of users read, write, or delete access to assets in a source system that you specify. Data Access Management pushes these policies into your cloud data platform. The data source enforces the data access control policy directly. 

For more information on data access control rules, see _Data Access Management_ in the Data Governance and Catalog documentation.

### Retrieve data access control assets

Send a GET request to retrieve the details of data access control policies and data access control rules. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Methods and endpoints

The following table describes retrieval methods and associated endpoints for the data access control API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|GET|**<baseApiUrl><prefix>/data-**<br>**access-controls**|Retrieve the list of all published data<br>access control policies.|
|GET|**<baseApiUrl><prefix>/data-**<br>**access-controls/{ID}**|Retrieve the draft or published<br>version of a specified data access<br>control policy.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`offset`|Integer|Optional. Specify the record number from which to begin returning<br>assets. The default value is zero.|
|`limit`|Integer|Optional. Specify the total number of records to return. The default<br>value is 500.|
|`fetchDraft`|Boolean|Optional. Retrieve the draft version of a specified data access<br>control policy. When using the**<baseApiUrl><prefix>/data-**<br>**access-controls/{ID}**endpoint, use` fetchDraft=true`. The<br>default value is` false`.<br>**Note:**To retrieve the draft version of all data access control<br>policies, see<br>“<br>Retrieveassetsinthecatalog<br>”<br>onpage<br>15.|


#### Request body

These endpoints have no request body. 

### Create data access control assets

Send a POST request to create data access control policies. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Method and endpoint

The following table the describes creation method and associated endpoint for the data access control API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|POST|**<baseApiUrl><prefix>/data-**|Create new data access control|
||**access-controls**|policies.|

#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|
|`enabled`|Boolean|Optional. Specify the status of the asset: true|false.|
|`effectiveDate`|String|Optional. Specify the start date of the asset in the format yyyy-mm-dd.|
|`endDate`|String|Optional. Specify the end date of the asset in the format yyyy-mm-dd.|
|`enforcementMethod `|String|Optional. Specify the data access policy enforcement method:<br>` PUSHDOWN`|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|
|`dataAccessRules `|Object|Optional. Define one or more data access control rules.<br>` ruleName `: String. Name of the rule.<br>` ruleDescription `: String. Description of the rule.<br>` enabled `: Boolean. Status of the rule.<br>` rank `: Integer. Ranking of the rule within the policy.<br>` condition `: Object. Set of conditions for the rule.<br>` accessControls`: Object. Asset query and permissions that the<br>rule grants.|


#### Request body

In the body of the request, specify the parameters in the following format: 

```
{

  "name": "Data Access Control Policy",

  "description": "General Access Restriction Policy.",
```

> `"externalId": "Data Access Control Policy",` 

```
  "effectiveDate": "2000-01-01",
```

Data access control API       71

```
  "enabled": false,
   "dataAccessRules": [
        {
            "ruleName": "Data Access Control Rule",
            "ruleDescription": "General access restrictions.",
            "enabled": true,
            "rank": 0,
        "condition": {
            "dialect": "structured-predicate",
            "version": "1.0",
            "predicate": {
                "or": [
                {
                    "isAnyOf": [
                {
                  "path": "context::principal.groups"
                },
                {
                  "constant": {
                    "__type": "group_identifier",
                    "type": "PLAIN",
                    "id": "USERS"
                  }}]}]}},
       "accessControls": [
        {
          "accessTypes": [
            "READ"
          ],
          "resourceLocatorQuery": {
            "dialect": "knowledge-graph-search",
            "query": {
              "query": "tables related to business term '*Customer'",
              "filter": [
                "{\"bool\":{\"filter\":[{\"terms\":{\"core.classType\":
[\"com.infa.odin.models.relational.Table\"]}},{\"terms\":{\"core.resourceType\":
[\"Databricks Notebooks\"]}},{\"terms\":{\"core.assetLifecycle\":[\"Published\"]}}]}}"

              ]
            },
            "version": "1.0"
          }}]}]
}
```

### Modify data access control assets

Send a PUT request to replace a data access control policy. Send a PATCH request to modify the details of a data access control policy. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Methods and endpoints

The following table describes modification methods and associated endpoints for the data access control API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|PUT|**<baseApiUrl><prefix>/data-**<br>**access-controls/{ID}**|Replace a specified data access<br>control policy.|
|PATCH|**<baseApiUrl><prefix>/data-**<br>**access-controls/{ID}**|Modify the details of a specified data<br>access control policy.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|
|`enabled`|Boolean|Optional. Specify the status of the asset: true|false.|
|`effectiveDate`|String|Optional. Specify the start date of the asset in the format yyyy-mm-dd.|
|`endDate`|String|Optional. Specify the end date of the asset in the format yyyy-mm-dd.|


Data access control API       73

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|
|`dataAccessRules `|Object|Optional. Define one or more data access control rules.<br>` ruleName `: String. Name of the rule.<br>` ruleDescription `: String. Description of the rule.<br>` rank `: Integer. Ranking of the rule within the policy.<br>` condition `: Object. Set of conditions for the rule.<br>` accessControls`: Object. Asset query and permissions that the<br>rule grants.|


#### Request body

In the body of the request, specify the parameters in the following format: 

```
{
```

- `"description": "General Access Restriction, updated."` 

- `}` 

This example request body uses the PATCH method to modify only the policy's description. 

### Delete data access control assets

Send a DELETE request to delete a specific data access control policy. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the deletion method and associated endpoint for the data access control API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|DELETE|**<baseApiUrl><prefix>/data-**|Delete a specified data access|
||**access-controls/{ID}**|control policy.|


#### Request body

This endpoint has no request body.

## Data de-identification API

Use the data de-identification API to create, update, delete, or retrieve data de-identification policies and data de-identification rules. 

Data de-identification policies transform data in ways that you specify to de-identify sensitive data. Data deidentification policies can tokenize data to maintain the original format, generalize dates to a common year or month, or truncate data to keep only minimal contents visible. These de-identifications protect the data and make the data useful for analysis. 

For more information on data de-identification rules, see _Data Access Management_ in the Data Governance and Catalog documentation. 

### Retrieve data de-identification assets

Send a GET request to retrieve the details of data de-identification policies and data de-identification rules. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Methods and endpoints

The following table describes retrieval methods and associated endpoints for the data de-identification API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|GET|**<baseApiUrl><prefix>/data-**<br>**deidentifications**|Retrieve the list of all published data<br>de-identification policies.|
|GET|**<baseApiUrl><prefix>/data-**<br>**deidentifications/{ID}**|Retrieve the draft or published<br>version of a specified data de-identification policy.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`offset`|Integer|Optional. Specify the record number from which to begin returning<br>assets. The default value is zero.|
|`limit`|Integer|Optional. Specify the total number of records to return. The default<br>value is 500.|
|`fetchDraft`|Boolean|Optional. Retrieve the draft version of a specified data de-identification policy. When using the**<baseApiUrl><prefix>/data-**<br>**deidentifications/{ID}**endpoint, use` fetchDraft=true`. The<br>default value is` false`.<br>**Note:**To retrieve the draft version of all data de-identification<br>policies, see<br>“<br>Retrieveassetsinthecatalog<br>”<br>onpage<br>15.|


Data de-identification API       75

### Create data de-identification assets

Send a POST request to create data de-identification policies. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the creation method and associated endpoint for the data de-identification API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|POST|**<baseApiUrl><prefix>/data-**|Create new data de-identification|
||**deidentifications**|policies.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|
|`enabled`|Boolean|Optional. Specify the status of the asset: true|false.|
|`effectiveDate`|String|Optional. Specify the start date of the asset in the format yyyy-mm-dd.|
|`endDate`|String|Optional. Specify the end date of the asset in the format yyyy-mm-dd.|
|`precedenceTier`|Reference|Required. Specify the internal ID of the precedence tier in which to<br>create the policy.|

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|
|`dataDeIdentificatio `<br>` nRules `|Object|Optional. Define one or more data de-identification rules.<br>` ruleName `: String. Name of the rule.<br>` ruleDescription `: String. Description of the rule.<br>` enabled `: Boolean. Status of the rule.<br>` rank `: Integer. Ranking of the rule within the policy.<br>` condition `: Object. Set of conditions for the rule.<br>` fieldLevelProtections `: Object. The list of data protections to<br>apply to a data element classification.<br>` cellLevelProtections`: Object. The cell-level condition and list<br>of protections to apply to a data element classification.|


#### Request body

In the body of the request, specify the parameters in the following format: 

```
{
"name": "Data Privacy Protection Policy",
"description": "General usage data privacy policy.",
"externalId": "Data Privacy Protection Policy",
"effectiveDate": "2000-01-01",
"enabled": true,
"precedenceTier": "95b3021d-3945-4de5-bc7e-30cc3c94cbb3",
"dataDeIdentificationRules": [
{
"ruleName": "Data Privacy Protection Rule",
"ruleDescription": "General data privacy enforcements.",
"enabled": true,
"rank": 1,
"condition": { "dialect": "structured-predicate",
"version": "1.0",
"predicate": { "and": [ { "isAnyOf": [ {
```

Data de-identification API       77

```
"path": "context::request.usage"
}, { "constant": "{{Data-Access-Management}}" } ] }
] } },
"fieldLevelProtections": [ {
"dataProtectionInternalId": "",
"fieldLocatorQuery": { "dialect": "basic-object-locator",
"query": {
"path": "cdgc::dataelement.classifications[*].id",
"value": "{{DAM - Customer Number}}"
}, "version": "1.0" } } ],
"cellLevelProtections": [ { "fieldValuePredicate": {
"dialect": "structured-predicate",
"version": "1.0",
"predicate": { "or": [ { "isNotNull": [ {
"fieldValue": { "fieldLocatorQuery": {"dialect": "basic-object-locator", "version":
"1.0", "query": {
"path": "cdgc::dataelement.classifications[*].id",
"value": "{{DAM - Customer Status}}" } }, "type": "STRING"
} } ] } ] } }, "fieldProtections": [ {
"dataProtectionInternalId": "",
"fieldLocatorQuery": { "dialect": "basic-object-locator", "query": {"path":
"cdgc::dataelement.classifications[*].id", "value": "{{DAM - Customer Status}}" },
"version": "1.0" }} ] }
} ] }
]
}
```

### Modify data de-identification assets

Send a PUT request to replace a data de-identification policy. Send a PATCH request to modify the details of a specific data de-identification policy. The prefix, indicated by `<prefix>` , for each endpoint is `/ data360/dam/api/v1/` . 

#### Methods and endpoints

The following table describes modification methods and associated endpoints for the data de-identification API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|PUT|**<baseApiUrl><prefix>/data-**<br>**deidentifications/{ID}**|Replace a specified data de-identification policy.|
|PATCH|**<baseApiUrl><prefix>/data-**<br>**deidentifications/{ID}**|Modify the details of a specified data<br>de-identification policy.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|
|`enabled`|Boolean|Optional. Specify the status of the asset: true|false.|
|`effectiveDate`|String|Optional. Specify the start date of the asset in the format yyyy-mm-dd.|
|`endDate`|String|Optional. Specify the end date of the asset in the format yyyy-mm-dd.|
|`precedenceTier`|Reference|Required. Specify the internal ID of the precedence tier in which to<br>create the policy.|


Data de-identification API       79

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|
|`dataDeIdentificatio `<br>` nRules `|Object|Optional. Define one or more data de-identification rules.<br>` ruleName `: String. Name of the rule.<br>` ruleDescription `: String. Description of the rule.<br>` enabled `: Boolean. Status of the rule.<br>` rank `: Integer. Ranking of the rule within the policy.<br>` condition `: Object. Set of conditions for the rule.<br>` fieldLevelProtections `: Object. The list of data protections to<br>apply to a data element classification.<br>` cellLevelProtections`: Object. The cell-level condition and list<br>of protections to apply to a data element classification.|


#### Request body

In the body of the request, specify the parameters in the following format: 

```
{
"description": "General usage data privacy policy. [Updated]",
"precedenceTier": "95b3021d-3945-4de5-bc7e-30cc3c94cbb3"
}
```

- `"description": "General usage data privacy policy. [Updated]",` 

This example request body uses the PATCH method to modify only the policy's description and precedence tier. 

### Delete data de-identification assets

Send a DELETE request to delete a data de-identification policy. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the deletion method and associated endpoint for the data de-identification API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|DELETE|**<baseApiUrl><prefix>/data-**|Delete a specified data de-|
||**deidentifications/{ID}**|identification policy.|

#### Request body

This endpoint has no request body. 

## Data filter API

Use the data filter API to create, update, delete, or retrieve data filter policies and data filter rules. 

Data filter policies restrict or limit the records that Data Access Management delivers to users. 

For more information on data filter rules, see _Data Access Management_ in the Data Governance and Catalog documentation. 

### Retrieve data filter assets

Send a GET request to retrieve the details of data filter policies and data filter rules. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Methods and endpoints

The following table describes retrieval methods and associated endpoints for the data filter API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|GET|**<baseApiUrl><prefix>/data-**<br>**filters**|Retrieve the list of all published data<br>filter policies.|
|GET|**<baseApiUrl><prefix>/data-**<br>**filters/{ID}**|Retrieve the draft or published<br>version of a specified data filter<br>policy.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`offset`|Integer|Optional. Specify the record number from which to begin returning<br>assets. The default value is zero.|
|`limit`|Integer|Optional. Specify the total number of records to return. The default<br>value is 500.|
|`fetchDraft`|Boolean|Optional. Retrieve the draft version of a specified data filter policy.<br>When using the**<baseApiUrl><prefix>/data-filters/{ID}**<br>endpoint, use` fetchDraft=true`. The default value is` false`.<br>**Note:**To retrieve the draft version of all data filter policies, see<br>“<br>Retrieveassetsinthecatalog<br>”<br>onpage<br>15.|


Data filter API       81

### Create data filter assets

Send a POST request to create data filter policies. The prefix, indicated by `<prefix>` , for each endpoint is `/ data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the creation method and associated endpoint for the data filter API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|POST|**<baseApiUrl><prefix>/data-**<br>**filters**|Create new data filter policies.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|
|`enabled`|Boolean|Optional. Specify the status of the asset: true|false.|
|`effectiveDate`|String|Optional. Specify the start date of the asset in the format yyyy-mm-dd.|
|`endDate`|String|Optional. Specify the end date of the asset in the format yyyy-mm-dd.|
|`enforcementMethod`|String|Optional. Specify the enforcement method as either` PUSHDOWN ` or<br>` ON_QUERY`. The default method is` ON_QUERY`.|

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|
|`dataFilterRules `|Object|Optional. Define one or more data filter rules.<br>` ruleName `: String. Name of the rule.<br>` ruleDescription `: String. Description of the rule.<br>` enabled `: Boolean. Status of the rule.<br>` rank `: Integer. Ranking of the rule within the policy.<br>` condition `: Object. Set of conditions for the rule.<br>` queries`: Object. The asset query used when you set the<br>enforcement method to` PUSHDOWN `.<br>` filter`: Object. The filter used to deny access to data.|


Request body for on-query enforcement 

In the body of the request, specify the parameters in the following format: 

```
{
"name": "Third-Party Access Control Policy",
"description": "Restrict access for third-party.",
"enabled": true,
"dataFilterRules": [
{    "ruleName": "Access Control Rule",
"ruleDescription": "",
"enabled": true,
"rank": 0,
"condition": {
"dialect": "structured-predicate",
"version": "1.0",
"predicate": {
"and": [
{"isAnyOf": [
{
```

Data filter API       83

```
"path": "context::request.usage"
}, {
"constant": "{{Data-Access-Management}}"
}]
}, {
"isAnyOf": [
{
"path": "cdgc::dataset.glossaries[*].id"
}, {
"constant": "{{DAM - Account}}"
}] }] }},
"filter": {
"dialect": "structured-predicate",
"predicate": {
"or": [
{
"isAnyOf": [
{
"fieldValue": {
"fieldLocatorQuery": {
"dialect": "basic-object-locator",
"version": "1.0",
"query": {
"path": "cdgc::dataelement.classifications[*].id",
"value": "{{DAM - Customer Status}}"
} },
"type": "STRING"
} },
{
"constant": "1"
}] }] },
"version": "1.0"
} } ]
}
```

Request body for pushdown enforcement 

In the body of the request, specify the parameters in the following format: 

```
{
"name": "Data Filter Pushdown Policy",
"description": "Enforce row-level security natively through a data filter policy.",
"enabled": false,
"enforcementMethod": ["PUSHDOWN"],
"dataFilterRules": [
{ "ruleName": "Data Filter Pushdown Rule",
"rank": 0,
"ruleDescription": "",
"enabled": true,
"condition": {
"dialect": "structured-predicate",
"version": "1.0",
"predicate": {
"or": [{
"isAnyOf": [
{
"path": "context::principal.groups"
}, { "constant": {
"__type": "group_identifier",
"type": "PLAIN", "id": "USER_GROUP"
} } ] } ] },
"queries": [
{
"dialect": "knowledge-graph-search", "version": "1.0",
"query": {"query": "tables related to business term '*Customer'",
"filter": [ "{\"bool\":{\"filter\":[{\"terms\":{\"core.classType\":
[\"com.infa.odin.models.relational.Table\"]}},{\"terms\":{\"core.resourceType\":
[\"Databricks Notebooks\"]}},{\"terms\":{\"core.assetLifecycle\":[\"Published\"]}}]}}"
] } } ] },
"filter": {
"dialect": "structured-predicate",
"predicate": {
"or": [{
"isNotAnyOf": [
```

Data filter API       85

```
{
"fieldValue": {
"fieldLocatorQuery": {
"dialect": "basic-object-locator",
"query": {
"path": "cdgc::dataelement.classifications[*].id",
"value": "{{DAM - Person Country}}"
},
"version": "1.0" },
"type": "STRING"} },
{"constant": "USA"}, {"constant": "CANADA"}
] } ] },
"version": "1.0"
} }]
}
```

### Modify data filter assets

Send a PUT request to replace a specific data filter policy. Send a PATCH request to modify the details of a specific data filter policy. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Methods and endpoints

The following table describes modification methods and associated endpoints for the data filter API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|PUT|**<baseApiUrl><prefix>/data-**<br>**filters/{ID}**|Replace a specified data filter policy.|
|PATCH|**<baseApiUrl><prefix>/data-**<br>**filters/{ID}**|Modify the details of a specified data<br>filter policy.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`enabled`|Boolean|Optional. Specify the status of the asset: true|false.|
|`effectiveDate`|String|Optional. Specify the start date of the asset in the format yyyy-mm-dd.|
|`endDate`|String|Optional. Specify the end date of the asset in the format yyyy-mm-dd.|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|
|`dataFilterRules `|Object|Optional. Define one or more data filter rules.<br>` ruleName `: String. Name of the rule.<br>` ruleDescription `: String. Description of the rule.<br>` enabled `: Boolean. Status of the rule.<br>` rank `: Integer. Ranking of the rule within the policy.<br>` condition `: Object. Set of conditions for the rule.<br>` queries`: Object. The asset query used when you set the<br>enforcement method to` PUSHDOWN `.<br>` filter`: Object. The filter used to deny access to data.|


#### Request body

In the body of the request, specify the parameters in the following format: 

```
{

"description": "Enforce row level security natively, updated.",

"enabled": true

}
```

This example request body uses the PATCH method to modify only the policy's description and status. 

Data filter API       87

### Delete data filter assets

Send a DELETE request to delete a specific data filter policy. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the deletion method and associated endpoint for the data filter API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|DELETE|**<baseApiUrl><prefix>/data-**<br>**filters/{ID}**|Delete a specified data filter policy.|


#### Request body

This endpoint has no request body. 

## Data protection API

Use the data protection API to create, update, delete, or retrieve data protections. 

Data protections use data de-identification techniques to protect data while maintaining data consistency, reversibility, and format. 

For more information on data protections, see _Data Access Management_ in the Data Governance and Catalog documentation. 

### Retrieve data protection assets

Send a GET request to retrieve the details of data protections. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Methods and endpoints

The following table describes retrieval methods and associated endpoints for the data protection API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|GET|**<baseApiUrl><prefix>/data-**<br>**protections**|Retrieve the list of all published data<br>protections.|
|GET|**<baseApiUrl><prefix>/data-**<br>**protections/{ID}**|Retrieve the draft or published<br>version of a specified data<br>protection.|

#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`offset`|Integer|Optional. Specify the record number from which to begin returning<br>assets. The default value is zero.|
|`limit`|Integer|Optional. Specify the total number of records to return. The default<br>value is 500.|
|`fetchDraft`|Boolean|Optional. Retrieve the draft version of a specified data protection.<br>When using the**<baseApiUrl><prefix>/data-protections/{ID}**<br>endpoint, use` fetchDraft=true`. The default value is` false`.<br>**Note:**To retrieve the draft version of all data protections, see<br>“<br>Retrieveassetsinthecatalog<br>”<br>onpage<br>15.|


### Create data protection assets

Send a POST request to create data protections. The prefix, indicated by `<prefix>` , for each endpoint is `/ data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the creation method and associated endpoint for the data protection API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|POST|**<baseApiUrl><prefix>/data-**<br>**protections**|Create new data protections.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|


Data protection API       89

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|
|`dataProtectionTechn `<br>` iques `|Object|Required. Specify the data de-identification technique.<br>` dataProtectionTechniqueType `: String. The data de-identification technique.<br>` appliesToDataType `: String. The data type for the selected data<br>de-identification technique.<br>` parameters`: String. Additional required parameters for the<br>selected data de-identification technique.|


The following table lists the supported data types and the required parameters for each data de-identification technique: 

|**Data De-identification**<br>**Technique**|**Supported Data**<br>**Types**|**Required Parameters**|
|---|---|---|
|`CONSTANT_TEXT_VALUE `<br>` _FUNCTION`|- STRING<br>- INTEGER<br>- DECIMAL|`"value": "****"`|
|`GENERALIZE_DATE_FUN `<br>` CTION`|- DATE<br>- TIMESTAMP|`"preserve_date_type": "YEAR|MONTH"`|
|`HASH_FUNCTION`|STRING|`"hash_algorithm": "<HASHING_FUNCTION>"`<br>`"compatibility_mode": true|false`|
|`NUMERIC_TOKENISE_FU `<br>` NCTION`|INTEGER|`"regex": ""`<br>`"consistent": true|false`|
|`REDACT_WITH_NULL_FU `<br>` NCTION`|- UNKNOWN<br>- STRING<br>- INTEGER<br>- DECIMAL<br>- DATE<br>- TIMESTAMP|none|
|`RETAIN_FUNCTION`|- UNKNOWN<br>- STRING<br>- INTEGER<br>- DECIMAL<br>- DATE<br>- TIMESTAMP|none|

|**Data De-identification**<br>**Technique**|**Supported Data**<br>**Types**|**Required Parameters**|
|---|---|---|
|`SUBSTITUTE_FUNCTION`|STRING|`"filename": "<FILENAME>"`<br>`"consistent": true|false`|
|`TOKENISE_FUNCTION`|STRING|`"regex": ""`|
|||`"consistent": true|false`|
|`TRUNCATE_FUNCTION`|STRING|`"length": "",` `"behaviour":`<br>`"PRESERVE_FIRST_FEW_CHARACTERS"`|


#### Request body

In the body of the request, specify the parameters in the following format: 

```
{
"name": "Protection: Preserve 4 Characters",
"externalId": "Protection: Preserve 4 Characters",
"description": "",
"dataProtectionTechniques": [
{
"dataProtectionTechniqueType": "REDACT_WITH_NULL_FUNCTION",
"appliesToDataType": "UNKNOWN",
"parameters": null
},
{
"dataProtectionTechniqueType": "TRUNCATE_FUNCTION",
"appliesToDataType": "STRING",
"parameters": {
"length": "4",
"behaviour": "PRESERVE_FIRST_FEW_CHARACTERS"
}
},
{
"dataProtectionTechniqueType": "REDACT_WITH_NULL_FUNCTION",
"appliesToDataType": "INTEGER",
"parameters": null
},
{
"dataProtectionTechniqueType": "REDACT_WITH_NULL_FUNCTION",
```

Data protection API       91

```
"appliesToDataType": "DECIMAL",
"parameters": null
},
{
"dataProtectionTechniqueType": "GENERALIZE_DATE_FUNCTION",
"appliesToDataType": "DATE",
"parameters": {"preserve_date_type": "YEAR"}
}, {
"dataProtectionTechniqueType": "REDACT_WITH_NULL_FUNCTION",
"appliesToDataType": "TIMESTAMP",
"parameters": null
}
]
}
```

### Modify data protection assets

Send a PUT request to replace a specific data protection. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the modification method and associated endpoint for the data protection API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|PUT|**<baseApiUrl><prefix>/data-**<br>**protections/{ID}**|Replace a specified data protection.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|
|`dataProtectionTechn `<br>` iques `|Object|Required. Specify the data de-identification technique.<br>` dataProtectionTechniqueType `: String. The data de-identification technique.<br>` appliesToDataType `: String. The data type for the selected data<br>de-identification technique.<br>` parameters`: String. Additional required parameters for the<br>selected data de-identification technique.|


#### Request body

In the body of the request, specify the parameters in the following format: 

```
{
"name": "Protection: Retain [Updated]",
"externalId": "Protection: Retain",
"description": "Protection function retain [Updated]",
"dataProtectionTechniques": [
{
"dataProtectionTechniqueType": "RETAIN_FUNCTION",
"appliesToDataType": "UNKNOWN",
"parameters": null
},
{
"dataProtectionTechniqueType": "RETAIN_FUNCTION",
"appliesToDataType": "STRING",
"parameters": null
}
]
}
```

Data protection API       93

### Delete data protection assets

Send a DELETE request to delete a specific data protection. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the deletion method and associated endpoint for the data protection API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|DELETE|**<baseApiUrl><prefix>/data-**<br>**protections/{ID}**|Delete a specified data protection.|


#### Request body

This endpoint has no request body. 

## Policy pushdown resync API

Use the policy pushdown resync API to resynchronize all data access policies associated with an asset connection that you specify on your source system. Use this API when data access policies that you push down don't return expected results. 

To run this API, you need the Governance Administrator role in Metadata Command Center role. 

The policy pushdown resync API works with Databricks and Microsoft Fabric Data Lakehouse. 

#### How this API ensures secure resynchronization

The policy pushdown resync API stops processing or pushing down data access policies to your source system. This API is useful when data access policies enter an undefined state and Data Governance and Catalog continually attempts to enforce the policies. 

The policy pushdown resync API keeps your data safe while it resynchronizes data access control policies. It revokes permissions from the groups and reapplies them when the resynchronization is complete. 

The policy pushdown resync API resynchronizes data filter policies without exposing data. 

The API works in the following ways for each source system: 

- For Databricks, the policy pushdown resync API replaces the row filter function. If the row filter function is already applied to the data, then it is still applied when Data Governance and Catalog replaces the function. There is no point when the table is without a row filter function. 

- For Microsoft Fabric Data Lakehouse, the policy pushdown resync API creates a new security function and attaches it as a filter predicate on the target table. If the correct data filter policy already exists, Data Governance and Catalog atomically updates the predicate in place. If a different data filter policy is attached, Data Governance and Catalog detaches the old one and then creates the data filter policy. Data Governance and Catalog handles concurrent creation conflicts by updating the existing filter predicate of the data filter policy. 

For more information on pushing down data access policies, see _Data Access Management_ in the Data Governance and Catalog documentation.

### Policy pushdown resync API endpoints

Send a POST request to resynchronize all data access policies on your source system. Submit a request with the appropriate method and endpoint. 

#### Method and endpoint

The following table describes the method and associated endpoint for the policy pushdown resync API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|POST|**<baseApiUrl>/cdam-**<br>**runtime/api/v1/policy-**<br>**pushdown/resync?**<br>**connectionId=<connectionId>**|Resynchronizes the data access<br>policies for the associated assets of<br>a connection that you specify.<br>For more information on retrieving<br>the connection ID, see_REST API_<br>_Reference_in the Administrator<br>documentation.|


#### Request parameters

The following table describes the parameter that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`connectionId`|String|Required. Internal ID of the connection.<br>If successful, Data Governance and Catalog returns a 200 response<br>with the following message:|
|||`"message": "Actions triggered for connection:`<br>`00000000000000000000"`|


#### Request body

This endpoint has no request body. 

## Precedence tier API

Use the precedence tier API to create, update, delete, or retrieve precedence tiers. 

Precedence tiers are ordered groupings of data de-identification policies that determine the priority and sequence of data de-identification policies and data protections. You assign data de-identification policies to a precedence tier. 

For more information on precedence tiers, see _Data Access Management_ in the Data Governance and Catalog documentation. 

Precedence tier API       95

### Retrieve precedence tier assets

Send a GET request to retrieve the details of precedence tiers. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Methods and endpoints

The following table describes retrieval methods and associated endpoints for the precedence tier API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|GET|**<baseApiUrl><prefix>/**<br>**precedence-tiers**|Retrieve the list of all published<br>precedence tiers.|
|GET|**<baseApiUrl><prefix>/**<br>**precedence-tiers/{ID}**|Retrieve the draft or published<br>version of a specified precedence<br>tier.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`offset`|Integer|Optional. Specify the record number from which to begin returning<br>assets. The default value is zero.|
|`limit`|Integer|Optional. Specify the total number of records to return. The default<br>value is 500.|
|`fetchDraft`|Boolean|Optional. Retrieve the draft version of a specified precedence tier.<br>When using the**<baseApiUrl><prefix>/precedence-tiers/{ID}**<br>endpoint, use` fetchDraft=true`. The default value is` false`.<br>**Note:**To retrieve the draft version of all precedence tiers, see<br>“<br>Retrieveassetsinthecatalog<br>”<br>onpage<br>15.|


### Create precedence tier assets

Send a POST request to create precedence tiers. The prefix, indicated by `<prefix>` , for each endpoint is `/ data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the creation method and associated endpoint for the precedence tier API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|POST|**<baseApiUrl><prefix>/**<br>**precedence-tiers**|Create new precedence tiers.|

#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|
|`rank`|Integer|Required. Specify the rank of the asset. An error occurs if an asset<br>exists with the same rank.|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|


#### Request body

In the body of the request, specify the parameters in the following format: 

```
{
"name": "Precedence Tier Unit 1",
"externalId": "Precedence Tier Unit 1",
"description": "Precedence Tier for policies in unit #1",
"rank":    5000,
"stakeholders": [
{
"stakeholderId":"9p5CFWeemQXfj5IOg2C6Io",
"roleId": "9SKimMiBWHvendjzxdP6iv",
"stakeholderType": "USER"
}
]
}
```

Precedence tier API       97

### Modify precedence tier assets

Send a PUT request to replace a specific precedence tier. Send a PATCH request to modify the details of a specific precedence tier. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Methods and endpoints

The following table describes modification methods and associated endpoints for the precedence tier API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|PUT|**<baseApiUrl><prefix>/**<br>**precedence-tiers/{ID}**|Replace a specified precedence tier.|
|PATCH|**<baseApiUrl><prefix>/**<br>**precedence-tiers/{ID}**|Modify the details of a specified<br>precedence tier.|


#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`name`|String|Required. Specify the name of the asset.|
|`externalId`|String|Optional. Specify the external identifier of the asset.|
|`description`|String|Optional. Specify the description of the asset.|
|`rank`|Integer|Required. Specify the rank of the asset. An error occurs if an asset<br>exists with the same rank.|
|`stakeholders `|Object|Optional. Specify the following attributes to add a stakeholder to<br>the asset.<br>` stakeholderId `: The internal ID of the stakeholder. You can find<br>this ID by looking at the URL of the stakeholder page in Data<br>Governance and Catalog.<br>` roleId `: The internal ID of the role for the stakeholder. You can find<br>this ID in the role details in Administrator. Navigate to the**User**<br>**Roles**page, open the user role, and copy the ID at the end of the<br>URL.<br>` stakeholderType`: Specify whether the stakeholder is a` USER ` or<br>` GROUP`.|


#### Request body for the PATCH method

In the body of the request, specify the parameters in the following format: 

```
{

"description": "Precedence Tier, updated description",

"rank": 5001

}
```

This example request body uses the PATCH method to modify only the precedence tier's description and rank.

### Delete precedence tier assets

Send a DELETE request to delete a precedence tier. The prefix, indicated by `<prefix>` , for each endpoint is `/ data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the deletion method and associated endpoint for the precedence tier API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|DELETE|**<baseApiUrl><prefix>/**<br>**precedence-tiers/{ID}**|Delete a specified precedence tier.|


#### Request body

This endpoint has no request body. 

## Publish API

Use the publish API to submit assets for approval if your organization has enabled workflow for managing approvals of data access control assets. 

Assign one or more stakeholders to the asset before submitting it for approval. The submission creates a workflow ticket and assigns the tasks configured for the workflow to the stakeholders of the asset. The asset stakeholder approves, rejects, or reassigns the tasks. 

If your organization has not enabled workflow in Metadata Command Center, the publish API returns an error. 

For more information about enabling workflow, see _Administration_ in the Metadata Command Center documentation. 

### Publish API endpoints

Send a request to submit a draft data access asset for workflow review. The prefix, indicated by `<prefix>` , for each endpoint is `/data360/dam/api/v1/` . 

#### Method and endpoint

The following table describes the method and associated endpoint for the publish API: 

|**Method**|**Endpoint**|**Description**|
|---|---|---|
|POST|**<baseApiUrl>/**|Create a workflow ticket and submit|
||**data360/dam/api/v1/publish**|a specific asset for review.|


The request body includes details needed to create the workflow ticket. 

Publish API       99

#### Request parameters

The following table describes the parameters that you can specify in the endpoint of the request: 

|**Parameter**|**Type**|**Description**|
|---|---|---|
|`id`|String|Required. Specify the internal ID of the asset that you want to<br>publish.|
|`classType`|String|Required. Specify one of the following class types:<br>- DATA_ACCESS_CONTROL<br>- DATA_DE_IDENTIFICATION<br>- DATA_FILTER<br>- DATA_PROTECTION<br>- PRECEDENCE_TIER|
|`ticketDetails `|Object|Required. Specify the details of the workflow ticket to create when<br>publishing the asset.<br>` ticketTitle `: String. Title of the ticket.<br>` ticketDescription `: String. Description of the ticket.<br>` severity `: Reference. Severity of the ticket. Select one of the<br>following:<br>-<br>` HIGH `<br>-<br>` MEDIUM `<br>-<br>` LOW `<br>` urgency `: Reference. Urgency of the ticket. Select one of the<br>following:<br>-<br>` BLOCKER `<br>-<br>` MAJOR `<br>-<br>` MINOR`|


#### Request body

In the body of the request, specify the parameters in the following format: 

```
{
"id": "",
"classType": "PRECEDENCE_TIER",
"ticketDetails": {
"ticketTitle": "Send for Approval",
"ticketDescription": "Please approve this change.",
"severity": "LOW",
"urgency": "MINOR"
}
}
```

# Chapter 7: Export assets

Use this API to search for business or technical assets in the catalog and export the searched assets to a Microsoft Excel or a CSV file. 

To export assets using the export API, ensure that your organization administrator has enabled the **Export** privilege for your user role in Administrator. 

Use the export API in one of the following ways: 

- Search for assets by using queries and export the specified assets 

- Search for assets by asset ID and export the specified assets 

The following image shows the overall process of using the export API and the expected response for the endpoint: 


<!-- Start of picture text -->
Obtain the POST<br>essonIDthe ung If you knowthe asset ID GET<br>LoginAl Generatea JW token<br>Using the JWT Token Bulk export assets using search<br>API queries withthe<br>aeper) —<— data360/search/export/v1/assetsendpoint Bulkdata6osearchexportvexport assets usingassetassestsIDs withthe<br>el cookie! USER SESSION=(s endpoint<br>ord 10S-SESSION-0Response:wt tokenToken) (seston) Requesteader:‘Authorization:XINFA-ORG:D orgsBarer Token ‘Autorization:RequestHeader:Bearer Token<br>aan: XINFA-ORGID:i old<br>summaryviews), Nsowedgecuery segments etiam, Parameters:segments<br>Request¢ Body: Request, Body: filename, summaryvews, scheme)<br>(pages) i<br>k {Ustofssetisto}  export<br>Response:<br>0 Response:<br>+ Job10-uniquelDof<br>Tracking the: {<br>+ ’thejobOutput unt-uRiURI) 1|OutputD TrackingJab -sniquelbott URI-uRlURI  ofthe1<br><!-- End of picture text -->

## Prerequisites

Before you export the results of a search, verify the following prerequisites. 

You can export assets from the catalog only if your organization administrator grants you the following privileges and permissions: 

- The **Export** privilege for your user role in Administrator. 

- The **Read** permission on the assets that you export granted to you through access policies in Metadata Command Center 

For more information about the privileges and permissions, see the _Asset Management_ help. 

## Export assets using search queries

To search for assets in the catalog using search queries and export the assets to a Microsoft Excel or a CSV file, send a POST request. Use the request URL parameters and the request body to enter your search queries and specify the details of the assets you want to export. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|POST|**<baseApiUrl>/data360/search/export/v1/assets**|


The base API URL differs for each pod. For more information, see “Send requests” on page 9.

### Request parameters

To limit the scope of the exported assets, enter search queries using the request URL parameters and filter the search results. 

The following table describes the parameters that you can specify in the request URL: 

|**Parameter**|**Description**|
|---|---|
|`knowledgeQuery`|Specify the asset type or name along with any search criteria. For<br>information about search query examples, see<br>“<br>Searchqueryexamples<br>”<br>onpage<br>117.<br>For example, you can enter` knowledgeQuery=Business Terms ` and<br>` knowledgeQuery=data elements related to business term`.|
|`segments `|Specify the level of asset detail that you want the API request to return.<br>Enter any segment value from the following values:<br>-<br>` all `. Returns the summary, system attributes, and all details of the<br>asset and its child assets.<br>-<br>` summary `. Returns the name, description, and location of the asset.<br>-<br>` systemAttributes `. Returns the origin, class type of the asset,<br>base types, user that created or updated the asset, and the date on<br>which the asset was created or updated.<br>-<br>` customAttributes `. Returns the custom fields specified for the<br>asset.<br>-<br>` selfAttributes `. Returns the properties of assets.<br>-<br>` stakeholdership `. Returns the type of user and role that are<br>assigned as stakeholder for the asset.<br>-<br>` dataProfile `. Returns the profiling statistics of the asset.<br>-<br>` dataClassification `. Returns the data classification associated<br>with the asset.<br>-<br>` glossary `. Returns the glossary associated with the asset.<br>-<br>` dataQuality `. Returns the data quality score of the asset.<br>-<br>` hierarchy`. Returns the hierarchy of the asset.<br>The default value is` segments=all`.|
|`fileName`|Specify the name of the Microsoft Excel output file that you want to<br>export. The file name should not start with dot (.) or underscore (_).|
|`fileType`|Specify the file type of the output file. Enter**CSV**or**EXCEL**.|
|`summaryViews `|If you set this parameter, the exported Microsoft Excel file includes a<br>summary sheet. Specify the type of assets you want to see in the<br>summary sheet. You can specify one of the following values:<br>-<br>` all `. The summary sheet in the exported file contains a summary of<br>all the assets you have exported.<br>-<br>` Technical `. The summary sheet in the exported file contains a<br>summary of only the technical assets you have exported.<br>-<br>` Business`. The summary sheet in the exported file contains a<br>summary of only the business assets you have exported.|


Export assets using search queries       103

### Request body

Use the request body to specify the pagination parameters. The API response exports the specified number of assets that match the search criteria. 

In the body of the request, specify the pagination parameters in the following format: 

```
{
    "from": 0,
    "size": 10,
}
```

The following list describes the pagination parameters that you can use to specify the number of assets that you want to export: 

- `from` . Specify a numeric value to set an offset for pagination. The default value is zero. 

- `size` . Specify a numeric value for the number of assets that you want to export. The default value is 10. 

Consider the following scenarios when you specify the number of assets to export using this API: 

- To export more than 50,000 assets that match the search criteria, remove the `from ` parameter and specify the number of assets to export in the ` size ` parameter of the request body. If ` size` is set to more than 50,000 assets, then the exported file is in the ZIP format containing multiple files. Each file is limited to 50,000 rows. 

- To export all assets that match the search criteria, remove the `from ` parameter and specify zero as the value of the ` size` parameter in the request body. 

### Example requests

#### Export more than 50,000 assets

The following example shows the POST request to export and download 55,000 assets that start with the name `Customer` . The summary sheet in the exported file will contain a summary of the assets. 

`POST` **https://idmc-api.dm-us.informaticacloud.com/data360/search/export/v1/assets? knowledgeQuery=Customer*&fileName=Export__all_assetse&summaryViews=all** 

```
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>

Body
{
    "size": 55000
}
```

#### Export all assets in the catalog

The following example shows the POST request to export and download all assets in the catalog. The summary sheet in the exported file will contain a summary of the assets. 

`POST` **https://idmc-api.dm-us.informaticacloud.com/data360/search/export/v1/assets? knowledgeQuery=all&fileName=Export__all_assetse&summaryViews=all** 

```
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>

Body
{
    "size": 0
}
```

#### Export all details of all business assets

The following example shows the POST request to export all details of business assets in the catalog and downloads 500 assets based on the `size` body parameter. The summary sheet in the exported file will contain a summary of the assets. 

`POST` **https://idmc-api.dm-us.informaticacloud.com/data360/search/export/v1/assets?knowledgeQuery=Business Assets&segments=all&fileName=Export_32_all_segment_all_view&summaryViews=all** 

```
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
Body
{
    "from": 0,
    "size": 500
}
```

#### Export the summary and custom attributes of all business assets

The following example shows the POST request to export the summary and custom attributes of business assets in the catalog and downloads 10 business assets based on the `size` body parameter. The summary sheet in the exported file will contain a summary of the business assets. 

`POST` **https://idmc-api.dm-us.informaticacloud.com/data360/search/export/v1/assets?knowledgeQuery=Business Assets&segments=summary,customAttributes&fileName=Export_32_all_segment_all_view&summaryViews=Business** 

```
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
Body
{
    "from": 0,
    "size": 10
}
```

## Export assets using asset IDs

To search for assets in the catalog using asset IDs and export the assets in a Microsoft Excel or a CSV file, send a POST request. Specify the asset IDs in the request body and use the request parameters to specify the details of the assets that you want to export. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|POST|**<baseApiUrl>/data360/search/export/v1/assets/details**|


The base API URL differs for each pod. For more information, see “Send requests” on page 9. 

Export assets using asset IDs       105

### Request parameters

To export specific details of the exported assets, use the request URL parameters and specify the details that you want to export. 

The following table describes the parameters that you can specify in the request URL: 

|**Parameter**|**Description**|
|---|---|
|`segments `|Specify the level of asset detail that you want the API request to return.<br>Enter any segment value from the following values:<br>-<br>` all `. Returns the summary, system attributes, and the API request<br>link to view further details of the asset.<br>-<br>` summary `. Returns the name, description, and location of the asset.<br>-<br>` lineage `. Returns the lineage details of the asset. The export file<br>includes separate sheets for data sets and data elements. Each<br>sheet contains columns that map the asset's full lineage, including<br>its upstream sources and downstream systems.You can download<br>the export file in either CSV or Microsoft Excel format.<br>**Note:**<br>- You can export lineage for only one asset per request.<br>- The API returns the lineage details only when you specify the<br>` lineage` segment. If you select the` all` segment, the API<br>response will not include lineage details.<br>- The only segments you can add alongside the lineage segment in<br>the request URL when exporting lineage are` customAttributes`<br>and` hierarchy `.<br>- The**Lineage Level**field in the sheet categorizes assets based on<br>their relationship to the source. A value of 0 identifies the seed<br>asset. Negative values denote upstream assets while positive<br>values indicate downstream assets.<br>-<br>` systemAttributes `. Returns the origin, class type of the asset,<br>base types, user that created or updated the asset, and the date on<br>which the asset was created or updated.<br>-<br>` customAttributes `. Returns the custom fields specified for the<br>asset.<br>-<br>` selfAttributes `. Returns the properties of assets.<br>-<br>` stakeholdership `. Returns the type of user and role that are<br>assigned as stakeholder for the asset.<br>-<br>` dataProfile `. Returns the profiling statistics of the asset.<br>-<br>` dataClassification `. Returns the data classification associated<br>with the asset.<br>-<br>` glossary `. Returns the glossary associated with the asset.<br>-<br>` dataQuality `. Returns the data quality score of the asset.<br>-<br>` hierarchy `. Returns the hierarchy of the asset.<br>-<br>` neighborhood`. Returns assets that are in a direct relationship with<br>the searched asset.<br>The default value is` segments=all`.|
|`fileName`|Specify the name of the Microsoft Excel output file that you want to<br>export. The file name should not contain spaces. Specify the file<br>extension. The exported file will either be in the`.xlsx` or in the`.csv`<br>format.|

|**Parameter**|**Description**|
|---|---|
|`summaryViews `|If you set this parameter, the exported Microsoft Excel file includes a<br>summary sheet. Specify the type of assets you want to see in the<br>summary sheet. You can specify one of the following values:<br>-<br>` all `. The summary sheet in the exported file contains a summary of<br>all the assets you have exported.<br>-<br>` Technical `. The summary sheet in the exported file contains a<br>summary of only the technical assets you have exported.<br>-<br>` Business`. The summary sheet in the exported file contains a<br>summary of only the business assets you have exported.|
|`scheme `|Specify the type of asset ID you want to use to query assets. Enter one<br>of the following values:<br>-<br>` internal `. Indicates that the asset ID that you specify in the request<br>is the internal ID of the asset.<br>-<br>` external`. Indicates that the asset ID that you specify in the request<br>is the unique reference ID of the asset.|


### Request body

Use the request body to specify the external or internal IDs of assets you want to export. You can specify in the `scheme` parameter of the request URL whether you want to search the asset by external ID or internal ID. 

In the body of the request, specify comma-separated asset IDs in the following format: 

- `[ "0c5777c2-ec04-4a57-8bf0-fc895c4579a2", "982cd858-b5b0-4cea-b120-cad3c325e38e", "e907f08f-6327-4214-9401-c794bc0c34db", ]` 

If you don't know the ID of a particular asset, you can first send a POST request to view the list of assets in the catalog. This request returns the internal ID of each asset in the `core.identity ` parameter and the unique reference ID of the asset in the ` core.externalId` parameter. For more information, see “Retrieve assets in the catalog” on page 15. 

### Example request

Export technical assets along with the specified details 

The following example shows the POST request to export the summary, data profile statistics, data classifications, and the associated glossary details of seven technical assets in a file named `Export_technical_assets` . The IDs of the technical assets are specified in the body of the request. 

```
POST https://idmc-api.dm-us.informaticacloud.com/data360/search/export/v1/assets/details?
segments=summary,dataProfile,dataClassification,glossary&fileName=Export_technical_assets
&summaryViews=all&scheme=internal
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
Body
[
    "0c5777c2-ec04-4a57-8bf0-fc895c4579a2",
    "982cd858-b5b0-4cea-b120-cad3c325e38e",
    "e907f08f-6327-4214-9401-c794bc0c34db",
    "a378e9fa-e4b1-427d-ae9b-978111d431dd",
    "7d01c99f-f57b-44f0-8dd9-ff3721f1eb93",
    "23fd6de2-b0aa-4c79-aa5f-c83205164fc1",
```

Export assets using asset IDs       107

```
    "aaace41b-3dff-4a74-82f7-f4e7874dacb8"
]
```

Export lineage with custom attributes and hierarchy details 

```
Post https://idmc-api.dm-us.informaticacloud.com/data360/search/export/v1/assets/details?
segments=lineage&segments=hierarchy&segments=customAttributes&fileName=BA_PAPI_IL_IH_IDV&
scheme=internal&fileType=CSV
Authorization: Bearer <jwt_token>
Content-Type: application/json
X-INFA-ORG-ID:<Org ID>
Body
[
     "ef91ffb7-43ac-48d5-9c4f-a002eb2842d7"
]
```

## Response body

When you send a POST request to bulk export assets from the catalog, the API triggers a job to export the assets. 

The following example shows the response of the POST request to bulk export assets from the catalog: 

```
{
    "jobId": "6a5b273a-f816-4346-a8ae-f2d27f3e3240",
    "trackingURI": "/data360/observable/v1/jobs/6a5b273a-f816-4346-a8ae-f2d27f3e3240?
expandChildren=OUTPUT-PROPERTIES",
    "outputURI": "/data360/observable/v1/jobs/6a5b273a-f816-4346-a8ae-f2d27f3e3240/
outputProperties/files/Export_File"
}
```

The following table describes the response body parameters for the export API request: 

|**Parameter**|**Description**|
|---|---|
|`jobId`|Unique ID of the export job that the request triggers.|
|`trackingURI`|Job tracking URI that you can use to send a GET request to monitor the status of the export job.|
|`outputURI`|Output file URI that you can use to send a GET request to download the exported file.|


Once the job completes, you can send a GET request to download the exported file using the outputURI. The exported file is a Microsoft Excel file in the `.xslx` format or a CSV file. 

**Note:** If the export file contains more than 10,000 assets, the system compresses the data into a ZIP file. 

For more information about using the jobs API to monitor the status of jobs, see Chapter 10, “Monitor jobs” on page 116.

# Chapter 8: Get audit history

Use the audit API to get the audit history of one or multiple assets. 

The following image shows the overall process of using the audit API to get the audit history of assets: 


<!-- Start of picture text -->
Obtain the POST<br>sessionID using<br>the Login API Generate. W token SET<br>Request Body:- using theAPLWT Token data360/audit/vi/assets/eventsGet aucitassetshistorywithofthe muttiple GetEraucit history ofa specificeeasset<br>‘Usemame paler using asset ID with the<br>Pesvwerd Request Header? eS ata360/audit/vi/assets/lassetIDYevents<br>Sra AteessondTooke USER SESSION= ‘Authorization? ‘endpoint<br>‘sersionls Bearer Token<br>owe {5S SESSONID seston Request Header: ‘hathozation<br>Response: Beare’ Token<br>wt token Token) XINPRORCID—— ond Request Header:<br>fi XINPRORGIO: ogld<br>) {fet i ter son) Parameters:f<br>= ,‘1 gcheme, herareryOptions, lteter, 2<br>t<br>2 Temeetamp Response:<br>per) t<br>faced, ot<br>eet2+ hateCreated 12 TheseBen,iD<br>+ MeaiiedBy, Creat on + EventType<br>1+3 AssetChangedDetala AeributesBy, Mofied On +1* Created Modehein By,By MofeCreated d  OnOn<br>5 arse Detals<br>55 Chonged Atrios<br><!-- End of picture text -->

## Get audit history of multiple assets

To get the audit history of multiple assets, send a GET request using the audit API. Use the request URL parameters to specify the external or internal IDs of up to five assets and the details of the audit history that you want to view. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|GET|**<baseApiUrl>/data360/audit/v1/assets/events**|


The `<baseApiUrl>` differs for each pod. For more information about the base API URL, see “Send requests” on page 9.

### Request parameters

In the request URL parameters, specify the internal or external IDs of up to five assets or specify the asset type to view the audit history. You can additionally specify the pagination and sorting parameters along with the other audit history details that you want the request to return. 

The following table describes the parameters that you can specify in the request URL: 

|**Parameter**|**Description**|
|---|---|
|`offset`|Specify a numeric value to set an offset for pagination. The default<br>value is zero.|
|`limit`|Specify a numeric value between 1 to 100 for the number of records<br>that you want to view on the page. The default value is 100.|
|`filters `|Specify the fields and values of the audit events that you want the API<br>to use to filter the results. The request returns the audit events based<br>on the fields that you apply as filters. Use the following format to apply<br>filters:<br>` field name:operator:(field value)`<br>You can apply filters on one or more field names from the following<br>list:<br>-<br>`assetId` or` assetExternalId `. Specify the internal or external IDs<br>of assets to view their audit history. You can specify up to five IDs<br>separated by commas. For example, enter the following:<br>` assetExternalId:IN:(50K_BT_CDGC_34,CDGC_BT_4235)`<br>-<br>`assetTypes `. Specify the type of asset to view its audit history. Use<br>this filter option only if you didn't specify the external or internal ID<br>of the asset. For example, enter the following:` assetTypes:IN:`<br>`(com.infa.ccgf.models.governance.BusinessTerm)`<br>-<br>`timestamp `. Specify the date and time on which the asset was last<br>updated. To specify the time range, use the following operators:<br>-<br>` GE `. Greater than equal to<br>-<br>` LE `. Less than equal to<br>For example, enter the following:` timestamp:GE:`<br>`(2024-01-29T18:21:14.564Z)`.<br>-<br>`eventType `. Specify the events that include any changes to the<br>asset, relationships, or stakeholders. For example, enter the<br>following:` eventType:IN:(Stakeholder,Relationship)`.<br>-<br>`action `. Specify the actions that are performed on the asset to view<br>the associated audit events. For example, enter the following:<br>` action:IN:(UPDATE)`.<br>-<br>`changedAttributes `. Specify the changed attribute of the asset.<br>For example, enter the following:` changedAttributes:IN:`<br>`(com.infa.odin.models.file.path,core.resourceType,co `<br>` re.reference,core.name,core.assetLifecycle,core.sour `<br>` ceModifiedOn)`.<br>-<br>`modifiedBy `. Specify the ID of the user who has performed any<br>action on the asset. For example, enter the following:<br>` modifiedBy:IN:datastewards_org`.|
|`sort`|Sort the order of the events of the audit history by timestamps, event<br>type, action, or the user ID who modified the asset. By default, the audit<br>history of each asset is sorted in the descending order of the time of<br>its events.<br>For example, you can enter` timestamp:ASC` to sort the events of the<br>audit history of assets in the ascending order of the time of their<br>events.|

### Example request

Get the audit history of business terms 

The following example shows the GET request to get the audit history of business terms based on the asset type specified in the `filter` parameter. The response returns audit events that are generated after the specified date and time. The response displays a maximum of 100 records per page. 

```
GET https://idmc-api.dm-us.informaticacloud.com/data360/audit/v1/assets/events?
offset=0&limit=100&filter=timestamp:GE:(2025-05-01T18:21:14.564Z)&filter=assetTypes:IN:
(com.infa.ccgf.models.governance.BusinessTerm)
```

#### Get the audit history of assets using the external ID

The following example shows the GET request to get the audit history of two assets based on the external IDs specified in the `filter` parameter. The response returns audit events that are generated before the specified date and time. The response displays a maximum of 100 records per page. 

```
GET https://idmc-api.dm-us.informaticacloud.com/data360/audit/v1/assets/events?
offset=0&limit=100&filter=assetExternalId:IN:
(50K_BT_CDGC_34,CDGC_BT_4235)&filter=timestamp:LE:(2025-05-01T18:21:14.564Z)
```

## Get audit history of a specific asset

To get the audit history of a specific asset, send a GET request using the audit API. Specify the asset ID in the endpoint and use the request URL parameters to sort, filter, and specify other details of the audit history that you want to view. 

The following table provides the HTTP method and endpoint for the API call: 

|**Method**|**Endpoint**|
|---|---|
|GET|**<baseApiUrl>/data360/audit/v1/assets/<asset ID>/events**|


The `<baseApiUrl>` differs for each pod. For more information about the base API URL, see “Send requests” on page 9. 

The `<assetID>` that you enter can be the internal ID or the external ID of the asset. You can specify in the `scheme` parameter of the request URL whether you want to retrieve the asset by external ID or internal ID. 

Get audit history of a specific asset       111

### Request parameters

In the request URL parameters, specify the type of asset ID that you want to use to retrieve the asset. You can additionally specify whether you want to view the audit history of the child assets in the hierarchy, pagination, and sorting parameters along with the other audit history details that you want the request to return. 

The following table describes the parameters that you can specify in the request URL: 

|**Parameter**|**Description**|
|---|---|
|`scheme `|Specify the type of asset ID you want to use to retrieve assets. Enter<br>one of the following values:<br>-<br>` internal `. Indicates that the asset ID that you specify in the request<br>is the internal ID of the asset.<br>-<br>` external`. Indicates that the asset ID that you specify in the request<br>is the unique reference ID of the asset.|
|`hierarchyOptions `|Specify whether you want to get the audit history of the assets in the<br>hierarchy of the open asset. Enter one of the following values:<br>-<br>` EXCLUDE HIERARCHY `. Returns the audit history of only the specified<br>asset.<br>-<br>` INCLUDE ALL CHILDREN `. Returns the audit history of the specified<br>asset and all its associated child assets.<br>-<br>` INCLUDE IMMEDIATE CHILDREN`. Returns the audit history of the<br>immediate child assets of the specified asset.<br>The default value is` EXCLUDE HIERARCHY`.|

|**Parameter**|**Description**|
|---|---|
|`filters `|Specify the fields and values of the audit events that you want the API<br>to use to filter the results. The request returns the audit events based<br>on the fields that you apply as filters. Use the following format to apply<br>filters:<br>` field name:operator:(field value)`<br>You can apply filters on one or more field names from the following<br>list:<br>-<br>`timestamp `. Specify the date and time on which the asset was last<br>updated. To specify the time range, use the following operators:<br>-<br>` GE `. Greater than equal to<br>-<br>` LE `. Less than equal to<br>For example, enter the following:` timestamp:GE:`<br>`(2024-01-29T18:21:14.564Z)`.<br>-<br>`eventType `. Specify the events that include any changes to the<br>asset, relationships, or stakeholders. For example, enter the<br>following:` eventType:IN:(Stakeholder,Relationship)`.<br>-<br>`action `. Specify the actions that are performed on the asset. For<br>example, enter the following:` action:IN:(CREATE)`.<br>-<br>`changedAttributes `. Specify the changed attribute of the asset.<br>Your request can contain the following attributes:<br>-<br>` com.infa.odin.models.file.path `<br>-<br>` core.assetLifecycle `<br>-<br>` core.changedatacapture.audience `<br>-<br>` core.changedatacapture.hiSequenceNo `<br>-<br>` core.changedatacapture.lowSequenceNo `<br>-<br>` core.changedatacapture.requestid `<br>-<br>` core.changedatacapture.status `<br>-<br>` core.changedatacapture.traceid `<br>-<br>` core.createdOn `<br>-<br>` core.identity `<br>-<br>` core.name `<br>-<br>` core.reference `<br>-<br>` core.resourceType `<br>-<br>` core.sourceModifiedOn `<br>For example, enter the following:` changedAttributes:IN:`<br>`(com.infa.odin.models.file.path,core.resourceType,co `<br>` re.reference,core.name,core.assetLifecycle,core.sour `<br>` ceModifiedOn)`.<br>-<br>`modifiedBy `. Specify the ID of the user who has performed any<br>action on the asset. For example, enter the following:<br>` modifiedBy:IN:datastewards_org`.|
|`offset`|Specify a numeric value to set an offset for pagination. The default<br>value is zero.|
|`limit`|Specify a numeric value between 1 to 100 for the number of records<br>that you want to view on the page. The default value is 100.|
|`sort`|Sort the order of the events of the audit history by timestamps, event<br>type, action, or the user ID who modified the asset. By default, the audit<br>history of each asset is sorted in the descending order of the time of<br>its events.<br>For example, you can enter` timestamp:ASC` to sort the events of the<br>audit history of assets in the ascending order of the time of their<br>events.|


Get audit history of a specific asset       113

### Example request

Get the audit history of a specific asset 

The following example shows the GET request to get the audit history of an asset based on the external ID specified in the endpoint. The response returns audit events that are generated after the specified date and time. The response returns a maximum of 100 records per page in the descending order of the event types. 

```
GET https://idmc-api.dm-us.informaticacloud.com/data360/audit/v1/assets/50K_BT_CDGC_34/
events?scheme=EXTERNAL&offset=0&limit=100&sort=eventType:DESC,filter=timestamp:GE:
(2024-01-29T18:21:14.564Z)
```

Get the audit history of an asset and all its child assets 

The following example shows the GET request to get the audit history of an asset and all its child assets based on the `hierarchyOptions` parameter. The response returns a maximum of 100 records per page in the ascending order of the date and time of the events. 

```
GET https://idmc-api.dm-us.informaticacloud.com/data360/audit/v1/assets/50K_BT_CDGC_34/
events?
scheme=EXTERNAL&hierarchyOptions=INCLUDE_ALL_CHILDREN&offset=0&limit=100&sort=eventId:DES
C,timestamp:ASC
```

# Chapter 9: Manage catalog sources

Use APIs to work with catalog sources. 

You can use APIs to perform the following tasks: 

- Create a catalog source 

- Retrieve a catalog source 

- List catalog sources 

- Update catalog source 

- Delete catalog source 

- Run a catalog source job 

For information about how you can use APIs to manage catalog sources, see the Informatica Developer Portal. 

The following image shows the overall process of running a catalog source job and the expected response for the request: 


<!-- Start of picture text -->
Obtain the<br>sessionID using<br>the Login API Generate a JW token<br>Request Body : using theAptJWT Token /data360/executable/v1/catalogsource/{catalogSourceUUID}Run a catalog source job using the<br>Username endpoint<br>Response:Reesessionid RequestHeader:1S-SESSIOND(sessonid})‘cookie: USER_SESSION=((sessionid}) ‘Authorization:Bearer Token<br>oad Response: Request Header:<br>jwtfoken (Toker) IDS-SESSION-ID  {sessionld)}<br>XINFA-ORG-ID : orgld<br>Comm<br>“eapabilityNames":[“Metadata Extraction’,<br>"Data Profiling’,<br>1 "Data Classification”<br>}<br>ra<br>+ Job ID - Unique 1D ofthe submitted job<br>+ ‘Tracking URI The tracking URI to monitor the job<br>+ Status~ The status ofthe submitted job<br>=<br>POS’GET +) Task‘such Groups as group ID,The group details name, about and thetask submitteddet<br><!-- End of picture text -->

### C h a p t e r 1 0

# Chapter 10: Monitor jobs

Use the jobs API to monitor the status of any job in Data Governance and Catalog and download the job output files. 

For information about the jobs API, see the Informatica Developer Portal. 

The following image shows the overall process of using the jobs API and the expected response for each endpoint: 


<!-- Start of picture text -->
Generate a JW token Download the output file of the job<br>to APL data360/observable/\1/jobs/{{JOB data360/observable/v/jobs/{{10B.<br>— 1D}} endpoint 1D}}/outputProperties/files/{{outputfile<br>besaon (entsberecookie: USER_SESSION= a endpoint<br>—mre eeTanone ood; aeraeT<br>vasesneeaneeTf ‘tenders outrutenoremés, | |CineRtronn:‘ one Do<br>u ‘Output file for the job<br>a:<br>i mS<br>sehfiedunuebeyssoase ae<br>POST 0<br>GET<br><!-- End of picture text -->

# Appendix A: Search for assets

This appendix includes the following topics: 

- Search query examples, 117 

- Class types for business assets, 141 

- File size limits for API payload fields, 142 

## Search query examples

In the search bar, you can progressively build complex search queries to search for different assets, relationships and associations between assets, data classifications and more. As you type your search query, the search provides intelligent suggestions. 

You can perform search queries in Data Governance and Catalog for the following areas: 

- Business assets 

- Technical assets 

- Data access assets 

- Asset groups 

- Relationships 

- Stakeholders 

- Lifecycle 

- Date and time 

- Collaboration 

- Classifications 

- Custom attributes 

- Data quality rule occurrences 

- Glossary assets associations with data classifiations 

You can refer to this chapter to find and use suitable search query examples.

### Business asset search query examples

You can use search queries to search for business assets like Business Terms, Domain assets, Policy assets, and more. 

The following table lists some sample business asset search query that you can try in the search box: 

|**Search query**|**What it does**|
|---|---|
|`all in domain HIPAA`*|Shows all assets that are child assets or belong to Domain assets that<br>start with the text` HIPAA`. The search returns results on the name, alias<br>name, or business name fields of the asset.|
|`all in 'domain Human Resources'`|Shows all assets that are children or belong to Domain assets that start<br>with the name` Human Resources`.|
|`all related to AI System`<br>`"CLAIRE"`|Shows all assets that are related to AI System assets with the name<br>`CLAIRE`.|
|`data sets related to AI System`<br>`"Gemini"`|Shows all Data Set assets that are related to AI System assets with the<br>name` Gemini`.|
|`AI model related to AI System`<br>`"Grok"`|Shows all AI Model assets that are related to AI System assets with the<br>name` Grok`.|
|`project related to AI System`<br>`"CLAIRE"`|Shows all Project assets that are related to AI System assets with the<br>name` CLAIRE`.|
|`business term 'phone number'`|Shows all Business Term assets with the text` Phone Number`. The<br>search returns results on the name, alias name, or business name fields<br>of the asset.|
|`business term phone*`|Shows all Business Term assets that start with text` Phone`. The search<br>returns results on the name, alias name, or business name fields of the<br>asset.|
|`business terms which are `<br>` critical data element`|Shows all Business Term assets that are marked as**Critical Data**<br>**Element**.|
|`business terms in domain `<br>` Finance*`|Shows all Business Term assets that are children of Domain assets that<br>start with the name` Finance`.|
|`business terms in subdomain`<br>`*Payroll`|Shows all Business Term assets that are children of Subdomain assets<br>that end with the text` Payroll`.|
|`business terms where format `<br>` type is Text`|Shows all Business Term assets with Text in the**Format Type**field.|
|`business terms without `<br>` description`|Shows all Business Term assets with the**Description**field empty.|
|`business terms related to `<br>` policy "CCPA Policy"`|Shows all Business Term assets related to Policy assets with the name<br>`CCPA Policy`.|
|`business terms which are not `<br>` critical data element`|Shows all Business Term assets that are not marked as**Critical Data**<br>**Element**.|

|**Search query**|**What it does**|
|---|---|
|`business terms 'phone number',`<br>`Metric 'EBITDA'`|Shows all Business Term assets that have the name` phone number` and<br>all Metric assets that have the name` EBITDA`. The search returns results<br>on the name, alias name, or business name fields of the asset.|
|`dataset Customers*`|Shows all business Data Set assets that start with the text` Customers`.<br>The search returns results on the name, alias name, or business name<br>fields of the asset.|
|`dataset containing manual data `<br>` element 'Party Data'`|Shows all data sets containing manual data elements with the text` Party `<br>` Data`.|
|`domain 'Party Data'`|Shows all Domain assets with the text` Party Data`. The search returns<br>results on the name, alias name, or business name fields of the asset.|
|`domains 'Party Data*',`<br>`subdomains 'Party Data*'`|Shows all Domain assets that start with the text` Party Data` and<br>Subdomain assets that start with the text` Party Data`. The search<br>returns results on the name, alias name, or business name fields of the<br>asset.|
|`domains containing business `<br>` term Email`|Shows all Domain assets that have a parent-child relationship to<br>Business Term assets with the name` Email`.|
|`domains containing subdomain`<br>`'Party Data*'`|Shows all Domain assets that have a parent-child relationship to<br>Subdomain assets that start with` Party Data`.|
|`domains containing metric `<br>` EBITDA`|Shows all Domain assets that have a parent-child relationship to Metric<br>assets with the name` EBITDA`.|
|`manual data element Customers*`|Shows all manual data elements starting with the text` Customers*`.|
|`manual data element matching`<br>`'Product Code'`|Shows all manual data elements matching the name`'Product Code'`.|
|`manual data elements in dataset `<br>` EMPLOYEE.`|Shows all manual data elements that are in the data set` EMPLOYEE`.|
|`metric EBITDA`|Shows all Metric assets with the name` EBITDA`.|
|`metric without description`|Shows all Metric assets with the**Description**field empty.|
|`policy GDPR*`|Shows all Policy assets that start with the text` GDPR`. The search returns<br>results on the name, alias name, or business name fields of the asset.|
|`policies, processes`|Shows all Policy and Process assets.|
|`policies without description`|Shows all Policy assets with the**Description**field empty.|
|`policy P*, process P*`|Shows all Policy assets that start with` P` and Process assets that start<br>with` P`. The search returns results on the name, alias name, or business<br>name fields of the asset.|
|`policy *University*, domain`<br>`*University*`|Shows all Policy assets that contains the text` University` and Domain<br>assets that contain the text` University`. The search returns results on<br>the name, alias name, or business name fields of the assets.|


Search query examples        119

|**Search query**|**What it does**|
|---|---|
|`processes without description`|Shows all Process assets with the**Description**field empty.|
|`process KYC`|Shows all Process assets that contain the text` KYC`. The search returns<br>results on the name, alias name, or business name fields of the asset.|
|`process 'Know Your Client'`|Shows all Process assets with the exact text` Know Your Client` in its<br>name, alias name, or business name fields.|
|`subdomain Payroll*`|Shows all Subdomain assets that start with the text` Payroll`. The search<br>returns results on the name, alias name, or business name fields of the<br>asset. lias name, or business name fields of the asset.|
|`subdomain 'Party Data'`|Shows all Subdomain assets with the exact text` Party Data` in its name,<br>alias name, or business name.|
|`subdomain Payroll* and `<br>` containing business term `<br>` Salary*`|Shows all Subdomain assets that start with` Payroll` that have a parent-child relationship to Business Term assets that start with` Salary`.|
|`subdomain 'Party Data*' and `<br>` related to policies`|Shows all Subdomain assets that start with` Party Data` and are related<br>to Policy assets.|
|`subdomain without description`|Shows all Subdomain assets with the**Description**field empty.|
|`subdomain 'Party Data*',`<br>`subdomains 'Party Data*'`|Shows all Subdomain assets that start with` Party Data` and are related<br>to Subdomain assets that start with` Party Data`.|
|`subdomain containing business `<br>` term Email`|Shows all Subdomain assets that have a parent-child relationship to<br>Business Term assets with the name` Email`.|
|`subdomain containing metric `<br>` EBITDA`|Shows all Subdomain assets that have a parent-child relationship to<br>Metric assets with the name` EBITDA`.|
|`system DWH*`|Shows all System assets that start with the text` DWH`. The search returns<br>results on the name, alias name, or business name fields of the asset.|
|`systems without description`|Shows all System assets with the**Description**field empty.|
|`domain with reference ID`<br>`'<Reference ID value>'`|Shows the domain that has the specified value in its reference ID field.|
|`<Reference ID value>`<br>`REL_BT_AUT_Parent_CSV_05`|Shows all assets with` REL_BT_AUT_Parent_CSV_05` in the names,<br>aliases, descriptions, and**Reference ID**.|
|`business term 'phone_number*'`|Shows all Business Term assets that start with the text` phone_number`.<br>The search returns results on the name, alias name, or business name<br>fields of the asset.|
|`domain 'Party-Data*'`|Shows all Domain assets that start with the text` Party-Data`. The<br>search returns results on the name, alias name, or business name fields<br>of the asset.|

|**Search query**|**What it does**|
|---|---|
|`domains containing subdomain`|Shows all Domain assets that have a parent-child relationship to|
|`'Party_Data*'`|Subdomain assets that start with` Party Data `,` Party_Data `, and<br>` Party-Data`.|
|`business terms 'phone-number*',`|Shows all Business Term assets that start with the text` phone-number`,|
|`Metric 'EBITDA'`|and all Metric assets with the name` EBITDA`. The search returns results<br>on the name, alias name, or business name fields of the asset.|


### Technical asset search query examples

You can use search queries to search for technical assets like files, tables, columns, catalog sources, and more. 

The following table lists some technical asset search query examples that you can try in the search box: 

|**Search query**|**What it does**|
|---|---|
|`unstructured files`|Shows all the unstructured files in the catalog. Unstructured files<br>include PDFs, text files, Microsoft Word document files, along with<br>the unrecognized file formats that the system treats as unstructured<br>files.|
|`document with name 'Customer*'`|Shows all unstructured files that start with the name` Customer`.<br>Unstructured files include PDFs, text files, Microsoft Word document<br>files, along with the unrecognized file formats that the system treats<br>as unstructured files.|
|`unstructured file with Modified On `<br>` within last 7 days`|Shows all the unstructured files that were modified in the last seven<br>days. Unstructured files include PDFs, text files, Microsoft Word<br>document files, along with the unrecognized file formats that the<br>system treats as unstructured files.|
|`all in resource <catalog source `<br>` name>`|Shows all the assets of the catalog source. The result includes the<br>data quality rule occurrences that have their primary data element<br>belonging to the catalog source.|
|`all with catalog source name `<br>` Oracle*`|Shows all assets that belong to catalog sources starting with the<br>name` Oracle*`.|
|`all with catalog source type`<br>`'Amazon S3'`|Shows all assets that belong to the catalog source type` Amazon S3`.|
|`columns related to business term`<br>`'Phone Number'`|Shows all columns that are related to Business Term assets named<br>`Phone Number`.|
|`columns related to data `<br>` classification 'Social Security `<br>` Number'`|Shows all columns that are related to data classifications named<br>`Social Security Number`.|
|`columns related to data `<br>` classification Email`|Shows all columns that are related to data classifications with the<br>name` Email`.|


Search query examples        121

|**Search query**|**What it does**|
|---|---|
|`columns in (catalog source Oracle*)`<br>`and related to (business term`<br>`'Phone Number')`|Shows all columns that are present in the catalog sources starting<br>with the name` ORACLE ` and are related to the Business Term asset<br>` Phone Number`.|
|`(columns which are profiled and in `<br>` catalog source Oracle*) related to `<br>` data classification Email`|Shows all columns that are profiled and in catalog sources that start<br>with the name` ORACLE` and are related to data classification` Email`.|
|`columns not related to business `<br>` term`|Shows all columns that are not related to Business Term assets.|
|`data elements related to business `<br>` term Email`|Shows all data elements that are related to Business Term assets<br>with the name` Email`.|
|`data elements related to data `<br>` classification 'Social Security `<br>` Number'`|Shows all data elements that are related to data classifications with<br>the name` Social Security Number`.|
|`data elements not related to data `<br>` classification`|Shows all data elements that are not related to any data<br>classification.|
|`data elements related to (data set `<br>` related to system DWH) and related `<br>` to Policy GDPR`|Shows all data elements that fulfill both the following criteria:<br>- related to Data Set assets that are in turn related to System assets<br>with the name` DWH`.<br>- related to Policy assets with the name` GDPR`.|
|`Data Elements related to data set `<br>` related to System related to Policy `<br>` GDPR`|Shows all data elements related to Data Set assets and are related to<br>System assets and are related to Policy assets with the name` GDPR`.|
|`Data Elements related to data set `<br>` related to System related to `<br>` Process KYC`|Shows all data elements related to Data Set assets and are related to<br>System assets and are related to Process assets with the name` KYC`.|
|`Data Elements related to data set `<br>` related to System related to`<br>`(Policy GDPR, Process KYC)`|Shows all data elements related to Data Set assets and are related to<br>System assets and are related to Policy assets with the name` GDPR`<br>and Process assets with the name` KYC`.|
|`data elements not related to `<br>` business term`|Shows all data elements that are not related to Business Term<br>assets.|
|`data elements related to data `<br>` classification`<br>`'Social_Security_Number*'`|Shows all data elements that are related to data classifications with<br>the name` Social Security Number `,<br>` Social_Security_Number`, and` Social-Security-Number`.|
|`glossaries with lifecycle Published `<br>` and related to policy GDPR*`|Shows all Glossary assets where the**Lifecycle**field is in the<br>**Published**status and are related to Policy assets that start with<br>`GDPR`.|
|`glossaries with lifecycle Published `<br>` and related to (policy GDPR*,`<br>`policy CCPA*)`|Shows all Glossary assets where the**Lifecycle**field is in the<br>**Published**status and are related to Policy assets that start with<br>`GDPR` and are related to Policy assets that start with` CCPA`.|
|`glossaries related to all in policy `<br>` GDPR `|Shows all Glossary assets related to all Policy assets with the name<br>` GDPR`.|

|**Search query**|**What it does**|
|---|---|
|`glossaries related to policy GDPR `|Shows all Glossary assets related to Policy assets with the name<br>` GDPR`.|
|`glossaries cust* and related to `<br>` policy GDPR`|Shows all Glossary assets that start with` cust` and are related to<br>Policy assets with the name` GDPR`.|
|`bi reports related to policy GDPR*`|Shows all BI reports that are related to Policy assets that start with<br>`GDPR`.|
|`bi reports related to process KYC*`|Shows all BI reports that are related to Process assets that start with<br>`KYC`.|
|`tables related to column City`|Shows all tables that are related to columns with the name` City`.|
|`data elements related to (business `<br>` term Customer ID, Customer Name)`|Shows all data elements related to Business Term assets with the<br>names` Customer ID` and` Customer Name`.|
|`catalog source ORACLE_HR`|Shows catalog source assets with the name` ORACLE_HR`. The search<br>returns results on the name, alias name, or business name fields of<br>the asset.|
|`catalog source ORACLE*`|Shows catalog source assets that start with the text` ORACLE`. The<br>search returns results on the name, alias name, or business name<br>fields of the asset.|
|`catalog sources with resource type `<br>` Oracle`|Shows all assets of the catalog source type` ORACLE`.|
|`catalog sources with resource type`<br>`'Amazon S3'`|Shows all assets of the catalog source type` Amazon S3`.|
|`columns Credit*`|Shows all columns that start with the text` Credit`. The search<br>returns results on the name, alias name, or business name fields of<br>the asset.|
|`columns which are profiled`|Shows all columns that are profiled.|
|`columns which are not profiled`|Shows all columns that are not profiled.|
|`columns which are not related to `<br>` data classification`|Shows all columns that are not related to data classifications.|
|`columns in catalog source Oracle*`|Show all columns in the catalog source types that start with` ORACLE`.|
|`flat field`|Shows all the flat field assets contained in flat files.|
|`data elements Credit*`|Shows all data elements that start with the text` Credit`. The search<br>returns results on the name, alias name, or business name fields of<br>the asset.|
|`data elements 'CREDIT_CARD'`|Shows all data elements with the name, alias name, or business<br>name as` CREDIT_CARD`.|
|`data elements which are profiled`|Shows all data elements that are profiled.|


Search query examples        123

|**Search query**|**What it does**|
|---|---|
|`file Customer*`|Shows all files that start with the name` Customer`. The search<br>returns results on the name, alias name, or business name fields of<br>the asset.|
|`folder Customer*`|Shows all folders that start with the name` Customer`. The search<br>returns results on the name, alias name, or business name fields of<br>the asset.|
|`folder containing hierarchical file `<br>` Customer*`|Shows all folders that contain files that start with` Customer`.|
|`folder containing file Customer*`|Shows all folders that contain files that start with` Customer`.|
|`glossaries`|Shows all Glossary assets.|
|`glossaries cust*`|Shows all Glossary assets that start with the text` cust`. The search<br>returns results on the name, alias name, or business name fields of<br>the asset.|
|`glossaries with stakeholder`<br>`@<username>`|Shows all Glossary assets with a user name or user group in the<br>**Stakeholder**field.|
|`glossaries without stakeholder`|Shows all Glossary assets with the**Stakeholder**field empty.|
|`bi report 'Quarterly Sales'`|Shows all BI reports with the name, alias name, or business name as<br>`Quarterly Sales`.|
|`bi reports with stakeholder`<br>`@<username>`|Shows all BI reports with a user name or user group in the<br>**Stakeholder**field.|
|`bi reports without stakeholder`|Shows all BI reports with the**Stakeholder**field empty.|
|`reports modified by @<username>`|Shows all reports with a user name or user group in the**Modified By**<br>field.|
|`Tableau Workbook Org*`|Shows all Tableau Workbook assets that start with the text` Org`. The<br>search returns results on the name, alias name, or business name<br>fields of the asset.|
|`table CUST*`|Shows all tables that start with the text` CUST`. The search returns<br>results on the name, alias name, or business name fields of the<br>asset.|
|`tables which are profiled`|Shows all columns that are profiled.|
|`hierarchical file Employees*`|Shows all hierarchical files that start with the text` Employees`. The<br>search returns results on the name, alias name, or business name<br>fields of the asset.|
|`bi reports created by @<username>`|Shows all BI reports with a user name or user group in the**Created**<br>**By**field.|
|`tables in catalog source Oracle*`|Shows all tables in catalog source types that start with` ORACLE`.|
|`tables in schema ORACLE_HR*`|Shows all tables in schemas that start with` ORACLE_HR`.|

|**Search query**|**What it does**|
|---|---|
|`((table "ACCOUNTS") in catalog `<br>` source Oracle*) in schema "GENERAL"`|Shows the table` ACCOUNTS` in catalog sources starting with` Oracle`<br>that is in the schema` General`.|
|`technical dataset EMPLOYEE*`|Shows all technical data set assets that start with the text<br>`Employee`. The search returns results on the name, alias name, or<br>business name fields of the asset.|
|`technical datasets which are `<br>` profiled`|Shows all technical data set assets that are profiled.|
|`technical datasets in catalog `<br>` source Oracle*`|Shows all technical data set assets in catalog source types that start<br>with` ORACLE`.|
|`technical datasets in schema `<br>` ORACLE_HR*`|Shows all technical data sets in schema source types that start with<br>`ORACLE_HR`.|
|`Property`|Shows all assets that have` Property` in their names, business<br>names, alias names, descriptions, or technical descriptions.|
|`Customer`|Shows all assets that have` Customer` in their names, business<br>names, alias names, descriptions, or technical descriptions.|
|`Phone_number`|Shows assets with the text` Phone number `,` Phone_number `, and<br>` Phone-number` in their names, business names, alias names,<br>descriptions, or technical descriptions.|


### Data access asset search query examples

You can use search queries to search for data access assets. 

The following table lists some sample data access asset search queries that you can try in the search box to query data access control, data de-identification, data filter, data protection, and precedence tier. 

**Note:** There are no plural synonyms for these asset types. 

|**Search query**|**What it does**|
|---|---|
|`data access assets`|Shows all data access assets.|
|`data access asset <name>`|Shows all data access assets matching the specified value. The search<br>returns results on the name field of the asset. If the name has a space<br>in it, enclose the name in double quotation marks.|
|`data access asset P*`|Shows all data access assets that start with` P`. The search returns<br>results on the name field of the asset.|
|`data access asset without `<br>` description`|Shows all data access assets with no values in the**Description**field of<br>the asset.|
|`data access asset related to `<br>` policy <name>`|Shows all data access assets related to the named policy business<br>asset.|
|`data access asset with `<br>` stakeholder @<username>`|Shows all data access assets for which the indicated user is a<br>stakeholder.|


Search query examples        125

|**Search query**|**What it does**|
|---|---|
|`data access asset without `<br>` stakeholder`|Shows all data access assets for which there is no stakeholder.|
|`data access asset created by`<br>`@<username>`|Shows all data access assets created by the indicated user. Use<br>`@CurrentUser` to search for data access assets that you created.|
|`data access asset modified by`<br>`@<username>`|Shows all data access assets modified by the indicated user. Use<br>`@CurrentUser` to search for data access assets that you modified.|
|`data access asset with lifecycle`<br>`<Draft, Published>`|Shows all data access assets in the indicated status.|
|`data access asset where lifecycle `<br>` is not <Draft, Published>`|Shows all data access assets not in the indicated status.|
|`data access asset which are "is `<br>` enabled"`|Shows all enabled data access assets.|
|`data access asset which are not`<br>`"is enabled"`|Shows all data access assets that are not enabled.|
|`data access asset with Asset `<br>` Group *`|Shows all data access assets within any Asset Group.|
|`data access asset without Asset `<br>` Group`|Shows all data access assets without an Asset Group.|
|`data access asset having comments`|Shows all data access assets with comments.|
|`data access asset not having `<br>` comments`|Shows all data access assets with no comments.|
|`data access asset commented `<br>` within last 2 days`|Shows all data access assets with comments created in the last two<br>days.|
|`data access asset end date within `<br>` last 2 days`|Shows all data access assets with an end date in the last two days.|
|`data access asset effective date `<br>` within last 2 days`|Shows all data access assets with an effective date in the last two<br>days.|
|`data access asset created on `<br>` within last 2 days`|Shows all data access assets created in the last two days.|
|`data access asset modified on `<br>` within last 2 days`|Shows all data access assets modified in the last two days.|
|`data access asset matching`<br>`<value>`|Shows all data access assets that match the specified value. The<br>search returns results on the name and description fields of the asset.|
|`Precedence tier with rank less `<br>` than 2`|Shows all precedence tiers with a rank less than the specified value.|
|`Precedence tier with rank greater `<br>` than 2`|Shows all precedence tiers with a rank greater than the specified<br>value.|

|**Search query**|**What it does**|
|---|---|
|`Precedence tier containing data `<br>` access assets`|Shows all precedence tiers that contain data de-identification policies.|
|`Precedence tier not containing `<br>` data access assets`|Shows all precedence tiers that don't contain data de-identification<br>policies.|
|`data access assets in precedence `<br>` tier 'Tier A'`|Shows all data de-identification policies in the named precedence tier.|
|`data access asset that is enabled `<br>` and where lifecycle is published`|Shows all data access assets that are enabled and published.|
|`data access asset with with `<br>` lifecycle in (draft, published)`|Shows all data access assets in either draft or published status.|
|`data access asset related to `<br>` policy <name> related to business `<br>` area H*`|Shows all data access assets that are related to any policy business<br>asset that has a relationship to any business area starting with "H."|
|`data access asset in precedence `<br>` tier with rank greater than 10`|Shows all data access assets that are in any precedence tier with rank<br>greater than 10.|
|`precedence tier containing data `<br>` access assets and with rank less `<br>` than 10`|Shows all precedence tiers with rank less than 10 that contain data<br>access assets.|
|`data access asset matching 'De-id', data access assets in `<br>` precedence tier with rank greater `<br>` than 10`|Shows all data access assets that contains the string "De-id" in the<br>**Name**or**Description**fields and is part of a precedence tier with a rank<br>greater than 10.|
|`data access asset with `<br>` enforcement method pushdown`|Shows all data access assets with an enforcement method of<br>pushdown.|
|`data access asset with `<br>` enforcement method on_query`|Shows all data access assets with an enforcement method of "Data<br>Integration/Data Marketplace."<br>**Note:**The underscore in "on_query" is required.|
|`data access asset with `<br>` enforcement method in (pushdown,`<br>`on_query)`|Shows all data access assets with an enforcement method of either<br>"pushdown" or "Data Integration/Data Marketplace."<br>**Note:**The underscore in "on_query" is required.|
|`data access asset with `<br>` enforcement method *`|Shows all data access assets with any enforcement method.|


Search query examples        127

### Asset groups search query examples

You can use search queries to search for assets that are assigned different asset groups. 

The following table lists some asset group search query examples: 

|**Search query**|**What it does**|
|---|---|
|`((Domain, Sub Domain) with asset group`<br>`@assetgroup:Finance) related to any`|Shows domains and subdomains that are<br>assigned the` Finance` asset group and are<br>related to any asset.|
|`(technical assets with asset group`<br>`@assetgroup:Finance) in catalog source Oracle`|Shows technical assets that are assigned<br>the` Finance` asset group and are in the<br>catalog source` Oracle`.|
|`(technical assets with Asset Group in`<br>`(@assetgroup:Finance,@assetgroup:Legal)) in catalog `<br>` source Oracle`|Shows technical assets that are assigned<br>the` Finance` and` Legal` asset groups and<br>belong to the catalog source` Oracle`.|
|`(column with asset group @assetgroup:Finance) in `<br>` schema Oracle_HR*`|Shows assets that are assigned the<br>`Finance` asset group and belong to the<br>schema source` Oracle_HR*`.|
|`(technical data set with asset group in`<br>`(@assetgroup:Finance)) related with curation status `<br>` ACCEPTED to data classifications`|Shows technical data sets that are assigned<br>the` Finance` asset group and are related to<br>data classifications that are in the accepted<br>state.|
|`(technical data set with asset group in`<br>`(@assetgroup:Finance) ) related with curation status `<br>` REJECTED to data classification Sales*`|Shows technical data sets that are assigned<br>the` Finance` asset group and are related to<br>the ` Sales*` data classifications that are in<br>the rejected state.|
|`all with asset group *`|Shows all assests that are assigned asset<br>groups.|
|`all with Asset Group @assetgroup:Finance `|Shows assets that are assigned the<br>` Finance` asset group.|
|`all without asset group`|Shows all assets that are not assigned asset<br>groups.|
|`asset with Asset Group @assetgroup:Finance `|Shows assets that are assigned the<br>` Finance` asset group.|
|`assets related to Policy with Asset Group`<br>`@assetgroup:EU`|Shows assets related to Policy assets that<br>are assigned the` EU` asset group.|
|`Assets related to sub domain with Asset Group`<br>`@assetgroup:Legal`|Shows assets related to subdomains that<br>are assigned the` Legal` asset group.|
|`Asset with asset group @assetgroup:Finance and `<br>` stakeholder @<username>`|Shows assets that are assigned the<br>`Finance` asset group and the stakeholder<br>`<username>`.|
|`business terms with asset group @assetgroup:EU`|Shows all business terms that are assigned<br>the` EU` asset group.|

### Relationship search query examples

You can use search queries to search for specific asset relationships. 

**Note:** When you search for assets with relationships to data classifications or glossary terms, the search shows only data classifications and glossaries that have been accepted during asset curation or creation. 

The following table lists some relationship search query examples that you can try in the search box: 

|**Search query**|**What it does**|
|---|---|
|`all related to business term email`|Shows all assets that are related to Business Term assets with the<br>name` email`.|
|`all related to domain HIPAA*, all `<br>` related to process KYC`|Shows all assets that are related to Domain assets that start with<br>the name` HIPAA` or all assets that are related to Process assets<br>that start with the name` KYC`.|
|`all related to glossary GDPR *`|Shows all assets related to Glossary assets that start with` GDPR`.|
|`business terms related to data `<br>` elements`|Shows all Business Term assets that are related to data elements.|
|`business terms related to tables`|Shows all Business Term assets that are related to tables.|
|`data classification related to data `<br>` element`|Shows all data classifications that are related to data elements.|
|`data element related to data set `<br>` EMPLOYEE`|Shows data elements that are related to the data set` EMPLOYEE`.|
|`business terms not related to data `<br>` elements`|Shows all Business Term assets that are not related to data<br>elements.|
|`dataset related to business term`<br>`'Customer'`|Shows all Data Set assets that are related to Business Term assets<br>with the name` Customer`.|
|`columns related to data `<br>` classification`|Shows all columns that are related to any data classification.|
|`data classifications related to data `<br>` elements`|Shows all data classifications that are related to data elements.|
|`data elements related to glossary`|Shows all data elements that are related to Glossary assets.|
|`datasets not related to business `<br>` term`|Shows all Data Set assets that are not related to Business Term<br>assets.|
|`data elements related to `<br>` classifications and related to `<br>` policy`|Shows all data elements that have associated classifications that<br>are related to a policy.|
|`data elements related with curation `<br>` status ACCEPTED to classification `<br>` with sensitivity Medium`|Shows all data elements for which the associated data<br>classifications are in the accepted state and are marked with High<br>sensitivity.|
|`datasets related to System related `<br>` to Policy GDPR`|Shows all Data Set assets related to System assets that are related<br>to Policy assets with the name` GDPR`.|


Search query examples        129

|**Search query**|**What it does**|
|---|---|
|`datasets related to System related `<br>` to Process KYC`|Shows all Data Set assets related to System assets that are related<br>to Process assets with the name` KYC`.|
|`datasets related to System related `<br>` to (Policy GDPR, Process KYC)`|Shows all Data Set assets related to System assets that are related<br>to Policy assets with the name` GDPR` and Process assets with the<br>name` KYC`.|
|`domains HIPAA* and related to `<br>` subdomains`|Shows all Domain assets that start with` HIPAA` and are related to<br>Subdomain assets.|
|`domains 'Party Data *' and related `<br>` to policies`|Shows all Domain assets with the name` Party Data` and are<br>related to Policy assets.|
|`domains HIPAA* and related to `<br>` subdomains`|Shows all Domain assets that start with` HIPAA` and are related to<br>Subdomain assets.|
|`domains 'Party Data *' and related `<br>` to policies`|Shows all Domain assets with the name` Party Data` and are<br>related to Policy assets.|
|`domains related to policy GDPR*`|Shows all Domain assets that are related to Policy assets that start<br>with` GDPR`.|
|`domains related to policies in `<br>` policy CCPA*`|Shows all Domain assets related to Policy assets that start with<br>`CCPA`.|
|`domain related to any`|Shows all Domain assets that are related to any asset.|
|`manual data element related to data `<br>` set EMPLOYEE `|Shows all manual data elements that are related to the data set<br>` EMPLOYEE`.|
|`assets related to data element `<br>` classification with classification `<br>` category in ('Name', 'List')`|Shows all assets related to a data element classification that<br>belongs to the classification categories` Name` or` List`.|
|`assets related to data element `<br>` classification with classification `<br>` category *`|Shows all assets related to a data element classification that<br>belongs to any classification category.|
|`manual data element related to data `<br>` set ADDRESS, data element related to `<br>` data set ADDRESS`|Shows all manual data elements and data elements that are related<br>to the data set` ADDRESS`.|
|`manual data element related to `<br>` glossary`|Shows all manual data elements that are related to Glossary assets.|
|`manual data element related to `<br>` Policy GDPR`|Shows all manual data elements that are related to Policy assets<br>with the name` GDPR`.|
|`manual data elements related to `<br>` classifications and related to `<br>` policy`|Shows all manual data elements that are related to classifications<br>and Policy assets.|
|`manual data elements related to `<br>` classification with name 'Social `<br>` Security Number'`|Shows all manual data elements that are related to classifications<br>with the name` Social Security Number`.|

|**Search query**|**What it does**|
|---|---|
|`Policy related to (System DWH) and `<br>` related to (System CMD)`|Shows all Policy assets related to System assets with the name` DWH`<br>and are related to System assets with the name` CMD`.|
|`processes related to Subdomain `<br>` Payroll*`|Shows all Process assets related to Subdomain assets that start<br>with` Payroll`.|
|`subdomain related to policy GDPR*`|Shows all Subdomain assets related to Policy assets that start with<br>`GDPR`.|
|`subdomain related to policies in `<br>` policy CCPA*`|Shows all Subdomain assets related to Policy assets that start with<br>`CCPA`.|
|`subdomain related to any`|Shows all Subdomain assets related to any asset.|
|`systems related to policy GDPR *`|Shows all System assets that are related to Policy assets that start<br>with` GDPR`.|
|`systems related to process KYC *`|Shows all System assets that are related to Process assets that<br>start with` KYC`.|
|`tables related to business terms`|Shows all tables that are related to Business Term assets.|
|`tables related to metric`|Shows all tables that are related to Metric assets.|
|`tables related to data entity `<br>` classifications`|Shows all tables that are related to data entity classifications.|


### Stakeholder search query examples

You can use search queries to search for assets that are created and modified by existing or deleted stakeholders. 

The following table lists some stakeholder or user name search query examples that you can try in the search box: 

|**Search query**|**What it does**|
|---|---|
|`all related to stakeholder where `<br>` stakeholder state is 'Deleted'`<br>**Note:**You can enter` stakeholder ` or<br>` stakeholders`.|Shows all assets that are assigned to stakeholders who have been<br>deleted.|
|`all with stakeholder @<deleted `<br>` username>`|Shows all assets that are assigned to a specific stakeholder who has<br>been deleted.|
|`all related to stakeholder where `<br>` stakeholder state is 'Inactive'`<br>**Note:**You can enter` stakeholder ` or<br>` stakeholders`.|Shows all assets that are assigned specific to stakeholders who are in<br>the inactive state.|
|`all with stakeholder @<inactive `<br>` username>`|Shows all assets that are assigned to a specific stakeholder who is in<br>the inactive state.|


Search query examples        131

|**Search query**|**What it does**|
|---|---|
|`business terms with stakeholder`<br>`@CurrentUser`|Shows all Business Term assets with your user name in the<br>**Stakeholder**field.|
|`business terms with stakeholder`<br>`@<username>`|Shows all Business Term assets with a user name in the**Stakeholder**<br>field.|
|`business terms without stakeholder`|Shows all Business Term assets with the**Stakeholder**field empty.|
|`business term created by`<br>`@CurrentUser`|Shows all Business Term assets created by you.|
|`business term created by`<br>`@<username>`|Shows all Business Term assets created by the user.|
|`business term modified by`<br>`@CurrentUser`|Shows all Business Term assets modified by you.|
|`business term modified by`<br>`@<username>`|Shows all Business Term assets modified by the user.|
|`datasets with stakeholder`<br>`@CurrentUser`|Shows all Data Set assets with your user name in the**Stakeholder**<br>field.|
|`datasets with stakeholder`<br>`@<username>`|Shows all Data Set assets with a user name in the**Stakeholder**field.|
|`datasets created by @CurrentUser`|Shows all Data Set assets created by you.|
|`datasets created by @<username>`|Shows all Data Set assets created by the user.|
|`datasets modified by @CurrentUser`|Shows all Data Set assets modified by you.|
|`datasets modified by @<username>`|Shows all Data Set assets modified by the user.|
|`domains with stakeholder`<br>`@CurrentUser`|Shows all Domain assets with your user name in the**Stakeholder**field.|
|`domains with stakeholder`<br>`@<username>`|Shows all Domain assets with a user name in the**Stakeholder**field.|
|`domains without stakeholder`|Shows all Domain assets with the**Stakeholder**field empty.|
|`domains created by @CurrentUser`|Shows all Domain assets created by you.|
|`domains created by @<username>`|Shows all Domain assets created by the user.|
|`domains modified by @CurrentUser`|Shows all Domain assets modified by you.|
|`domains modified by @<username>`|Shows all Domain assets modified by the user.|
|`metric with stakeholder`<br>`@CurrentUser`|Shows all Metric assets with your user name in the**Stakeholder**field.|
|`metric with stakeholder`<br>`@<username>`|Shows all Metric assets with a user name in the**Stakeholder**field.|

|**Search query**|**What it does**|
|---|---|
|`metric without stakeholder`|Shows all Metric assets with the**Stakeholder**field empty.|
|`metric created by @CurrentUser`|Shows all Metric assets created by you.|
|`metric created by @<username>`|Shows all Metric assets created by the user.|
|`metric modified by @CurrentUser`|Shows all Metric assets modified by you.|
|`metric modified by @<username>`|Shows all Metric assets modified by the user.|
|`policies with stakeholder`<br>`@CurrentUser`|Shows all Policy assets with your user name in the**Stakeholder**field.|
|`policies with stakeholder`<br>`@<username>`|Shows all Policy assets with a user name in the**Stakeholder**field.|
|`policies without stakeholder`|Shows all Policy assets with the**Stakeholder**field empty.|
|`policies created by @CurrentUser`|Shows all Policy assets created by you.|
|`policies created by @<username>`|Shows all Policy assets created by the user.|
|`policies modified by @CurrentUser`|Shows all Policy assets modified by you.|
|`policies modified by @<username>`|Shows all Policy assets modified by the user.|
|`processes with stakeholder`<br>`@CurrentUser`|Shows all Process assets with your user name in the**Stakeholder**<br>field.|
|`processes with stakeholder`<br>`@<username>`|Shows all Process assets with a user name in the**Stakeholder**field.|
|`processes without stakeholder`|Shows all Process assets with the**Stakeholder**field empty.|
|`processes created by @CurrentUser`|Shows all Process assets created by you.|
|`processes created by @<username>`|Shows all Process assets created by the user.|
|`processes modified by @CurrentUser`|Shows all Process assets modified by you.|
|`processes modified by @<username>`|Shows all Process assets modified by the user.|
|`subdomain with stakeholder`<br>`@CurrentUser`|Shows all Subdomain assets with your user name in the**Stakeholder**<br>field.|
|`subdomain with stakeholder`<br>`@<username>`|Shows all Subdomain assets with a user name in the**Stakeholder**<br>field.|
|`subdomain without stakeholder`|Shows all Subdomain assets with the**Stakeholder**field empty.|
|`subdomain created by @CurrentUser`|Shows all Subdomain assets created by you.|
|`subdomain created by @<username>`|Shows all Subdomain assets created by the user.|


Search query examples        133

|**Search query**|**What it does**|
|---|---|
|`subdomain modified by @CurrentUser`|Shows all Subdomain assets modified by you.|
|`subdomain modified by @<username>`|Shows all Subdomain assets modified by the user.|
|`systems with stakeholder`<br>`@CurrentUser`|Shows all System assets with your user name in the**Stakeholder**field.|
|`systems with stakeholder`<br>`@<username>`|Shows all System assets with a user name in the**Stakeholder**field.|
|`systems without stakeholder`|Shows all System assets with the**Stakeholder**field empty.|
|`systems created by @CurrentUser`|Shows all System assets created by you.|
|`systems created by @<username>`|Shows all System assets created by the user.|
|`systems modified by @CurrentUser`|Shows all System assets modified by you.|
|`systems modified by @<username>`|Shows all System assets modified by the user.|


### Lifecycle search query examples

You can use search queries to search for assets based on their lifecycle status. 

The following table lists some lifecycle search query examples that you can try in the search box: 

|**Search query**|**What it does**|
|---|---|
|`all where lifecycle is not `<br>` Published`|Shows all assets where the**Lifecycle**field is not in the**Published**status.|
|`all with lifecycle Draft`|Shows all assets where the**Lifecycle**field is in the**Draft**status.|
|`business terms with lifecycle `<br>` Draft`|Shows all Business Term assets where the**Lifecycle**field is in the**Draft**<br>status.|
|`domains with lifecycle Draft`|Shows all Domain assets where the**Lifecycle**field is in**Draft**status.|
|`metric with lifecycle draft`|Shows all Metric assets where the**Lifecycle**field is in the**Draft**status.|
|`policies with lifecycle Draft`|Shows all Policy assets where the**Lifecycle**field is in the**Draft**status.|
|`subdomain with lifecycle `<br>` Draft`|Shows all Subdomain assets where the**Lifecycle**field is in the**Draft**status.|
|`systems with lifecycle Draft`|Shows all System assets where the**Lifecycle**field is in the**Draft**status.|
|`Assets with Lifecycle (Draft,`<br>`'In Review')`|Shows all assets where the**Lifecycle**field is in**Draft**or**In Review**status.|

|**Search query**|**What it does**|
|---|---|
|`all with lifecycle 'Obsolete'`|Shows all assets where the**Lifecycle**field is in**Obsolete**status.|
|`business terms with lifecycle`<br>`'Obsolete'`|Shows all Business Term assets where the**Lifecycle**field is in**Obsolete**<br>status.|


### Date and time search query examples

You can use search queries to search for assets based on the creation or modification date and time. 

The following table lists some sample data and time search query examples that you can try in the search box: 

|**Search query**|**What it does**|
|---|---|
|`assets with created on before 2025-01-20`|Shows all assets created before the specified date.|
|`assets with updated after 2025-01-20`|Shows all assets updated after the specified date.|
|`assets with modified after 2025-01-20`|Shows all assets modified after the specified date.|
|`assets with created on between 2025-04-20 `<br>` and 2025-05-28`|Shows all assets created between the specified dates.|
|`assets with created on after `<br>` 2023-02-11T14:30:00Z`|Shows all assets created after the specified local date<br>and time.|
|`assets with created on before `<br>` 2023-02-11T14:30:00Z`|Shows all assets created before the specified local date<br>and time.|
|`assets with updated between `<br>` 2023-02-11T00:00:00Z and `<br>` 2025-02-14T00:00:00Z`|Shows all assets updated between the specified local<br>date and time.|
|`assets with modified between `<br>` 2023-02-11T00:00:00Z and `<br>` 2025-02-14T00:00:00Z`|Shows all assets modified between the specified local<br>date and time.|


### Collaboration search query examples

You can use search queries to search for assets that have user engagement such as ratings, comments, certifications, and more. 

The following table lists some collaboration search query examples that you can try in the search box: 

|**Search query**|**What it does**|
|---|---|
|`all with rating between 1 and 5`|Shows all assets with rating between 1 and 5 stars.|
|`all rated by @<username>`|Shows all assets with a user name or user group in the**Updated By**<br>field.|


Search query examples        135

|**Search query**|**What it does**|
|---|---|
|`all rated within last 3 days`|Shows all assets rated within last 3 days.|
|`all commented by @username`|Shows all assets with comments with a user name or user group in<br>the**Updated By**field.|
|`all commented within last 2 days`|Shows all assets with comments within last 2 days.|
|`all certified by @username`|Shows all certified assets with a user name or user group in the<br>**Updated By**field.|
|`all certified within last 10 days`|Shows all certified assets within the last 10 days.|
|`technical datasets which are not `<br>` certified`|Shows all technical Data Set assets that are not certified.|
|`all having ratings`|Shows all assets with ratings.|
|`all having comments`|Shows all assets with comments.|
|`all not having ratings`|Shows all assets without ratings.|
|`all not having comments`|Shows all assets without comments.|
|`all which are certified`|Shows all technical Data Set assets that are certified.|
|`tables which are certified`|Shows all tables that are certified.|
|`files which are certified`|Shows all files that are certified.|
|`business assets with rating `<br>` between 1 and 5`|Shows all assets with rating between 1 and 5 stars.|
|`Business terms with rating less `<br>` than 3`|Shows all Business Term assets with rating less than 3 stars.|
|`manual data elements having `<br>` comments and commented by`<br>`@<username>`|Shows all manual data elements with comments and commented by a<br>specific user.|
|`Policies with rating equals 5`|Shows all Policy assets with 5 star rating.|
|`Processes with rating greater than `<br>` 3`|Shows all Process assets with 3 stars and above rating.|
|`Business terms commented within `<br>` last 2 days and with stakeholder`<br>`@username`|Shows all Business Term assets with comments within last 2 days<br>and with a user name or user group in the**Updated By**field.|
|`Business terms with rating less `<br>` than 3 and with stakeholder`<br>`@username`|Shows all Business Term assets with rating less than 3 stars and with<br>a user name or user group in the**Updated By**field.|

### Classifications search query examples

You can use search queries to search for classifications or assets that have associations with different classifications. 

The following table lists some search query examples that you can use for searching classifications: 

|**Search query**|**What it does**|
|---|---|
|`document classification with name`<br>`'claim*'`|Shows all document classifications that start with the name` Claim`.|
|`data element classification with `<br>` classification category (Billing)`|Shows all data element classifications belonging to the classification<br>category` Billing`.|
|`data element classification with `<br>` classification category 'Name'`|Shows all data elements classifications belonging to the<br>classification category` Name`.|
|`classification with classification `<br>` category *`|Shows all data element classifications belonging to any classification<br>category.|
|`data element classification with `<br>` classification category in`<br>`('Name', 'List')`|Shows all data element classifications belonging to the classification<br>categories` Name` or` List`.|
|`classification with sensitivity in`<br>`(High, Medium)`|Shows all data classifications that are marked with High and Medium<br>sensitivity.|
|`generated classifications`|Shows all automatically generated classifications in your<br>organization.|
|`generated classifications which `<br>` are not promoted`|Shows all automatically generated classifications that have not been<br>promoted to data element classifications.|
|`generated classifications which `<br>` are promoted`|Shows all automatically generated classifications that have been<br>promoted to data element classifications.|
|`classifications with sensitivity `<br>` high`|Shows all data classifications that are marked with High sensitivity.|
|`classifications with sensitivity `<br>` medium`|Shows all data classifications that are marked with Medium<br>sensitivity.|
|`classifications with sensitivity `<br>` low`|Shows all data classifications that are marked with '-' for Low<br>sensitivity.|
|`assets related to classifications `<br>` with sensitivity high`|Shows all assets that are associated with classifications that are<br>marked with High sensitivity.|


Search query examples        137

### Custom attributes search query examples

You can use search queries based on an asset's custom attributes. 

The following table lists some search query examples that you can use for searching custom attributes: 

|**Search query**|**What it does**|
|---|---|
|`assets which are 'Restricted `<br>` Access'`|Shows assets for which the custom attribute '`Restricted Access`' is<br>marked as` Yes`.|
|`assets which are not`<br>`'Restricted Access'`|Shows assets for which the custom attribute '`Restricted Access`' is<br>marked as` No`.|
|`all with 'Geo location' USA`|Shows all assets for which the custom attribute 'Geo location' value is USA.|
|`assets with <text custom `<br>` attribute> 'Hyderabad'`|Shows all assets that have a text data type custom attributes and the value<br>of the custom attributes is Hyderabad.|
|`assets with <numeric custom `<br>` attribute> greater than 80`|Shows all assets that have a numeric data type custom attributes and the<br>value of the custom attributes is greater than 80.|
|`assets with <numeric custom `<br>` attribute> less than 20`|Shows all assets that have a numeric data type custom attributes and the<br>value of the custom attributes is less than 20.|
|`assets with <numeric custom `<br>` attribute> equals 30`|Shows all assets that have a numeric data type custom attributes and the<br>value of the custom attributes is 30.|
|`assets with <numeric custom `<br>` attribute> between 50 and 60`|Shows all assets that have a numeric data type custom attributes and the<br>value of the custom attributes is in the range of 50 to 60.|
|`assets <date type custom `<br>` attribute> within last 7 hours`|Shows all assets that have a date type custom attributes and the value of<br>the custom attributes is within the last seven hours.|
|`assets <date type custom time `<br>` attribute> within last 4 days`|Shows all assets that have a date type custom attributes and the value of<br>the custom attributes is within the last four days.|
|`assets which are <boolean type `<br>` custom attribute>`|Shows all assets that have a check box type custom attributes.|
|`assets which are not <boolean `<br>` type custom attribute>`|Shows all assets that don't have a check box type custom attributes.|
|`Policy with 'Effective Date'`<br>`before 2024-12-04`|Shows all Policy assets for which the custom attribute 'Effective date' is<br>before the specified date.|
|`Policy with 'Effective Date'`<br>`after 2024-12-04`|Shows all Policy assets for which the custom attribute 'Effective date' is<br>after the specified date.|
|`Policy with 'Effective Date'`<br>`between 2024-10-02 and `<br>` 2024-12-04`|Shows all Policy assets for which the custom attribute 'Effective date' is<br>between the specified dates.|

### Data quality rule occurrences search query examples

You can use search queries to search for data rule occurrences and their associations. 

The following table lists some data quality rule occurrences search query examples: 

|**Search query**|**What it does**|
|---|---|
|`dq rule with threshold result in (good, acceptable,`<br>`'not acceptable') and related to data element `<br>` related to dataset '<DATASET_NAME>'`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**,<br>**Acceptable**, and**Not Acceptable**and are<br>related to the data elements in the specified<br>data set.|
|`dq rule with threshold result 'Good' and related to `<br>` data element related to dataset '<DATASET_NAME>'`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**and are<br>related to data elements belonging to the<br>specified data set.|
|`dq rule with threshold result in (good, acceptable,`<br>`'not acceptable') and related to data element `<br>` related to dataset related to system with name`<br>`<DQ_ORCL_SYSTEM>`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**,<br>**Acceptable**, and**Not Acceptable**and are<br>related to the data set belonging to a system<br>with a specified system name.|
|`dq rule with threshold result 'Good' and related to `<br>` data element related to dataset related to system`<br>`'<SYSTEM_NAME>'`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**and are<br>related to the data set belonging to a system<br>with a specified system name.|
|`dq rule with threshold result in (good, acceptable,`<br>`'not acceptable') and with stakeholder`<br>`'test_data_owner'`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**,<br>**Acceptable**, and**Not Acceptable**and with the<br>assigned stakeholder that is specified.|
|`dq rule with threshold result 'Good' and with `<br>` stakeholder @{{DO}} role @role:test_data_owner`|Shows all data quality rule occurrences with<br>the data quality score as**Good**and with the<br>specified stakeholder belonging to the<br>specified role.|
|`dq rule with threshold result in (good, acceptable,`<br>`'not acceptable') and without stakeholder`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**,<br>**Acceptable**, and**Not Acceptable**and are<br>without any assigned stakeholder.|
|`dq rule with threshold result in (good, acceptable,`<br>`'not acceptable') and with stakeholder`<br>`'test_data_owner' and related to data element `<br>` related to dataset with name <DATASET_NAME>`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**,<br>**Acceptable**, and**Not Acceptable**and with the<br>assigned stakeholder that is specified. These<br>data quality rule occurrences are related to<br>data elements belonging to the specified data<br>set.|
|`dq rule related to data element related to `<br>` technical dataset employees`|Shows all data quality rule occurrences that are<br>related to a specified technical data set.|


Search query examples        139

|**Search query**|**What it does**|
|---|---|
|`dq rule with threshold result in (good, acceptable,`<br>`'not acceptable') and related to data element `<br>` related to business term which are cde`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**,<br>**Acceptable**, and**Not Acceptable**and are<br>related to data elements belonging to specific<br>business terms associated with critical data<br>element attributes.|
|`dq rule with threshold result in (good, acceptable,`<br>`'not acceptable') and related to data element `<br>` related to datasets related to System related to `<br>` Policy '<POLICY_NAME>'`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**,<br>**Acceptable**, and**Not Acceptable**and are<br>related to the data set belonging to a system<br>with a specified policy name.|
|`dq rule with threshold result in (good, acceptable,`<br>`'not acceptable') and related to data element `<br>` related to datasets related to System related to `<br>` Policy`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**,<br>**Acceptable**, and**Not Acceptable**and are<br>related to the data set belonging to a system<br>with any policy name.|
|`dq rule with threshold result in (good, acceptable,`<br>`'not acceptable') and related to data element `<br>` related to datasets related to System related to `<br>` Process`|Shows all data quality rule occurrences with<br>the data quality score status as**Good**,<br>**Acceptable**, and**Not Acceptable**and are<br>related to the data set belonging to a system<br>with a process.|
|`dq rule related to data element related to datasets `<br>` related to System related to Process`<br>`'DQ_PAM_AZRSQL_PROP_PROCESS_001_10'`|Shows all data quality rule occurrences with or<br>without data quality score status and are<br>related to the data set belonging to a system<br>with a specified process.|
|`dq rule related to data element related to datasets `<br>` related to System related to Process`<br>`('<PROCESS_NAME>' , '<PROCESS_NAME_01>')`|Shows all data quality rule occurrences with or<br>without data quality score status and are<br>related to the data set belonging to a system<br>with two specified processes.|
|`dq rule related to data element related to dataset `<br>` related to system with stakeholder`<br>`'test_data_owner'`|Shows all data quality rule occurrences with or<br>without data quality score status and are<br>related to a data set belonging to a system with<br>the assigned stakeholder that is specified.|

### Glossary assets associations with data classifications search query examples

You can use search queries to search for Glossary assets that are associated with data classifications. 

The following table lists some search query examples for the associations of the Glossary assets with the data element and data entity classifications: 

|**Search query**|**What it does**|
|---|---|
|`data classifications related to `<br>` glossary`|Shows all the data classifications that are related to the glossary<br>assets.|
|`data classifications related to `<br>` glossary 'Country'`|Shows all data classifications that are related to the glossary asset<br>named` Country`.|
|`data elements related to glossary`<br>`'Sales' and data element `<br>` classification 'Email Address'`|Shows all the data elements that are related to the glossary assets<br>with the name` Sales` and the data element classifications with the<br>name` Email Address`.|
|`technical datasets related to `<br>` glossary 'Sales' and data entity `<br>` classification 'PCI'`|Shows the technical data sets that are related to the glossary assets<br>with the name` Sales ` and data entity classifications with the name<br>` PCI`.|
|`data elements related to glossary`<br>`'Customer email' and data element `<br>` classification 'Email Address'`|Shows all the data elements that are related to the glossary assets<br>with the name` Customer email` and data element classifications<br>with the name` Email Address`.|
|`glossary related to data elements`|Shows all the glossary assets that are related to the data elements.|
|`glossary related to data `<br>` classification`|Shows all the glossary assets that are related to the data<br>classifications.|
|`glossary related to data `<br>` classifications ‘Email Address’`|Shows all the glossary assets that are related to the data<br>classification named` Email Address`.|
|`glossary not related to any data `<br>` classifications`|Shows all the glossary assets that are not related to any data<br>classifications.|


## Class types for business assets

When you create a business asset, you must enter a value for the `core.classType` parameter. 

The following table lists the class types for business assets that you can enter when creating business assets via API. 

|**Business Asset**|**Class Type**|
|---|---|
|Business term|`com.infa.ccgf.models.governance.BusinessTerm`|
|Metric|`com.infa.ccgf.models.governance.Metric`|


Class types for business assets        141

|**Business Asset**|**Class Type**|
|---|---|
|System|`com.infa.ccgf.models.governance.System`|
|Policy|`com.infa.ccgf.models.governance.Policy`|
|Domain|`com.infa.ccgf.models.governance.Domain`|
|Subdomain|`com.infa.ccgf.models.governance.SubDomain`|
|Process|`com.infa.ccgf.models.governance.Process`|
|Data Set|`com.infa.ccgf.models.governance.DataSet`|
|AI Model|`com.infa.ccgf.models.AIModel.AIModel`|
|AI System|`com.infa.ccgf.models.AIModel.AISystem`|
|Business Area|`com.infa.ccgf.models.governance.BusinessArea`|
|Geography|`com.infa.ccgf.models.governance.Geography`|
|Legal Entity|`com.infa.ccgf.models.governance.LegalEntity`|
|Project|`com.infa.ccgf.models.governance.Project`|
|Regulation|`com.infa.ccgf.models.governance.Regulation`|


## File size limits for API payload fields

View the file size limits for API payload fields. 

The following table describes the storage that is used for the values that you enter in your API payload fields. 

|**Field Category**|**Type**|**Limit**|
|---|---|---|
|Rich-text fields (for example,<br>`core.description`)|String|Up to approximately 1 MB per value.|
|Viewable fields|String|Up to approximately 1 MB per value.|
|Searchable strings|String|Up to approximately 32 KB per value.|
|Keyword fields (for example,`core.name`,<br>identifiers)|String|Upto 256 characters per value.|
|Non-string attributes (boolean, integer, decimal,<br>date)|String|Not based on length. Use standard data type format for<br>the modeled attribute.|
