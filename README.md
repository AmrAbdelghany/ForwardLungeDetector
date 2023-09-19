# ForwardLungeDetector
An app that detects the user's pose, calculates the knee angle to monitor forward lunge progress and counts reps for both left and right legs.

This app uses MediaPipe for pose detection and then uses the leg landmarks to compute the knee angle to detect the progress of a forward lunge and count the correctly executed rep.

The CameraFragment.kt controls the full flow of the app including starting the camera, initializing the Mediapipe model for pose detection, and drawing the detected pose skeleton on top of the user's body.

The poseDetector class is responsible for building the model, setting its options, and handling the model's variables.

The overlay class is responsible for drawing the skeleton on top of the camera stream and updating it in real time

The permissions class is handling the required permissions for the app to run (Camera Permission), It requests the permission if does not have it already.


The Forward Lunge detection logic and rep counter are implemented in ForwardlungeDetector.kt, It gets the detected pose from the model and computes the angles for each knee (left and right), detects if a forward lunge is executed, and shows the progress of a single rep using the progress bar at the bottom of the screen. It also counts reps for the left and right legs that are shown at the bottom of the screen as well.


