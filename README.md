# sample-akka-with-BalancingPool

akka で BalancingPool を使用するテスト

- 共有メッセージボックスになるはず
- Worker が idle になると処理する

## Build and run

```bash
$ git clone [this repository]
$ sbt run
```

## Request and test

```bash
$ time curl http://localhost/delay/2000
```
