import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITenantDetails } from '../tenant-details.model';
import { TenantDetailsService } from '../service/tenant-details.service';

export const tenantDetailsResolve = (route: ActivatedRouteSnapshot): Observable<null | ITenantDetails> => {
  const id = route.params['id'];
  if (id) {
    return inject(TenantDetailsService)
      .find(id)
      .pipe(
        mergeMap((tenantDetails: HttpResponse<ITenantDetails>) => {
          if (tenantDetails.body) {
            return of(tenantDetails.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default tenantDetailsResolve;
