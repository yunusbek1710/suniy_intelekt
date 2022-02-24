package suniyIntelekt.config


case class AppConfig(
  dbConfig: DBConfig,
  logConfig: LogConfig,
  serverConfig: HttpServerConfig,
  redisConfig: RedisConfig
)