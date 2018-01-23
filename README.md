# ResourceAdapter
Resource adapter

## Functions
SOAP
wsdl: http://resourceadapter.herokuapp.com/?wsdl
- **void addArtist(Preference p)**  =>  calls the Preference Dao to act an insert request
- **void removeArtist(Preference p)**  =>  calls the Preference Dao to act a delete request
- **List\<Artist\> getRecommendedSimilarArtists(String id)**  =>  recommends similar artists in according to the ones preferred by the user with a certain id
- **List\<Event\> recommendEvents(String id)**  =>  recommends a list of events that can be useful for the user with a specific id
- **String getMotivation()**  =>  get a random quote from
- **List\<Preference\> getProfilePreferences(String p)**  =>  obtain the list of preferences by reading the Preference table
- **Integer getArtistExternalId(String artist)**  =>  get the id used by Songkick specifying the artist name
- **void evaluateArtistRecommendation(String artistId, String artistName, String id, Integer rate)**  =>  evaluate the recommendations of the artists
- **List\<Evaluation\> getEvaluationsByUser(String id)**  =>  calls a filter function in the Evaluation Dao
- **Person getPersonByUser(String id)**  =>  get the person information given the identifier id
- **void register(String name)**  =>  register a new user with a given name
- **List\<Person\> getAllUsers()**  =>  get the list of all the registered users


# Manual SOAP requests (Postman)

List<Person> getAllUsers
(request)
  
```
<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
    <soap:Body xmlns:m="http://soap.resourceadapter.introsde/">
        <m:getAllUsers>

        </m:getAllUsers>
    </soap:Body>
</soap:Envelope>
```

(response)
```
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Body>
        <ns2:getAllUsersResponse xmlns:ns2="http://soap.resourceadapter.introsde/">
            <getAllUsers>
                <name>Marco</name>
                <userId>1</userId>
            </getAllUsers>
            <getAllUsers>
                <name>Piero</name>
                <userId>2</userId>
            </getAllUsers>
            <getAllUsers>
                <name>Sofia</name>
                <userId>6</userId>
            </getAllUsers>
            <getAllUsers>
                <name>Giulia</name>
                <userId>7</userId>
            </getAllUsers>
           .....
        </ns2:getAllUsersResponse>
    </S:Body>
</S:Envelope>
```
