import { WebPlugin } from '@capacitor/core';

import type { OrderFetchingServicePlugin } from './definitions';

export class OrderFetchingServiceWeb
  extends WebPlugin
  implements OrderFetchingServicePlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
