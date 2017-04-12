About SoundCloud basic API demo - Track request
-----------------------------------------------------------------

1) Setup: 

	- Sign into soundcloud API and get a key
	- Go to SoundCloudRetrofit.java and paste your key at:
		public static final String API_KEY = "YOUR_API_KEY_HERE";

	

2) The project is organized as follow:

	* activites
	* adapters
	* fragments
	* mvp.model
	* mvp.presenter
	* mvp.view
	* network.callback
	* network.contract
	* network.request
	* utils

4) Unit test can be found at /app/src/test/java/mvp/presenter


5) Network calls; at first I wanted to use RxJava 2.0 and Retrofit. But still not super familiar with RxJava 2.0. For that reason I decided to implement a WeakRef Callback for retrofit get request:
	
	* The idea is that we won't notify onSuccessfulResponse and onFailedResponse in case the presenter is no longer availble.

