import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFundsTransfer } from '../funds-transfer.model';
import { FundsTransferService } from '../service/funds-transfer.service';

export const fundsTransferResolve = (route: ActivatedRouteSnapshot): Observable<null | IFundsTransfer> => {
  const id = route.params['id'];
  if (id) {
    return inject(FundsTransferService)
      .find(id)
      .pipe(
        mergeMap((fundsTransfer: HttpResponse<IFundsTransfer>) => {
          if (fundsTransfer.body) {
            return of(fundsTransfer.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default fundsTransferResolve;
