import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAnchorTraderPartner } from '../anchor-trader-partner.model';
import { AnchorTraderPartnerService } from '../service/anchor-trader-partner.service';

export const anchorTraderPartnerResolve = (route: ActivatedRouteSnapshot): Observable<null | IAnchorTraderPartner> => {
  const id = route.params['id'];
  if (id) {
    return inject(AnchorTraderPartnerService)
      .find(id)
      .pipe(
        mergeMap((anchorTraderPartner: HttpResponse<IAnchorTraderPartner>) => {
          if (anchorTraderPartner.body) {
            return of(anchorTraderPartner.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default anchorTraderPartnerResolve;
