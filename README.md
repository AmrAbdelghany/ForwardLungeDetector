#ForwardLungeDetector
ForwardLungeDetector is an Android app designed to detect the user's pose, calculate knee angles, monitor forward lunge progress, and count repetitions for both left and right legs. It leverages MediaPipe for pose detection and analysis.

##Project Structure

###CameraFragment.kt
CameraFragment.kt serves as the central control point for the app. It manages the camera setup, initializes the MediaPipe model for pose detection, and overlays the detected pose skeleton onto the camera stream in real-time.

###poseDetector.kt
poseDetector.kt is responsible for handling the MediaPipe model. It encompasses tasks such as building the model, configuring its options, and managing relevant variables.

###overlay.kt
overlay.kt focuses on the visualization aspect of the app. It's in charge of drawing the pose skeleton on top of the camera stream and updating it dynamically.

###permissions.kt
permissions.kt is dedicated to managing app permissions, particularly the camera permission. It requests this permission if not already granted.

###ForwardlungeDetector.kt
ForwardlungeDetector.kt contains the core logic for forward lunge detection and repetition counting. It extracts the detected pose data from the model, calculates knee angles (both left and right), determines if a forward lunge is performed, and displays the progress of a single repetition using the progress bar at the screen's bottom. Additionally, it keeps a count of repetitions for both the left and right legs, which is presented at the screen's bottom.

##File Locations
All project files are organized within the following directory structure:
app/src/main/java/com/mediapipe_posedetection_forwardlunge

This structure simplifies navigation and locates key components within the project.
