import { render, screen } from '@testing-library/react';
import Upload from './upload';

test('renders learn react link', () => {
  render(<Upload />);
  const element = screen.getByText(/Build your components here/i);
  expect(element).toBeInTheDocument();
});
