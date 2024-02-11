import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITradeChannel } from '../trade-channel.model';
import { TradeChannelService } from '../service/trade-channel.service';

export const tradeChannelResolve = (route: ActivatedRouteSnapshot): Observable<null | ITradeChannel> => {
  const id = route.params['id'];
  if (id) {
    return inject(TradeChannelService)
      .find(id)
      .pipe(
        mergeMap((tradeChannel: HttpResponse<ITradeChannel>) => {
          if (tradeChannel.body) {
            return of(tradeChannel.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default tradeChannelResolve;
