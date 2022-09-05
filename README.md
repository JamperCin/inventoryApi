# Inventory Management Api
- Inventory Management Api is a library or module that can be used to access the inventory management module for projects like NMB etc.

- The purpose of this document is to help understand details needed to develop the APIs required to integrate to UT5 system. Each API has URL, URI, request and response parameters described and a sample is provided as well for reference. The document encompasses the following:
> - Initialising Library with the necessary protocols like : Host, Port, etc.
> - Making Api Request
> - Specifying the Response or Result class expected from the Api request
> - Listening to the Response or Result through the use of Callbacks

## Getting Started
- Let us start getting our hands dirty by looking at few configurations before actual library implementation.

### Configurations Needed:
- You need to do the following configurations in the build.gradle file.

### Implementation Of Library:
- Now that we are done with the configurations, let us go straight into library usage.

#### 1. Instantiate the api
- To create a new instance of the library, do the following:

```InventoryApi inventoryApi = new InventoryApi(); ```

#### 2. Set Base Url by specifying the Host, Port, Token, Http/Https, rpc
- Set the Host of the base URL eg: ```10.16.8.1``` like below:

```
 inventoryApi = new InventoryApi()
                .setHost("10.16.8.1")
```
- Set the port of the base URL eg: ```8080``` like below:

```
inventoryApi = new InventoryApi()
                .setHost("10.16.8.1")
                .setPort("8080")
```

- Indicate whether the base URL will be Http or Https by specifying a boolean value like below:

```
inventoryApi = new InventoryApi()
               .setHost("10.16.8.1")
               .setPort("8080")
               .setIsHttps(false)
```

- Set Token that will be set to the header ```x-xsrf-token``` like example below:

```
inventoryApi = new InventoryApi()
                .setHost("10.16.8.1")
                .setPort("8080")
                .setIsHttps(false)
                .setToken("wesdfg_gvjkk63535353gjk6gfg86hkk")
```
- Every api method call has  ```rpc/``` at the end of the base URL. An example of such base URL will look like: ```https://10.16.8.1:8080/rpc/```.  You can change it to anything by specifying the rpc key word using ```setRpc("login")```   on the same instantiation. The default is ```rpc``` if nothing is specified. An example below:

```
inventoryApi = new InventoryApi()
                .setHost("10.16.8.1")
                .setPort("8080")
                .setIsHttps(false)
                .setToken("wesdfg_gvjkk63535353gjk6gfg86hkk")
                .setRpc("login")  --> This is optional.
```

- The last thing to specify at the end of instantiation is to call the ```build()``` method to complete the instantiation of the library. Sample below:

```
 inventoryApi = new InventoryApi()
                .setHost("10.16.8.1")
                .setPort("8080")
                .setIsHttps(false)
                .setToken("wesdfg_gvjkk63535353gjk6gfg86hkk")
                .setRpc("login") --> This is optional
                .build();
```
#### 4. Listening to your API responses from the library
- A typical response from the library will look like below JSON as specified in the documentation :

```
Successful response 
{
   "id":"xxxx",
   "jsonrpc":"2.0",
   "result":{
      "…"
   }
}
```

```
Error response 
{
   "id":"xxxx",
   "jsonrpc":"2.0",
   "error":{
      "code":-1,
      "message":"xxxxx",
      "errorPrint":"xxxxxx",
      "type":"xxxx"
   }
}
```

- There are two ways to receive your api responses from the Library.
> 1. By specifying result class to receive the ```result``` jsonObject from the response or
> 2. Passing ```null``` as class to the method. This makes the ```result``` object come as a ```Map<String, Object>```

See sample code below:
- Specifying a result class, eg: Passing ```ResultModel.class``` as the class to receive the response
```
inventoryApi.fetchRequisitions(requestBody,ResultModel.class, new IRequestCallback<ResponseModel<ResultModel>>() {
            @Override
            public void onFailure(String error) {
               //handle error message
            }

            @Override
            public void onSuccess(ResponseModel<ResponseModel<ResultModel>> response) {
               //handle results from the response
                ResultModel resultModel = (ResultModel)response.getResults();
                requisitionsLiveData.setValue(resultModel.getRequisitions());
            }
        } );
```

- Not specifying a result class, rather expecting result as a ```Map``` from response:
```
   inventoryApi.fetchRequisitions(requestBody, null, new IRequestCallback<ResponseModel<Map<String, Object>>>() {

            @Override
            public void onSuccess(ResponseModel<ResponseModel<Map<String, Object>>> response) {
                Map<String, Object> resultModel = (Map<String, Object>) response.getResults();

            }

            @Override
            public void onFailure(String error) {
              //handle the error message
            }
        });
```

#### 3. Usage of the various methods of the library
- Now let us dive into the usage of the various methods or api calls from the library

##### 3.1 Request Categories and Subcategories
- This service is used for requesting the various inventory categories and subcategories already configured in the system.
- parameters :
> - @param resultClass:  The Generic class to receive response
> - @param callback:    The callBack to listen on for response from the api

```
inventoryApi.getCategories();

```

##### 3.2  Fetch inventory request
- This service is used to fetch the requisitions made. It returns a list of the requisitions made in the form of paginations.
- parameters :
> - @param params :    The request body
> - @param resultClass:  The Generic class to receive response
> - @param callback:    The callBack to listen on for response from the api

```
inventoryApi.fetchRequisitions();

```

##### 3.3   Submit Inventory request
- This service is used to add an inventory requisition
- parameters :
> - @param params :    The request body
> - @param resultClass:  The Generic class to receive response
> - @param callback:    The callBack to listen on for response from the api

```
inventoryApi.addRequisition();

```

##### 3.4   Fetch Allocated Items
- This service is used to fetch a list of allocated requisitions.
- parameters :
> - @param params :    The request body
> - @param resultClass:  The Generic class to receive response
> - @param callback:    The callBack to listen on for response from the api

```
inventoryApi.getAllocatedItems();

```

##### 3.5   Get An Allocated Item
- This service is used to get an allocated requisition by specifying the allocationId in the params
- parameters :
> - @param params :    The request body
> - @param resultClass:  The Generic class to receive response
> - @param callback:    The callBack to listen on for response from the api

```
inventoryApi.getAnAllocatedItem();

```

##### 3.6  Accept Allocated Item
- This service is used to accept allocated requisition
- parameters :
> - @param params :    The request body
> - @param resultClass:  The Generic class to receive response
> - @param callback:    The callBack to listen on for response from the api

```
inventoryApi.acceptAllocatedItem();

```

##### 3.7   Reject Allocated Item
- This service is used to reject allocated requisition
- parameters :
> - @param params :    The request body
> - @param resultClass:  The Generic class to receive response
> - @param callback:    The callBack to listen on for response from the api

```
inventoryApi.rejectAllocatedItem();

```

