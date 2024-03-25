import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFundsTransferTransactionDetails } from '../funds-transfer-transaction-details.model';
import { FundsTransferTransactionDetailsService } from '../service/funds-transfer-transaction-details.service';

export const fundsTransferTransactionDetailsResolve = (
  route: ActivatedRouteSnapshot,
): Observable<null | IFundsTransferTransactionDetails> => {
  const id = route.params['id'];
  if (id) {
    return inject(FundsTransferTransactionDetailsService)
      .find(id)
      .pipe(
        mergeMap((fundsTransferTransactionDetails: HttpResponse<IFundsTransferTransactionDetails>) => {
          if (fundsTransferTransactionDetails.body) {
            return of(fundsTransferTransactionDetails.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default fundsTransferTransactionDetailsResolve;
