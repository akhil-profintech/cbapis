import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFinanceRequest } from '../finance-request.model';
import { FinanceRequestService } from '../service/finance-request.service';

export const financeRequestResolve = (route: ActivatedRouteSnapshot): Observable<null | IFinanceRequest> => {
  const id = route.params['id'];
  if (id) {
    return inject(FinanceRequestService)
      .find(id)
      .pipe(
        mergeMap((financeRequest: HttpResponse<IFinanceRequest>) => {
          if (financeRequest.body) {
            return of(financeRequest.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default financeRequestResolve;
