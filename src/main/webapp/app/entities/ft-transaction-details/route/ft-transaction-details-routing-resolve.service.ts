import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFTTransactionDetails } from '../ft-transaction-details.model';
import { FTTransactionDetailsService } from '../service/ft-transaction-details.service';

export const fTTransactionDetailsResolve = (route: ActivatedRouteSnapshot): Observable<null | IFTTransactionDetails> => {
  const id = route.params['id'];
  if (id) {
    return inject(FTTransactionDetailsService)
      .find(id)
      .pipe(
        mergeMap((fTTransactionDetails: HttpResponse<IFTTransactionDetails>) => {
          if (fTTransactionDetails.body) {
            return of(fTTransactionDetails.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default fTTransactionDetailsResolve;
