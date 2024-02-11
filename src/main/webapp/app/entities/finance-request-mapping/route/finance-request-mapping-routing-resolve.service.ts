import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFinanceRequestMapping } from '../finance-request-mapping.model';
import { FinanceRequestMappingService } from '../service/finance-request-mapping.service';

export const financeRequestMappingResolve = (route: ActivatedRouteSnapshot): Observable<null | IFinanceRequestMapping> => {
  const id = route.params['id'];
  if (id) {
    return inject(FinanceRequestMappingService)
      .find(id)
      .pipe(
        mergeMap((financeRequestMapping: HttpResponse<IFinanceRequestMapping>) => {
          if (financeRequestMapping.body) {
            return of(financeRequestMapping.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default financeRequestMappingResolve;
