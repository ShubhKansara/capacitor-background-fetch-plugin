export interface OrderFetchingServicePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
