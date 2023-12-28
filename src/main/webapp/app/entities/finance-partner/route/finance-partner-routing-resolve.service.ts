import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFinancePartner } from '../finance-partner.model';
import { FinancePartnerService } from '../service/finance-partner.service';

export const financePartnerResolve = (route: ActivatedRouteSnapshot): Observable<null | IFinancePartner> => {
  const id = route.params['id'];
  if (id) {
    return inject(FinancePartnerService)
      .find(id)
      .pipe(
        mergeMap((financePartner: HttpResponse<IFinancePartner>) => {
          if (financePartner.body) {
            return of(financePartner.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default financePartnerResolve;
