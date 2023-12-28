import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEscrowAccountDetails } from '../escrow-account-details.model';
import { EscrowAccountDetailsService } from '../service/escrow-account-details.service';

export const escrowAccountDetailsResolve = (route: ActivatedRouteSnapshot): Observable<null | IEscrowAccountDetails> => {
  const id = route.params['id'];
  if (id) {
    return inject(EscrowAccountDetailsService)
      .find(id)
      .pipe(
        mergeMap((escrowAccountDetails: HttpResponse<IEscrowAccountDetails>) => {
          if (escrowAccountDetails.body) {
            return of(escrowAccountDetails.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default escrowAccountDetailsResolve;
