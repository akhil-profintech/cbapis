import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEscrowTransactionDetails } from '../escrow-transaction-details.model';
import { EscrowTransactionDetailsService } from '../service/escrow-transaction-details.service';

export const escrowTransactionDetailsResolve = (route: ActivatedRouteSnapshot): Observable<null | IEscrowTransactionDetails> => {
  const id = route.params['id'];
  if (id) {
    return inject(EscrowTransactionDetailsService)
      .find(id)
      .pipe(
        mergeMap((escrowTransactionDetails: HttpResponse<IEscrowTransactionDetails>) => {
          if (escrowTransactionDetails.body) {
            return of(escrowTransactionDetails.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default escrowTransactionDetailsResolve;
