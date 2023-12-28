import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FinancePartnerDetailComponent } from './finance-partner-detail.component';

describe('FinancePartner Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FinancePartnerDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FinancePartnerDetailComponent,
              resolve: { financePartner: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FinancePartnerDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load financePartner on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FinancePartnerDetailComponent);

      // THEN
      expect(instance.financePartner).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
