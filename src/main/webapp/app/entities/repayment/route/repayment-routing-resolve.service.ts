import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRepayment } from '../repayment.model';
import { RepaymentService } from '../service/repayment.service';

export const repaymentResolve = (route: ActivatedRouteSnapshot): Observable<null | IRepayment> => {
  const id = route.params['id'];
  if (id) {
    return inject(RepaymentService)
      .find(id)
      .pipe(
        mergeMap((repayment: HttpResponse<IRepayment>) => {
          if (repayment.body) {
            return of(repayment.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default repaymentResolve;
