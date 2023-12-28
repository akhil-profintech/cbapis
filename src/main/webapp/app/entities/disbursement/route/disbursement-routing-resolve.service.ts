import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDisbursement } from '../disbursement.model';
import { DisbursementService } from '../service/disbursement.service';

export const disbursementResolve = (route: ActivatedRouteSnapshot): Observable<null | IDisbursement> => {
  const id = route.params['id'];
  if (id) {
    return inject(DisbursementService)
      .find(id)
      .pipe(
        mergeMap((disbursement: HttpResponse<IDisbursement>) => {
          if (disbursement.body) {
            return of(disbursement.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default disbursementResolve;
