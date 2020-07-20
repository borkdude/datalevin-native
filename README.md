# datalevin-native

This is an experiment to compile [datalevin](https://github.com/juji-io/datalevin) with GraalVM native-image.

## Build

To compile this project, point `GRAALVM_HOME` at your GraalVM distribution and
then run `script/compile`.

The build requires
[`clojure`](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools)
to be installed.

## Issues

Currently blocked on:


``` shell
$ ./datalevin-native
Exception in thread "main" java.lang.UnsatisfiedLinkError: com.kenai.jffi.Foreign.invokeN1O1(JJJLjava/lang/Object;III)J [symbol: Java_com_kenai_jffi_Foreign_invokeN1O1 or Java_com_kenai_jffi_Foreign_invokeN1O1__JJJLjava_lang_Object_2III]
	at com.oracle.svm.jni.access.JNINativeLinkage.getOrFindEntryPoint(JNINativeLinkage.java:145)
	at com.oracle.svm.jni.JNIGeneratedMethodSupport.nativeCallAddress(JNIGeneratedMethodSupport.java:57)
	at com.kenai.jffi.Foreign.invokeN1O1(Foreign.java)
	at com.kenai.jffi.Invoker.invokeN1(Invoker.java:997)
	at org.lmdbjava.Library$Lmdb$jnr$ffi$0.mdb_env_create(Unknown Source)
	at org.lmdbjava.Env$Builder.open(Env.java:486)
	at org.lmdbjava.Env$Builder.open(Env.java:512)
	at datalevin.lmdb$open_lmdb.invokeStatic(lmdb.clj:689)
	at datalevin.lmdb$open_lmdb.invoke(lmdb.clj:678)
	at datalevin.lmdb$open_lmdb.invokeStatic(lmdb.clj:682)
	at datalevin.storage$open.invokeStatic(storage.clj:386)
	at datalevin.db$empty_db.invokeStatic(db.cljc:213)
	at datalevin.core$create_conn.invokeStatic(core.cljc:387)
	at borkdude.datalevin_native.main$_main.invokeStatic(main.clj:11)
	at borkdude.datalevin_native.main$_main.doInvoke(main.clj:11)
	at clojure.lang.RestFn.invoke(RestFn.java:397)
	at clojure.lang.AFn.applyToHelper(AFn.java:152)
	at clojure.lang.RestFn.applyTo(RestFn.java:132)
	at borkdude.datalevin_native.main.main(Unknown Source)
```
