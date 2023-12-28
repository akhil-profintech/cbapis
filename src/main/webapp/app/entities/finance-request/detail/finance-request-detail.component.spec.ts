import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FinanceRequestDetailComponent } from './finance-request-detail.component';

describe('FinanceRequest Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FinanceRequestDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FinanceRequestDetailComponent,
              resolve: { financeRequest: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FinanceRequestDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load financeRequest on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FinanceRequestDetailComponent);

      // THEN
      expect(instance.financeRequest).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
