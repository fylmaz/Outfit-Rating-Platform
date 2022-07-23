# Outfit-Rating-Platform

## Used Principles
- Solid
- MVC
- Swing
- Observer Design Pattern

In this platform, the users will rate and comment on different outfits and create collections up to their interest or for their upcoming events. For example, a woman about to marry could create a collection namely “My Wedding” which consists of a wedding dress, a wedding shoe, a bride crown etc. Also, the users will see other users’ collections to see different products or combines.

There will be also a statistics page which will show the followings:
- The most liked outfit
- The most disliked outfit
- The most followed user

When a user performs a like/dislike or commenting operation the JSON file will be updated.
Furthermore, if any of these operations affect any of the user’s collections or the statistics page, these
changes will be applied on these as well.

The user information is stored in an XML file with user name, password, following users, follower users
and collection content. The collection will have a name and the product ids which are added to the
corresponding collection. When a user performs follow/unfollow operation, or an addition/removal
operation to one of his/her collections, the XML file will be updated.

Both the JSON and XML files will be loaded first when the program starts.

User Name:        Password:
- fatih1           - 123456
- fatih2           - 123456
- fatih3           - 123456
- fatihyilmaz      - 123456
- fatih5           - 123456

###### Login View

![image](https://user-images.githubusercontent.com/73228502/180604595-fa75118e-fcf3-4f20-a3dd-bb58fc602144.png)

###### User View

![image](https://user-images.githubusercontent.com/73228502/180605548-63186968-3ca8-4512-804c-84c2205aee92.png)

###### Outfit View

![image](https://user-images.githubusercontent.com/73228502/180606945-0c7af299-2d0c-4693-9c1b-425465e8e3e6.png)

###### Comment View

![image](https://user-images.githubusercontent.com/73228502/180606958-5476a499-94ff-4b66-bb2a-3ee1f5189b01.png)

###### Statistics View

![image](https://user-images.githubusercontent.com/73228502/180606975-761df6ff-3eff-4d9d-95a7-cc0ef0af30f6.png)


NOTE: If you want to terminate the app, you have to logout and close the login view.

NOTE: If you terminate and run again the app, it will read data from the files to use updated data in execution.



