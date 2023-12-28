import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IIndividualAssessment } from '../individual-assessment.model';
import { IndividualAssessmentService } from '../service/individual-assessment.service';

export const individualAssessmentResolve = (route: ActivatedRouteSnapshot): Observable<null | IIndividualAssessment> => {
  const id = route.params['id'];
  if (id) {
    return inject(IndividualAssessmentService)
      .find(id)
      .pipe(
        mergeMap((individualAssessment: HttpResponse<IIndividualAssessment>) => {
          if (individualAssessment.body) {
            return of(individualAssessment.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default individualAssessmentResolve;
