import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICreditAccountDetails } from '../credit-account-details.model';
import { CreditAccountDetailsService } from '../service/credit-account-details.service';

export const creditAccountDetailsResolve = (route: ActivatedRouteSnapshot): Observable<null | ICreditAccountDetails> => {
  const id = route.params['id'];
  if (id) {
    return inject(CreditAccountDetailsService)
      .find(id)
      .pipe(
        mergeMap((creditAccountDetails: HttpResponse<ICreditAccountDetails>) => {
          if (creditAccountDetails.body) {
            return of(creditAccountDetails.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default creditAccountDetailsResolve;
