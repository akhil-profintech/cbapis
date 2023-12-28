import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CreditBazaarIntegratorDetailComponent } from './credit-bazaar-integrator-detail.component';

describe('CreditBazaarIntegrator Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreditBazaarIntegratorDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CreditBazaarIntegratorDetailComponent,
              resolve: { creditBazaarIntegrator: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CreditBazaarIntegratorDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load creditBazaarIntegrator on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CreditBazaarIntegratorDetailComponent);

      // THEN
      expect(instance.creditBazaarIntegrator).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
