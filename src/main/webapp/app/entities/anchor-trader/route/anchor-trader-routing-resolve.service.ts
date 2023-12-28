import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAnchorTrader } from '../anchor-trader.model';
import { AnchorTraderService } from '../service/anchor-trader.service';

export const anchorTraderResolve = (route: ActivatedRouteSnapshot): Observable<null | IAnchorTrader> => {
  const id = route.params['id'];
  if (id) {
    return inject(AnchorTraderService)
      .find(id)
      .pipe(
        mergeMap((anchorTrader: HttpResponse<IAnchorTrader>) => {
          if (anchorTrader.body) {
            return of(anchorTrader.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default anchorTraderResolve;
