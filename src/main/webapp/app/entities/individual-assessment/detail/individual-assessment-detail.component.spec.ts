import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IndividualAssessmentDetailComponent } from './individual-assessment-detail.component';

describe('IndividualAssessment Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IndividualAssessmentDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: IndividualAssessmentDetailComponent,
              resolve: { individualAssessment: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(IndividualAssessmentDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load individualAssessment on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', IndividualAssessmentDetailComponent);

      // THEN
      expect(instance.individualAssessment).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
