import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICBCREProcess } from '../cbcre-process.model';
import { CBCREProcessService } from '../service/cbcre-process.service';

export const cBCREProcessResolve = (route: ActivatedRouteSnapshot): Observable<null | ICBCREProcess> => {
  const id = route.params['id'];
  if (id) {
    return inject(CBCREProcessService)
      .find(id)
      .pipe(
        mergeMap((cBCREProcess: HttpResponse<ICBCREProcess>) => {
          if (cBCREProcess.body) {
            return of(cBCREProcess.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default cBCREProcessResolve;
