import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { RepaymentDetailComponent } from './repayment-detail.component';

describe('Repayment Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepaymentDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: RepaymentDetailComponent,
              resolve: { repayment: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(RepaymentDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load repayment on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RepaymentDetailComponent);

      // THEN
      expect(instance.repayment).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
