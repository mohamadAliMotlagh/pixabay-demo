
### KMM android and iOS app
The project is developed using Kotlin Multiplatform Mobile and incorporates essential libraries, including:

- **Jetpack Compose**
- **SwiftUI(for iOS app)**
- **Koin(DI)**
- **Coroutines**
- **Coil**
- **Ktor**
- **SQLDelight**
### Description: 

- **UI Test**: this app has ui tests on search screen
- **Unit Test:** developed with **TDD** and the project utilizes: 
  - **JUnit**
  - **Mockk** libraries
    
- **Configuration Handling**: The app effectively manages configuration changes, ensuring a seamless user experience across different device orientations and screen sizes.
- **UI Optimization:** Employing the Layout Inspector, the app meticulously fine-tunes its user interface to minimize unnecessary recomposition.
- **Keyboard Management:** The app intelligently prevents keyboard overlays, ensuring that on-screen content remains fully visible and accessible during user interactions.

### Project Structure: 
the data and domain layer are shared and placed in shared module.[see the code](https://github.com/mohamadAliMotlagh/pixabay-demo/tree/main/shared/src/commonMain)

<img width="340" alt="Screenshot 2024-03-25 at 16 01 46" src="https://github.com/mohamadAliMotlagh/pixabay-demo/assets/4753472/c2d73b15-d75c-4752-9422-e74b7938c339">


the presentation layer is on androidApp module.
[see the code](https://github.com/mohamadAliMotlagh/pixabay-demo/tree/main/androidApp/src/main/java/com/app/pixabay/android)


<img width="337" alt="Screenshot 2024-03-25 at 16 04 20" src="https://github.com/mohamadAliMotlagh/pixabay-demo/assets/4753472/6451bc75-4fba-4aef-a55f-4a9ae0b8806b">

### Android APP: 
![Screenshot_1711341698](https://github.com/mohamadAliMotlagh/pixabay-demo/assets/4753472/c65ed129-36b1-4c1f-b8b3-3b3f2fb384c4)  ![Screenshot_1711341729](https://github.com/mohamadAliMotlagh/pixabay-demo/assets/4753472/58738db2-2ba7-4ec4-9212-e298ad6cd893)

### Pixaby API:
for access to to the api you need an ApiKey from pixaby. you can find it in here:
[get API key](https://pixabay.com/service/about/api/)

### iOS APP: 


<img width="200" alt="Screenshot 2024-02-16 at 01 08 13" src="https://github.com/mohamadAliMotlagh/pixabay-demo/assets/4753472/15e8113e-f335-4b11-b9a8-566ba0ea3f0c">


### APK File:
you can download the apk file from here:
[Download File](https://drive.google.com/file/d/1_ktncB2ASwOJxNrjdqapchr-8n4LmZO9/view?usp=sharing)

