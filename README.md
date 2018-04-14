# Codelib 

[![Gitter](https://badges.gitter.im/Project-ARTist/CodeLib.svg)](https://gitter.im/project-artist/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=body_badge)

The CodeLib project provides a template for Java libraries that are injected into targets (Android apps & system components) as a part of the [ARTist](https://github.com/Project-ARTist/ARTist) toolchain. The basic idea is that you can use this as a skeleton code to create a CodeLib for your new ARTist module. 

For more information about the ARTist ecosystem, see the dedicated section below. 

It is recommended to place as much of the business logic of your module as possible in the CodeLib and just use ARTist to connect and intertwine this functionality with a target. Consider a module that wants to implement an Inline Reference Monitor for fine-grained permission enforcement. In this case, the whole monitor implementation resides in the CodeLib while your ARTist module ensures your monitor API is called from the target (e.g., by injecting calls to the monitor API right before accessing a privileged resource). This way, you can do the heavy lifting in Java and restrict your ARTist glue code to a minimum. 


## Adapt

Currently, the CodeLib is a single class that implements some required boilerplate functionality.

For technical reasons, ARTist obtains a copy of your CodeLib through a static field and invokes methods on this very instance, hence the singleton implementation. Most module developers should stick to this and leave the singleton code untouched. Just implement own methods and register them in the corresponding native codelib files in your module. 

Do not try to add native code or anything that requires manifest changes since currently this is not supported. Only the dex file of the resulting APK is used in the ARTist toolchain. If, however, this keeps you from implementing your amazing module idea, let us know at [Gitter](https://gitter.im/project-artist/Lobby) so we can discuss how to add this. 

## Build

Since the CodeLib is a regular Android project, you can build it with gradle:

```./gradlew build```

In the current pre-beta version, you need to place the compiled CodeLib apk in the correct asset folder of [ArtistGui](https://github.com/Project-ARTist/ArtistGui) and select it in the app settings on the device. However, upon reaching beta state ARTist modules will bundle the native code and CodeLib together to get rid of this admittedly weird workflow we have right now. 

# ARTist - The Android Runtime Instrumentation and Security Toolkit

ARTist is a flexible open source instrumentation framework for Android's apps and Java middleware. It is based on the Android Runtime’s (ART) compiler and modifies code during on-device compilation. In contrast to existing instrumentation frameworks, it preserves the application's original signature and operates on the instruction level. 

ARTist can be deployed in two different ways: First, as a regular application using our [ArtistGui](https://github.com/Project-ARTist/ArtistGui) project (this repository) that allows for non-invasive app instrumentation on rooted devices, or second, as a system compiler for custom ROMs where it can additionally instrument the system server (Package Manager Service, Activity Manager Service, ...) and the Android framework classes (```boot.oat```). It supports Android versions after (and including) Marshmallow 6.0. 

For detailed tutorials and more in-depth information on the ARTist ecosystem, have a look at our [official documentation](https://artist.cispa.saarland) and join our [Gitter chat](https://gitter.im/project-artist/Lobby).

## Upcoming Beta Release

We are about to enter the beta phase soon, which will bring a lot of changes to the whole ARTist ecosystem, including a dedicated ARTist SDK for simplified Module development, a semantic versioning-inspired release and versioning scheme, an improved and updated version of our online documentation, great new Modules, and a lot more improvements. However, in particular during the transition phase, some information like the one in the repositories' README.md files and the documentation at [https://artist.cispa.saarland](https://artist.cispa.saarland) might be slightly out of sync. We apologize for the inconvenience and happily take feedback at [Gitter](https://gitter.im/project-artist/Lobby). To keep up with the current progress, keep an eye on the beta milestones of the Project: ARTist repositories and check for new blog posts at [https://artist.cispa.saarland](https://artist.cispa.saarland) . 

## Contribution

We hope to create an active community of developers, researchers and users around Project ARTist and hence are happy about contributions and feedback of any kind. There are plenty of ways to get involved and help the project, such as testing and writing Modules, providing feedback on which functionality is key or missing, reporting bugs and other issues, or in general talk about your experiences. The team is actively monitoring [Gitter](https://gitter.im/project-artist/) and of course the repositories, and we are happy to get in touch and discuss. We do not have a full-fledged contribution guide, yet, but it will follow soon (see beta announcement above). 

## Academia

ARTist is based on a paper called **ARTist - The Android Runtime Instrumentation and Security Toolkit**, published at the 2nd IEEE European Symposium on Security and Privacy (EuroS&P'17). The full paper is available [here](https://artist.cispa.saarland/res/papers/ARTist.pdf). If you are citing ARTist in your research, please use the following bibliography entry:

```
@inproceedings{artist,
  title={ARTist: The Android runtime instrumentation and security toolkit},
  author={Backes, Michael and Bugiel, Sven and Schranz, Oliver and von Styp-Rekowsky, Philipp and Weisgerber, Sebastian},
  booktitle={2017 IEEE European Symposium on Security and Privacy (EuroS\&P)},
  pages={481--495},
  year={2017},
  organization={IEEE}
}
```

There is a follow-up paper where we utilized ARTist to cut out advertisement libraries from third-party applications, move the library to a dedicated app (own security principal) and reconnect both using a custom Binder IPC protocol, all while preserving visual fidelity by displaying the remote advertisements as floating views on top of the now ad-cleaned application. The full paper **The ART of App Compartmentalization: Compiler-based Library Privilege Separation on Stock Android**, as it was published at the 2017 ACM SIGSAC Conference on Computer and Communications Security (CCS'17), is available [here](https://artist.cispa.saarland/res/papers/CompARTist.pdf).


