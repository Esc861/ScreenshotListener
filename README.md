# ScreenshotListener

## What is it?

An Android library to detect when a user has taken a screenshot of your app.

## Usage

ScreenshotListener is in the jCenter Maven repository

In your `build.gradle`:

```gradle
dependencies {
	compile 'com.esc861.screenshotlistener:screenshotlistener:1.0.3'
}
```

In an activity (or any class) that wants to listen for screenshots

```java
ScreenshotListener listener;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	listener = new ScreenshotListener() {
		@Override
		public void onScreenshotDetected(String path) {
			//Screenshot detected
		}
	};
}

@Override
protected void onResume() {
	super.onResume();
	listener.startListening();
}

@Override
protected void onPause() {
	super.onPause();
	listener.stopListening();
}
```

## Author

- [Eric Clayton](mailto:esc861@gmail.com)