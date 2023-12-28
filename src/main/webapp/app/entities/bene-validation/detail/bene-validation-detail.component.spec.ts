import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { BeneValidationDetailComponent } from './bene-validation-detail.component';

describe('BeneValidation Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BeneValidationDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: BeneValidationDetailComponent,
              resolve: { beneValidation: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(BeneValidationDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load beneValidation on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', BeneValidationDetailComponent);

      // THEN
      expect(instance.beneValidation).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
