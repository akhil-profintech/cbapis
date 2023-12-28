import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICreditBazaarIntegrator } from '../credit-bazaar-integrator.model';
import { CreditBazaarIntegratorService } from '../service/credit-bazaar-integrator.service';

export const creditBazaarIntegratorResolve = (route: ActivatedRouteSnapshot): Observable<null | ICreditBazaarIntegrator> => {
  const id = route.params['id'];
  if (id) {
    return inject(CreditBazaarIntegratorService)
      .find(id)
      .pipe(
        mergeMap((creditBazaarIntegrator: HttpResponse<ICreditBazaarIntegrator>) => {
          if (creditBazaarIntegrator.body) {
            return of(creditBazaarIntegrator.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default creditBazaarIntegratorResolve;
