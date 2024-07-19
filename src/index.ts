import { registerPlugin } from '@capacitor/core';

import type { OrderFetchingServicePlugin } from './definitions';

const OrderFetchingService = registerPlugin<OrderFetchingServicePlugin>(
  'OrderFetchingService',
  {
    web: () => import('./web').then(m => new m.OrderFetchingServiceWeb()),
  },
);

export * from './definitions';
export { OrderFetchingService };
