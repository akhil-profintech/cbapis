import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { DisbursementDetailComponent } from './disbursement-detail.component';

describe('Disbursement Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DisbursementDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: DisbursementDetailComponent,
              resolve: { disbursement: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(DisbursementDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load disbursement on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', DisbursementDetailComponent);

      // THEN
      expect(instance.disbursement).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
