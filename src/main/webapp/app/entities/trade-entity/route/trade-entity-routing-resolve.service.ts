import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITradeEntity } from '../trade-entity.model';
import { TradeEntityService } from '../service/trade-entity.service';

export const tradeEntityResolve = (route: ActivatedRouteSnapshot): Observable<null | ITradeEntity> => {
  const id = route.params['id'];
  if (id) {
    return inject(TradeEntityService)
      .find(id)
      .pipe(
        mergeMap((tradeEntity: HttpResponse<ITradeEntity>) => {
          if (tradeEntity.body) {
            return of(tradeEntity.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default tradeEntityResolve;
