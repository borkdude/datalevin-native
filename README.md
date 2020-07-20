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
Detailed message:
Error: unbalanced monitors: mismatch at monitorexit, 62|Invoke#Numbers.num != 238|Invoke#Numbers.num
Call path from entry point to datalevin.storage.Store.insert(Object):
	at datalevin.storage.Store.insert(storage.clj:181)
	at datalevin.storage$fn__4374$G__4258__4377.invoke(storage.clj:136)
	at clojure.core.proxy$clojure.lang.APersistentMap$ff19274a.applyTo(Unknown Source)
	at borkdude.datalevin_native.main.main(Unknown Source)
	at com.oracle.svm.core.JavaMainWrapper.runCore(JavaMainWrapper.java:149)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:184)
	at com.oracle.svm.core.code.IsolateEnterStub.JavaMainWrapper_run_5087f5482cc9a6abc971913ece43acb471d2631b(generated:0)
Original exception that caused the problem: org.graalvm.compiler.code.SourceStackTraceBailoutException$1: unbalanced monitors: mismatch at monitorexit, 62|Invoke#Numbers.num != 238|Invoke#Numbers.num
	at datalevin.storage.Store.insert(storage.clj:228)
```

The `locking` issue with GraalVM is supposed to be fixed in CLJ-1472, I don't
know why it's propping up here. Datalevin is not AOT-compiled so this can't be the issue.
