import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISettlement } from '../settlement.model';
import { SettlementService } from '../service/settlement.service';

export const settlementResolve = (route: ActivatedRouteSnapshot): Observable<null | ISettlement> => {
  const id = route.params['id'];
  if (id) {
    return inject(SettlementService)
      .find(id)
      .pipe(
        mergeMap((settlement: HttpResponse<ISettlement>) => {
          if (settlement.body) {
            return of(settlement.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default settlementResolve;
