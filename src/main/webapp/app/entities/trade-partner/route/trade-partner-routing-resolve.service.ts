import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITradePartner } from '../trade-partner.model';
import { TradePartnerService } from '../service/trade-partner.service';

export const tradePartnerResolve = (route: ActivatedRouteSnapshot): Observable<null | ITradePartner> => {
  const id = route.params['id'];
  if (id) {
    return inject(TradePartnerService)
      .find(id)
      .pipe(
        mergeMap((tradePartner: HttpResponse<ITradePartner>) => {
          if (tradePartner.body) {
            return of(tradePartner.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default tradePartnerResolve;
